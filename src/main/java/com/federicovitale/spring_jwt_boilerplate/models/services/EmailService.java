package com.federicovitale.spring_jwt_boilerplate.models.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage message);
}
