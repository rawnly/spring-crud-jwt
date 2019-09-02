package com.federicovitale.spring_jwt_boilerplate.web;

import com.federicovitale.spring_jwt_boilerplate.dtos.ErrorDTO;
import com.federicovitale.spring_jwt_boilerplate.dtos.MessageDTO;
import com.federicovitale.spring_jwt_boilerplate.dtos.PasswordResetDTO;
import com.federicovitale.spring_jwt_boilerplate.models.services.EmailService;
import com.federicovitale.spring_jwt_boilerplate.models.services.impl.UserServiceImpl;
import com.federicovitale.spring_jwt_boilerplate.utils.EasyEmail;
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
@CrossOrigin
@Slf4j
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EasyEmail easyEmail;

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}/info")
    public ResponseEntity getOne(@PathVariable Long id) {
        return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }
}
