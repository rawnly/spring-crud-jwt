package com.federicovitale.snkrshub.security;

import lombok.Data;

@Data
public class JWTRequest {
    private String username;
    private String password;
}
