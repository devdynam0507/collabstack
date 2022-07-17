package com.collabstack.app.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import net.collabstack.security.JwtProvider;

import com.collabstack.app.auth.dto.GithubAccessTokenRequest;
import com.collabstack.app.auth.dto.SocialLoginRequest;
import com.collabstack.app.auth.type.AuthenticationProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GithubLoginResolver implements LoginResolver {

    private final JwtProvider jwtProvider;
    private final RestTemplate restTemplate;

    @Value("${github.secret}")
    private final String githubSecret;

    @Value("${github.client_id}")
    private final String githubClientId;

    @Override
    public String resolveLogin(SocialLoginRequest loginRequest) {
        if (loginRequest.getProvider() != AuthenticationProvider.GITHUB) {
            throw new IllegalArgumentException(
                    "잘못된 호출입니다. " + AuthenticationProvider.GITHUB + "으로 로그인해야합니다.");
        }
        final UriComponents requestUrl =
                UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/access_token")
                                    .build();
        final GithubAccessTokenRequest tokenRequest =
                GithubAccessTokenRequest.from(githubClientId, githubSecret, loginRequest.getToken());
        final ResponseEntity<GithubAccessTokenRequest> response =
                restTemplate.postForEntity(requestUrl.toUri(), tokenRequest, GithubAccessTokenRequest.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("인증코드가 잘못되었습니다.");
        }
        return jwtProvider.encrypt("id", loginRequest.getToken(), 60L * 60);
    }

    @Override
    public AuthenticationProvider getProvider() {
        return AuthenticationProvider.GITHUB;
    }
}
