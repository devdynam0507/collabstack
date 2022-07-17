package net.collabstack.app.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubAccessTokenRequest {

    private final String clientId;
    private final String clientSecret;
    private final String code;

    public static GithubAccessTokenRequest from(
            final String clientId, final String clientSecret, final String code) {
        final GithubAccessTokenRequest tokenRequest =
                new GithubAccessTokenRequest(clientId, clientSecret, code);
        return tokenRequest;
    }
}
