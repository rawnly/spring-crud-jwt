package com.federicovitale.spring_jwt_boilerplate.web;

import com.federicovitale.spring_jwt_boilerplate.dtos.ErrorDTO;
import com.federicovitale.spring_jwt_boilerplate.exceptions.EmailExistsException;
import com.federicovitale.spring_jwt_boilerplate.exceptions.UsernameExistsException;
import com.federicovitale.spring_jwt_boilerplate.models.User.User;
import com.federicovitale.spring_jwt_boilerplate.models.services.UserPreferenceService;
import com.federicovitale.spring_jwt_boilerplate.security.*;
import com.federicovitale.spring_jwt_boilerplate.utils.EasyEmail;
import com.federicovitale.spring_jwt_boilerplate.utils.enums.UserStatusError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class JWTAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtil tokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    private UserPreferenceService.UserServiceImpl userService;

    @Autowired
    private EasyEmail easyEmail;

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody JWTRequest body) throws Exception {
        try {
            authenticate(body.getUsername(), body.getPassword());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO("Authentication Error", e.getMessage(), 400));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(body.getUsername());

        UserStatusError userStatusError = userService.findByUsername(userDetails.getUsername()).map(user -> {

            if (user.getActive() && user.getVerified()) {
                user.setLastLogin(Timestamp.from(Instant.now()));

                if ( user.getPreferences().getGetNotifiedOnLogin() ) {
                    easyEmail.sendLoginNotification(user);
                }

                return UserStatusError.OK;
            }

            if (!user.getVerified()) {
                return UserStatusError.NOT_VERIFIED;
            }

            return UserStatusError.NOT_ENABLED;
        })
        .orElse(UserStatusError.NOT_ENABLED);

        ErrorDTO error = null;

        switch (userStatusError) {
            case NOT_VERIFIED:
                error = new ErrorDTO("Not authorized", "User not verified.", 401);
                break;
            case NOT_ENABLED:
                error = new ErrorDTO("Not authorized", "User disabled.", 401);
                break;
        }

        if ( error != null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        final String token = tokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid @NotNull @RequestBody SignUpBody signUpBody) {
        User user = new User(signUpBody);

        try {
            user.setDeleted(false);
            user.setActive(false);
            user.setVerified(false);
            user.setStartedPasswordRecovery(false);
            user.setEmailVerificationToken(UUID.randomUUID().toString());
            user = userService.save(user);
        } catch (UsernameExistsException | EmailExistsException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO("Registration Error", e.getMessage(), 400));
        }

        easyEmail.sendRegisteredMessage(user);

        return ResponseEntity.ok(user);
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Account disabled", e);
        } catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials", e);
        }
    }
}
