package com.kaweel.rookieworkshopspring.config.handle;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ResponseMessage {

    private Date date;
    private String message;

    public ResponseMessage() {
        this.message = HttpStatus.OK.getReasonPhrase();
    }

    public ResponseMessage(String message) {
        this.date = new Date();
        this.message = message;
    }
}
