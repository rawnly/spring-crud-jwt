package com.federicovitale.snkrshub.models.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage message);
}
