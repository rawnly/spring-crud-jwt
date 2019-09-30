package com.federicovitale.spring_jwt_boilerplate.web;

import com.federicovitale.spring_jwt_boilerplate.dtos.ErrorDTO;
import com.federicovitale.spring_jwt_boilerplate.dtos.MessageDTO;
import com.federicovitale.spring_jwt_boilerplate.dtos.PasswordResetDTO;
import com.federicovitale.spring_jwt_boilerplate.dtos.VerifiedUserResponse;
import com.federicovitale.spring_jwt_boilerplate.models.User.UserService;
import com.federicovitale.spring_jwt_boilerplate.utils.DateUtil;
import com.federicovitale.spring_jwt_boilerplate.utils.EasyEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/user")
public class UserJWTResource {
    @Autowired
    private UserService userService;

    @Autowired
    private EasyEmail easyEmail;

    @GetMapping("/me")
    public ResponseEntity getCurrentUser() {
        return userService.getUser().map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }


    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam(name = "token") String verificationToken) {
        return userService
                .verify(verificationToken)
                .map(u -> ResponseEntity.ok(new VerifiedUserResponse(u.getId(), "User verified.", true)))
                .orElse(ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new VerifiedUserResponse(null, "Invalid token", false))
                );
    }

    @PostMapping("/password/reset")
    public ResponseEntity passwordReset(
            @Valid @NotNull @RequestBody PasswordResetDTO passwordResetDTO
    ) {
        if ( !passwordResetDTO.validate() ) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorDTO("Reset Error", "Password mismatch", 400));
        }

        return userService
                .findByResetToken(passwordResetDTO.getToken())
                .map(user -> {
                    user.setResetToken(null);
                    user.setStartedPasswordRecovery(false);
                    user.setPasswordEncrypted( passwordResetDTO.getPassword() );
                    user = userService.save(user);

                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    // Start reset della password
    @PostMapping("/password/reset/start")
    public ResponseEntity<?> forgotPassword(HttpServletRequest request, @RequestParam(name = "username") String username) {
        return userService
                .findByUsernameOrEmail(username, username)
                .map(user -> {
                    user.setStartedPasswordRecovery(true);
                    user.setResetToken(UUID.randomUUID().toString());
                    user.setPasswordRecoveryStart(DateUtil.now(0L));
                    user = userService.save(user);

                    easyEmail.sendPasswordReset(user);

                    return ResponseEntity.ok(new MessageDTO("Password reset started successfully!"));
                })
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
