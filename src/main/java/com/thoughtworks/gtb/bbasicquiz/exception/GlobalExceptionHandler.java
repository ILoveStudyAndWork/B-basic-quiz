package com.thoughtworks.gtb.bbasicquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult handleCommonException(UserNotExistException ex){
        ErrorResult result = ErrorResult.builder()
                .message(ex.getMessage())
                .status(ex.getStatus())
                .error(ex.getError())
                .timestamp(ex.getTimestamp()).build();
        return result;
    }
}
