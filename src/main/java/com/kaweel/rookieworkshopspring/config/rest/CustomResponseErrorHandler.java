package com.kaweel.rookieworkshopspring.config.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
public class CustomResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series() == CLIENT_ERROR || response.getStatusCode().series() == SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus httpStatus = response.getStatusCode();
        if (httpStatus.series() == HttpStatus.Series.SERVER_ERROR) {
            log.info("SERVER_ERROR httpStatus is {}", httpStatus);
        } else if (httpStatus.series() == HttpStatus.Series.CLIENT_ERROR) {
            log.info("CLIENT_ERROR httpStatus is {}", httpStatus);
            if (httpStatus == HttpStatus.NOT_FOUND) {
                log.info("CLIENT_ERROR NOT_FOUND");
            }
        }
    }
}
