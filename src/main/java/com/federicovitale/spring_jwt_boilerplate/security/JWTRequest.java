package com.federicovitale.spring_jwt_boilerplate.security;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}
