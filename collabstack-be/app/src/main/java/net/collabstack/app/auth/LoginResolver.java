package com.collabstack.app.auth;

import com.collabstack.app.auth.dto.SocialLoginRequest;
import com.collabstack.app.auth.type.AuthenticationProvider;

public interface LoginResolver {

    String resolveLogin(final SocialLoginRequest loginRequest);

    AuthenticationProvider getProvider();

}
