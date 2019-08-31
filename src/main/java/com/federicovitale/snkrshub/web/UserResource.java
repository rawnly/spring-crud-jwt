package com.federicovitale.snkrshub.web;

import com.federicovitale.snkrshub.dtos.ErrorDTO;
import com.federicovitale.snkrshub.dtos.MessageDTO;
import com.federicovitale.snkrshub.dtos.PasswordResetDTO;
import com.federicovitale.snkrshub.models.services.EmailService;
import com.federicovitale.snkrshub.models.services.impl.EmailServiceImpl;
import com.federicovitale.snkrshub.models.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}/info")
    public ResponseEntity getOne(@PathVariable Long id) {
        return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    // Verifica di un nuovo utente
    @PostMapping("/verify-user")
    public ResponseEntity verifyUser(@RequestParam(name = "token", required = true) String verificationToken) {
        return userService.verify(verificationToken).map(u -> ResponseEntity.ok(new MessageDTO("User verified."))).orElse(ResponseEntity.badRequest().build());
    }

    // Reset della password
    @PostMapping("/password-reset")
    public ResponseEntity passwordReset(
            @Valid @NotNull @RequestBody PasswordResetDTO passwordResetDTO,
            @RequestParam(name = "token", required = true) String passwordResetToken
    ) {
        if ( !passwordResetDTO.validate() ) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorDTO("Reset Error", "Password mismatch", 400));
        }

        return userService
                .findByResetToken(passwordResetToken)
                .map(user -> {
                    user.setResetToken(null);
                    user.setStartedPasswordRecovery(false);
                    user.setPasswordEncrypted( passwordResetDTO.getPassword() );
                    userService.save(user);
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    // Start reset della password
    @PostMapping("/start-password-reset")
    public ResponseEntity<?> forgotPassword(HttpServletRequest request, @RequestParam(name = "username") String username) {
        String appUrl = request.getScheme() + "://" + request.getServerName();

        return userService
                .findByUsernameOrEmail(username, username)
                .map(user -> {
                    user.setStartedPasswordRecovery(true);
                    user.setResetToken(UUID.randomUUID().toString());
                    user = userService.save(user);

                    SimpleMailMessage passwordResetMessage = new SimpleMailMessage();

                    passwordResetMessage.setFrom("fedevitale99@gmail.com");
                    passwordResetMessage.setTo(user.getEmail());
                    passwordResetMessage.setSubject("Password Recovery");
                    passwordResetMessage.setText(String.format("Hey! It seems that you've requested a password reset! Make a post request to: '%s'", appUrl + "/api/user/password-reset?token=" + user.getResetToken()));

                    emailService.sendEmail(passwordResetMessage);

                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }


}
