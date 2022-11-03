package com.example.eventBridge_backend.controller;


import com.example.eventBridge_backend.config.Config;
import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.entity.Role;
import com.example.eventBridge_backend.payload.JWTAuthResponse;
import com.example.eventBridge_backend.payload.LoginDto;
import com.example.eventBridge_backend.payload.SignUpDto;
import com.example.eventBridge_backend.payload.UserResponse;
import com.example.eventBridge_backend.repository.RoleRepository;
import com.example.eventBridge_backend.repository.UserRepository;
import com.example.eventBridge_backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;

//@Api(value = "Auth controller exposes siginin and signup REST APIs")
@CrossOrigin(origins = Config.HOST)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

//    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestParam(value = "params", required = false) String pass, LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getFirstNameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

//    @ApiOperation(value = "REST API to Signin or Login user to Blog app")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByFirstName(signUpDto.getFirstName())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        Person user = new Person();
        user.setLastName(signUpDto.getName());
        user.setFirstName(signUpDto.getFirstName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));



        Role roles = roleRepository.findRoleByName("ROLE_ADMIN");
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @GetMapping(value="/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);


        }
        return "redirect:/";
    }

    @PostMapping("/getDetails")
    public ResponseEntity<UserResponse> getUserDetail(@RequestParam String token){
        UserResponse userResponse = new UserResponse();

        String toks = tokenProvider.getUsernameFromJWT(token);
        Optional<Person> person = userRepository.findByFirstName(toks);
        userResponse.setId(person.get().getPersonId());
        userResponse.setUsername(person.get().getFirstName());
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);

    }

}
