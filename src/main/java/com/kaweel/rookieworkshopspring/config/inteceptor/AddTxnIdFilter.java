package com.kaweel.rookieworkshopspring.config.inteceptor;

import com.kaweel.rookieworkshopspring.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@WebFilter("/**")
@Component
public class AddTxnIdFilter implements Filter {

    @Autowired
    public AuthService authService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("=== doFilterInternal ===");
        HeaderMapRequestWrapper wrappedRequest = new HeaderMapRequestWrapper((HttpServletRequest) request);
        wrappedRequest.addHeader("txnId", UUID.randomUUID().toString());
        chain.doFilter(wrappedRequest, response);
    }
}
