package net.collabstack.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.auth0.jwt.exceptions.TokenExpiredException;

class JwtProviderTest {

    private JwtProvider jwtProvider = new JwtProvider();

    @BeforeEach
    public void beforeEach() {
        jwtProvider.setSecretKey("secretKey");
        jwtProvider.init();
    }

    @Test
    public void encryptString() {
        final String encrypted = jwtProvider.encrypt("id", "value", 10L);

        final String decrypt = jwtProvider.decrypt(encrypted, "id", String.class);

        assertThat(decrypt).isEqualTo("value");
    }

    @Test
    public void encryptInt() {
        final String encrypted = jwtProvider.encrypt("id", 1, 10L);

        final Integer decrypt = jwtProvider.decrypt(encrypted, "id", Integer.class);

        assertThat(decrypt).isEqualTo(1);
    }

    @Test
    public void encryptLong() {
        final String encrypted = jwtProvider.encrypt("id", 1L, 10L);

        final Long decrypt = jwtProvider.decrypt(encrypted, "id", Long.class);

        assertThat(decrypt).isEqualTo(1L);
    }

    @Test
    public void encryptBoolean() {
        final String encrypted = jwtProvider.encrypt("id", true, 10L);

        final Boolean decrypt = jwtProvider.decrypt(encrypted, "id", Boolean.class);

        assertThat(decrypt).isEqualTo(true);
    }

    @Test
    public void encryptCollections() {
        assertThatThrownBy(() -> jwtProvider.encrypt("id", Arrays.asList(), 10L))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> jwtProvider.encrypt("id", Maps.newHashMap("", ""), 10L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void decryptCollections() {
        final String anyEncrypted = jwtProvider.encrypt("id", "any", 10L);

        assertThatThrownBy(() -> jwtProvider.decrypt(anyEncrypted, "id", HashMap.class))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> jwtProvider.decrypt(anyEncrypted, "id", List.class))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> jwtProvider.decrypt(anyEncrypted, "id", Set.class))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void expiredTest() throws InterruptedException {
        final String anyEncrypted = jwtProvider.encrypt("id", "any", 0L);

        Thread.sleep(1000);

        assertThatThrownBy(() -> jwtProvider.decrypt(anyEncrypted, "id", String.class))
                .isInstanceOf(TokenExpiredException.class);
    }
}