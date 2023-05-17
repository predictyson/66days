package com.ssafy._66days.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateNicknameException extends RuntimeException {

    public DuplicateNicknameException() {
    }

    public DuplicateNicknameException(String message) {
        super(message);
    }
}
