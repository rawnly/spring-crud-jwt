package com.federicovitale.snkrshub.dtos;

import lombok.Data;

@Data
public class ErrorDTO {
    private String title;
    private String message;
    private int statusCode;

    public ErrorDTO(String title, String message, int statusCode) {
        this.title = title;
        this.message = message;
        this.statusCode = statusCode;
    }
}
