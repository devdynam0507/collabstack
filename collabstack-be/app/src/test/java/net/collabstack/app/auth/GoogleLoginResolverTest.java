package net.collabstack.app.auth;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.collabstack.app.auth.dto.SocialLoginRequest;
import net.collabstack.app.auth.type.AuthenticationProvider;
import net.collabstack.security.JwtProvider;

public class GoogleLoginResolverTest {

    private JwtProvider jwtProvider;

    private GoogleLoginResolver resolver;

    @BeforeEach
    void init() {
        jwtProvider = new JwtProvider();
        jwtProvider.setSecretKey("secret");
        jwtProvider.init();
        resolver =  new GoogleLoginResolver(jwtProvider);
    }

    @Test
    void googleLoginTest() {
        final Map<String, Object> googleAuthenticationData = new HashMap<>();
        googleAuthenticationData.put("picture", "https://google.picture.com");
        googleAuthenticationData.put("name", "Daeyoung Nam");
        googleAuthenticationData.put("email", "wsnam0507@gmail.com");
        final String googleAccessToken =
                jwtProvider.encryptMapWithoutAlgorithm(
                        googleAuthenticationData, 60L * 60);
        final SocialLoginRequest request = new SocialLoginRequest(
                AuthenticationProvider.GOOGLE, googleAccessToken);

        final String response = resolver.resolveLogin(request);
        assertThat(response == null).isFalse();

        final String email = jwtProvider.decrypt(response, "id", String.class);
        assertThat(email).isEqualTo("wsnam0507@gmail.com");
    }
}
