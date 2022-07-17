package net.collabstack.app.auth;

import net.collabstack.app.auth.dto.SocialLoginRequest;
import net.collabstack.app.auth.type.AuthenticationProvider;

public interface LoginResolver {

    String resolveLogin(final SocialLoginRequest loginRequest);

    AuthenticationProvider getProvider();

}
