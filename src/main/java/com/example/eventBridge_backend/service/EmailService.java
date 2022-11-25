package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.payload.EmailDetails;

public interface EmailService {

    public void sendMailWithInlineResources(String to, String subject, String fileToAttach);

    String sendSimpleMail(EmailDetails details);
}
