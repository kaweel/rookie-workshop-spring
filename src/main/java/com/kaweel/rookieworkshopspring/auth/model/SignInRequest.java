package com.kaweel.rookieworkshopspring.auth.model;

import lombok.Data;

@Data
public class SignInRequest {
    private String name;
    private String pwd;
}
