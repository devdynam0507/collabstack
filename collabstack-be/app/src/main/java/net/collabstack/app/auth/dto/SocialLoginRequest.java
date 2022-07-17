package com.collabstack.app.auth.dto;

import com.collabstack.app.auth.type.AuthenticationProvider;

import lombok.Data;

@Data
public class SocialLoginRequest {

    private final AuthenticationProvider provider;
    private final String token;

}
