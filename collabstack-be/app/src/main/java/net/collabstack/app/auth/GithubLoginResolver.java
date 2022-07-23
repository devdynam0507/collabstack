package net.collabstack.app.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.collabstack.app.auth.dto.GithubAccessTokenRequest;
import net.collabstack.app.auth.dto.GithubUserResponse;
import net.collabstack.app.auth.dto.SocialLoginRequest;
import net.collabstack.app.auth.external.GithubExternalApiService;
import net.collabstack.app.auth.type.AuthenticationProvider;
import net.collabstack.app.member.MemberService;
import net.collabstack.app.member.dto.MemberCreate;
import net.collabstack.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GithubLoginResolver implements LoginResolver {

    private final JwtProvider jwtProvider;
    private final GithubExternalApiService githubApi;
    private final MemberService memberService;

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
        final String accessToken = githubApi.resolveGithubAccessToken(tokenRequest)
                                            .getAccessToken();
        if (accessToken == null) {
            throw new IllegalStateException("로그인을 다시 시도해주세요.");
        }
        final GithubUserResponse githubUserResponse = githubApi.resolveGithubUser(accessToken);
        memberService.createMember(MemberCreate.from(memberCreate -> {
            memberCreate.setEmail(githubUserResponse.getEmail());
            memberCreate.setUsername(githubUserResponse.getName());
            memberCreate.setCompany(githubUserResponse.getCompany());
            memberCreate.setProfileImageUrl(githubUserResponse.getImageUrl());
            memberCreate.setGithubUrl(githubUserResponse.getGithubUrl());
            return memberCreate;
        }));
        return jwtProvider.encrypt("id", githubUserResponse.getEmail(), 60L * 60);
    }

    @Override
    public AuthenticationProvider getProvider() {
        return AuthenticationProvider.GITHUB;
    }
}
