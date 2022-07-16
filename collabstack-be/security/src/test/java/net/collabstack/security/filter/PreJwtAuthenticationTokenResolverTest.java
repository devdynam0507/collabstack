package net.collabstack.security.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import net.collabstack.security.TokenInvalidException;

class PreJwtAuthenticationTokenResolverTest {

    PreJwtAuthenticationTokenResolver tokenResolver = new PreJwtAuthenticationTokenResolver();

    @Test
    void tokenResolverTest() {
        final String authorizationValue = "Bearer token";

        String resolved = tokenResolver.resolve(authorizationValue);

        assertThat(resolved).isEqualTo("token");
    }

    @Test
    void lowercaseBearerTokenResolverTest() {
        final String authorizationValue = "bearer token";

        String resolved = tokenResolver.resolve(authorizationValue);

        assertThat(resolved).isEqualTo("token");
    }

    @Test
    void emptyAuthorizationValueTest() {
        final String authorizationValue = "";

        assertThatThrownBy(() -> tokenResolver.resolve(authorizationValue))
                .isInstanceOf(TokenInvalidException.class);
    }

    @Test
    void invalidAuthorizationValueTest() {
        final String authorizationValue = "invalid token";

        assertThatThrownBy(() -> tokenResolver.resolve(authorizationValue))
                .isInstanceOf(TokenInvalidException.class);
    }
}