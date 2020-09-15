package com.thoughtworks.gtb.bbasicquiz.exception;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionFromConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserNotExistException extends Exception {
    private String message;
    private String error = ExceptionConstants.NOT_FOUND_MESSAGE;
    private int status = ExceptionConstants.NOT_FOUND_STATUS;
    private String timestamp;

    public UserNotExistException(String type,long message) {
        if (ExceptionFromConstants.BASIC_INFO.equals(type)){
            this.message = ExceptionConstants.USER_NOT_EXIST_WHEN_GET_USER_INFO+message;
        } else {
            this.message = ExceptionConstants.USER_NOT_EXIST_WHEN_GET_EDUCATION+message;
        }

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
