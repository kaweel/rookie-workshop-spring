package com.kaweel.rookieworkshopspring.config.inteceptor;

import com.kaweel.rookieworkshopspring.auth.AuthService;
import com.kaweel.rookieworkshopspring.config.handle.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class ValidateTokenInterceptor implements HandlerInterceptor {

    @Autowired
    public AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("=== PreHandle ===");
        String token = request.getHeader("x-token");
        String txnId = request.getHeader("txnId");
        log.info("txnId {} ", txnId);
        if (StringUtils.isEmpty(token)) {
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        }
        Boolean inValidToken = !authService.validateToken(token);
        if (inValidToken) {
            throw new CustomException(HttpStatus.UNAUTHORIZED);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("=== PostHandle ===");
    }
}
