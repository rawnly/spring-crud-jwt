package com.federicovitale.spring_jwt_boilerplate.utils;

import com.federicovitale.spring_jwt_boilerplate.models.User.User;
import com.federicovitale.spring_jwt_boilerplate.models.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EasyEmail {
    @Autowired
    private EmailService emailService;

    @Value("${client.address}")
    private String clientAddress;

    @Value("${client.port}")
    private String clientPort;

    private String getClientURL() {
        return String.format("%s:%s", this.clientAddress, this.clientPort);
    }

    @Async
    public void sendPasswordReset(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("fedevitale99@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Password Recovery");
        mailMessage.setText(String.format("Hey! It seems that you've requested a password reset! Make a post request to: '%s'", getClientURL() + "/auth/reset?token=" + user.getResetToken()));

        emailService.sendEmail(mailMessage);
    }

    @Async
    public void sendLoginNotification(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("fedevitale99@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("New Login");
        mailMessage.setText(String.format("Hi %s! A new login has been done.", user.getFirstName()));

        emailService.sendEmail(mailMessage);
    }

    public void sendRegisteredMessage(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("fedevitale99@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Welcome On Board!");
        mailMessage.setText(String.format("Hi %s! Thanks for having registered with us! Please verify your account at : '%s'", user.getFirstName(), getClientURL() + "/auth/verify?token=" + user.getEmailVerificationToken()));

        emailService.sendEmail(mailMessage);
    }
}
