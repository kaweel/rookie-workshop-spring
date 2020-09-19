package com.kaweel.rookieworkshopspring.auth.model;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
}
