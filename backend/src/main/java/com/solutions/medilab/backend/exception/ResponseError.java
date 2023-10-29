package com.solutions.medilab.backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {
    private final String code;
    private final String message;

    public ResponseError(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
