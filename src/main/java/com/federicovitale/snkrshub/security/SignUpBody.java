package com.federicovitale.snkrshub.security;

import lombok.Data;

@Data
public class SignUpBody {
    private String firstName;
    private String lastName;

    private String username;
    private String email;

    private String password;
}
