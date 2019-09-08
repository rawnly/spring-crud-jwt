package com.federicovitale.spring_jwt_boilerplate.web;

import com.federicovitale.spring_jwt_boilerplate.models.services.impl.UserServiceImpl;
import com.federicovitale.spring_jwt_boilerplate.utils.EasyEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/users")
@RestController
@CrossOrigin
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
