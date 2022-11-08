package com.example.eventBridge_backend.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"firstName"})
})
public class Person {

    @Id
   @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long personId;

    @NotBlank(message = "Please add user email")
    private String email;
    private String firstName;
    private String lastName;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
                joinColumns =@JoinColumn(name = "user_id", referencedColumnName = "personId"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;


//    @OneToMany(mappedBy = "customer", orphanRemoval = true)
//    private List<Ticket> tickets = new ArrayList<>();
}
