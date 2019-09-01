package com.federicovitale.spring_jwt_boilerplate.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException() {
    }

    public UsernameExistsException(String message) {
        super(message);
    }
}
