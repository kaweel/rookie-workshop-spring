package com.kaweel.rookieworkshopspring.auth;

import com.kaweel.rookieworkshopspring.auth.model.SignInRequest;
import com.kaweel.rookieworkshopspring.auth.model.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public SignInResponse signIn(@RequestBody SignInRequest signUpRequest) {
        return authService.signIn(signUpRequest);
    }

    @DeleteMapping("/sign-out")
    public void signOut(@RequestHeader("x-token") String token) {
        authService.signOut(token);
    }
}
