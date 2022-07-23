package net.collabstack.app.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import net.collabstack.app.auth.dto.GithubAccessTokenResponse;
import net.collabstack.app.auth.dto.GithubUserResponse;
import net.collabstack.app.auth.dto.SocialLoginRequest;
import net.collabstack.app.auth.type.AuthenticationProvider;
import net.collabstack.security.JwtProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestClientTest(value = { GithubLoginResolver.class })
class GithubLoginResolverTest {

    private static final String githubAccessTokenResolveUrl =
            "https://github.com/login/oauth/access_token";
    private static final String githubUserInfoResolveUrl =
            "https://api.github.com/user";


    @Autowired
    GithubLoginResolver resolver;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MockRestServiceServer externalMockServer;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtProvider jwtProvider;

    @BeforeEach
    void init() {
        externalMockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void githubLoginTest() throws JsonProcessingException {
        final SocialLoginRequest socialLoginRequest =
                new SocialLoginRequest(AuthenticationProvider.GITHUB, "token");
        // access token의 결과 dto
        final GithubAccessTokenResponse accessTokenResponse = new GithubAccessTokenResponse();
        accessTokenResponse.setAccessToken("access_token");
        accessTokenResponse.setTokenType("token_type");
        accessTokenResponse.setScope("[]");
        // user api 결과 dto
        final Map<String, Object> githubUserResponse = new HashMap<>();
        githubUserResponse.put("id", 1);
        githubUserResponse.put("avatar_url", "https://github.avatar/1");
        githubUserResponse.put("company", "any_company");
        githubUserResponse.put("html_url", "https://github.com/devdynam0507");
        githubUserResponse.put("name", "gamzaman");
        githubUserResponse.put("email", "wsnam0507@gmail.com");
        final GithubUserResponse userResponse = GithubUserResponse.from(githubUserResponse);

        externalMockServer.expect(requestTo(githubAccessTokenResolveUrl))
                          .andExpect(method(HttpMethod.POST))
                          .andRespond(withSuccess(
                                  objectMapper.writeValueAsString(accessTokenResponse),
                                  MediaType.APPLICATION_JSON));
        externalMockServer.expect(requestTo(githubUserInfoResolveUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        objectMapper.writeValueAsString(userResponse), MediaType.APPLICATION_JSON));

        final String collabStackAccessToken = resolver.resolveLogin(socialLoginRequest);
        final String email = jwtProvider.decrypt(collabStackAccessToken, "id", String.class);

        assertThat(collabStackAccessToken == null).isFalse();
        assertThat(email).isEqualTo("wsnam0507@gmail.com");
    }
}