package com.federicovitale.spring_jwt_boilerplate.dtos;

import lombok.Data;

@Data
public class VerifiedUserResponse {
    public VerifiedUserResponse(Long id, String message, Boolean verified) {
        this.id = id;
        this.verified = verified;
        this.message = message;
    }

    private Long id;
    private Boolean verified;
    private String message;
}
