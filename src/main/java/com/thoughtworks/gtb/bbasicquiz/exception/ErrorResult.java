package com.thoughtworks.gtb.bbasicquiz.exception;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {
    private String message;
    private String error = ExceptionConstants.NOT_FOUND_MESSAGE;
    private int status = ExceptionConstants.NOT_FOUND_STATUS;
    private String timestamp;
}
