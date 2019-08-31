package com.federicovitale.snkrshub.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException() {
    }

    public UsernameExistsException(String message) {
        super(message);
    }
}
