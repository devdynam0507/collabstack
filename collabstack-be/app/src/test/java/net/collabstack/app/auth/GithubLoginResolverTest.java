package net.collabstack.app.auth;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.MockRestServiceServer;

import net.collabstack.app.auth.dto.SocialLoginRequest;
import net.collabstack.app.auth.type.AuthenticationProvider;

@RestClientTest(value = { GithubLoginResolver.class })
class GithubLoginResolverTest {

    @Autowired
    GithubLoginResolver resolver;

    @Autowired
    MockRestServiceServer externalMockServer;

    @Test
    void githubLoginTest() {
        final SocialLoginRequest socialLoginRequest =
                new SocialLoginRequest(AuthenticationProvider.GITHUB, "token");

    }
}