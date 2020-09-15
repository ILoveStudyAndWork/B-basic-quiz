package com.thoughtworks.gtb.bbasicquiz.exception;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResult> handleCommonException(UserNotExistException ex){
        ErrorResult errorResult = ErrorResult.builder()
                .message(ex.getMessage())
                .status(ex.getStatus())
                .error(ex.getError())
                .timestamp(ex.getTimestamp()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        ErrorResult errorResult = ErrorResult.builder()
                .timestamp(sdf.format(new Date().getTime()))
                .error(ExceptionConstants.REQUEST_PARAM_NOT_VALID_MESSAGE)
                .status(ExceptionConstants.BAD_REQUEST_STATUS)
                .message(ex.getBindingResult().getFieldError().getDefaultMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

}
