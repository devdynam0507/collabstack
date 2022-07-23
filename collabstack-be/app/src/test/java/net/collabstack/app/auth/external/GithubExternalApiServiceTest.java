package net.collabstack.app.auth.external;

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

import net.collabstack.app.auth.dto.GithubAccessTokenRequest;
import net.collabstack.app.auth.dto.GithubAccessTokenResponse;
import net.collabstack.app.auth.dto.GithubUserResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestClientTest(GithubExternalApiService.class)
class GithubExternalApiServiceTest {

    private static final String githubAccessTokenResolveUrl =
            "https://github.com/login/oauth/access_token";
    private static final String githubUserInfoResolveUrl =
            "https://api.github.com/user";

    @Autowired
    GithubExternalApiService externalApiService;

    @Autowired
    RestTemplate restTemplate;

    MockRestServiceServer externalMockServer;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        externalMockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void resolveAccessTokenTest() throws JsonProcessingException {
        final GithubAccessTokenRequest request = GithubAccessTokenRequest.from(
                "github_client_id",
                "github_client_secret" ,
                "github_login_code");
        final GithubAccessTokenResponse response = new GithubAccessTokenResponse();
        response.setAccessToken("access_token");
        response.setTokenType("token_type");
        response.setScope("[]");

        externalMockServer.expect(requestTo(githubAccessTokenResolveUrl))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(
                        objectMapper.writeValueAsString(response), MediaType.APPLICATION_JSON));

        final GithubAccessTokenResponse accessTokenResponse =
                externalApiService.resolveGithubAccessToken(request);

        assertThat(accessTokenResponse.getAccessToken()).isEqualTo("access_token");
        assertThat(accessTokenResponse.getTokenType()).isEqualTo("token_type");
        assertThat(accessTokenResponse.getScope()).isEqualTo("[]");
    }

    @Test
    void resolveGithubUserTest() throws JsonProcessingException {
        final String request = "github_access_token";
        final Map<String, Object> githubUserResponse = new HashMap<>();
        githubUserResponse.put("id", 1);
        githubUserResponse.put("avatar_url", "https://github.avatar/1");
        githubUserResponse.put("company", "any_company");
        githubUserResponse.put("html_url", "https://github.com/devdynam0507");
        githubUserResponse.put("name", "gamzaman");
        githubUserResponse.put("email", "wsnam0507@gmail.com");
        final GithubUserResponse response = GithubUserResponse.from(githubUserResponse);

        externalMockServer.expect(requestTo(githubUserInfoResolveUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(
                        objectMapper.writeValueAsString(githubUserResponse),
                        MediaType.APPLICATION_JSON));

        final GithubUserResponse then = externalApiService.resolveGithubUser(request);

        assertThat(then.getId()).isEqualTo(1);
        assertThat(then.getImageUrl()).isEqualTo("https://github.avatar/1");
        assertThat(then.getCompany()).isEqualTo("any_company");
        assertThat(then.getGithubUrl()).isEqualTo("https://github.com/devdynam0507");
        assertThat(then.getName()).isEqualTo("gamzaman");
        assertThat(then.getEmail()).isEqualTo("wsnam0507@gmail.com");
    }
}