package net.collabstack.app.auth.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AuthenticationProvider {

    GITHUB, GOOGLE;

    @JsonCreator
    public static AuthenticationProvider from(final String provider) {
        return AuthenticationProvider.valueOf(provider.toUpperCase());
    }

}
