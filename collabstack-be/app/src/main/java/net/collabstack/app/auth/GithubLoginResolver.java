package net.collabstack.app.auth;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import net.collabstack.app.auth.dto.GithubAccessTokenRequest;
import net.collabstack.app.auth.dto.GithubAccessTokenResponse;
import net.collabstack.app.auth.dto.GithubUserResponse;
import net.collabstack.app.auth.dto.SocialLoginRequest;
import net.collabstack.app.auth.type.AuthenticationProvider;
import net.collabstack.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GithubLoginResolver implements LoginResolver {

    private final JwtProvider jwtProvider;
    private final RestTemplate restTemplate;

    @Value("${github.secret}")
    private String githubSecret;

    @Value("${github.client_id}")
    private String githubClientId;

    @Override
    public String resolveLogin(final SocialLoginRequest loginRequest) {
        if (loginRequest.getProvider() != AuthenticationProvider.GITHUB) {
            throw new IllegalArgumentException(
                    "잘못된 호출입니다. " + AuthenticationProvider.GITHUB + "으로 로그인해야합니다.");
        }
        final GithubAccessTokenRequest tokenRequest =
                GithubAccessTokenRequest.from(githubSecret, githubClientId, loginRequest.getToken());
        final String accessToken = resolveGithubAccessToken(tokenRequest).getAccessToken();
        if (accessToken == null) {
            throw new IllegalStateException("로그인을 다시 시도해주세요.");
        }
        final GithubUserResponse githubUserResponse = resolveGithubUser(accessToken);
        return jwtProvider.encrypt("id", githubUserResponse.getEmail(), 60L * 60);
    }

    private GithubUserResponse resolveGithubUser(final String accessToken) {
        final UriComponents githubUserApiUrl =
                UriComponentsBuilder.fromHttpUrl("https://api.github.com/user")
                                    .build();
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization", "token " + accessToken);
        final HttpEntity userHttp = new HttpEntity(headers);
        final ResponseEntity<Map> userResponse =
                restTemplate.exchange(
                        githubUserApiUrl.toUri(), HttpMethod.GET, userHttp, Map.class);
        if (userResponse.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("github에서 유저 정보를 불러오는데 실패하였습니다. 다시 시도해주세요.");
        }
        return GithubUserResponse.from(Objects.requireNonNull(userResponse.getBody()));
    }

    private GithubAccessTokenResponse resolveGithubAccessToken(
            final GithubAccessTokenRequest tokenRequest) {
        final UriComponents requestUrl =
                UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/access_token")
                                    .build();
        final HttpEntity<GithubAccessTokenRequest> httpEntity = createHttpRequest(tokenRequest);
        final ResponseEntity<GithubAccessTokenResponse> accessTokenResponse =
                restTemplate.postForEntity(requestUrl.toUri(), httpEntity, GithubAccessTokenResponse.class);
        if (accessTokenResponse.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("인증코드가 잘못되었습니다.");
        }
        return accessTokenResponse.getBody();
    }

    private HttpEntity<GithubAccessTokenRequest> createHttpRequest(
            final GithubAccessTokenRequest tokenRequest) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(tokenRequest, headers);
    }

    @Override
    public AuthenticationProvider getProvider() {
        return AuthenticationProvider.GITHUB;
    }
}
