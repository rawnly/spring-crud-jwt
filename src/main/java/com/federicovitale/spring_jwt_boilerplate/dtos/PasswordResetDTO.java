package com.federicovitale.spring_jwt_boilerplate.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class PasswordResetDTO {
    @JsonProperty("password")
    @Valid @NotEmpty
    private String password;

    @JsonProperty("confirm")
    @Valid @NotEmpty
    private String passwordConfirmation;

    @JsonProperty("token")
    @Valid @NotEmpty
    private String token;

    public Boolean validate() {
        return password.equals(passwordConfirmation);
    }
}
