package net.collabstack.app.auth.external;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GithubExternalApiService {

    private static final String githubAccessTokenResolveUrl =
            "https://github.com/login/oauth/access_token";
    private static final String githubUserInfoResolveUrl =
            "https://api.github.com/user";

    private final RestTemplate restTemplate;

    /**
     * 실제 깃허브 OAuth2 서버와 통신하여 Github의 access token을 받아옵니다.
     *
     * @return access_token of github
     * */
    public GithubAccessTokenResponse resolveGithubAccessToken(
            final GithubAccessTokenRequest tokenRequest) {
        final UriComponents requestUrl =
                UriComponentsBuilder.fromHttpUrl(githubAccessTokenResolveUrl)
                                    .build();
        final HttpEntity<GithubAccessTokenRequest> httpEntity = createHttpRequest(tokenRequest);
        final ResponseEntity<GithubAccessTokenResponse> accessTokenResponse =
                restTemplate.postForEntity(requestUrl.toUri(), httpEntity, GithubAccessTokenResponse.class);
        if (accessTokenResponse.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("인증코드가 잘못되었습니다.");
        }
        return accessTokenResponse.getBody();
    }

    /**
     * Github access token을 이용하여 유저의 정보를 얻어옵니다.
     *
     * @return Github user 정보
     * */
    public GithubUserResponse resolveGithubUser(final String accessToken) {
        final UriComponents githubUserApiUrl =
                UriComponentsBuilder.fromHttpUrl(githubUserInfoResolveUrl)
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

    private HttpEntity<GithubAccessTokenRequest> createHttpRequest(
            final GithubAccessTokenRequest tokenRequest) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(tokenRequest, headers);
    }
}
