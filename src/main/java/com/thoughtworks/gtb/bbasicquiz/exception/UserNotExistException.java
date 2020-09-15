package com.thoughtworks.gtb.bbasicquiz.exception;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserNotExistException extends Exception {
    private String message;
    private String error = ExceptionConstants.NOT_FOUND_MESSAGE;
    private int status = ExceptionConstants.NOT_FOUND_STATUS;
    private String timestamp;

    public UserNotExistException(long message) {
        this.message = ExceptionConstants.USER_NOT_EXIST+message;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        this.timestamp = sdf.format(new Date().getTime());
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
