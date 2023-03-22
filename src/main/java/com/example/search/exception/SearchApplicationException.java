package com.example.search.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public SearchApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = this.getMessage();
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        }
        return String.format("%s. %s", errorCode.getMessage(), message);
    }
}
