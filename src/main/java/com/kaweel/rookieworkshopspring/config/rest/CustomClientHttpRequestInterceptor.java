package com.kaweel.rookieworkshopspring.config.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String txnId = UUID.randomUUID().toString();
        log.info("{} request : api [{}] : method [{}] : header [{}] : body [{}]", txnId, request.getURI(), request.getMethod(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
        ClientHttpResponse response = new CustomClientHttpResponse(execution.execute(request, body));
        log.info("{} response : api [{}] : http status [{}]: header [{}] : body [{}]", txnId, request.getURI(), response.getStatusCode(), response.getHeaders(), StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        return response;
    }

}
