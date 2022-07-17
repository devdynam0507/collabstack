package net.collabstack.app.auth.dto;

import net.collabstack.app.auth.type.AuthenticationProvider;

import lombok.Data;

@Data
public class SocialLoginRequest {

    private final AuthenticationProvider provider;
    private final String token;

}
