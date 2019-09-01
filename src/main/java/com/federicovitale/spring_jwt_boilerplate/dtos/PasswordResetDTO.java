package com.federicovitale.spring_jwt_boilerplate.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PasswordResetDTO {
    @JsonProperty("password")
    private String password;

    @JsonProperty("confirm")
    private String passwordConfirmation;

    public Boolean validate() {
        return password.equals(passwordConfirmation);
    }
}
