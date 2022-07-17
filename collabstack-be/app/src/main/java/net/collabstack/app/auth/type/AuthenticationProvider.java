package com.collabstack.app.auth.type;

public enum AuthenticationProvider {

    GITHUB, GOOGLE;

    public static AuthenticationProvider from(final String provider) {
        return AuthenticationProvider.valueOf(provider.toUpperCase());
    }

}
