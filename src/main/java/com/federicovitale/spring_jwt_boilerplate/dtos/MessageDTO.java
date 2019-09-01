package com.federicovitale.spring_jwt_boilerplate.dtos;

import lombok.Data;

@Data
public class MessageDTO {
    private String message;

    public MessageDTO(String message) {
        this.message = message;
    }
}
