package com.kaweel.rookieworkshopspring.config.handle;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    private HttpStatus httpStatus;
    private ResponseMessage responseMessage;

    public CustomException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.responseMessage = new ResponseMessage(httpStatus.getReasonPhrase());
    }

    public CustomException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.responseMessage = new ResponseMessage(message);
    }

}
