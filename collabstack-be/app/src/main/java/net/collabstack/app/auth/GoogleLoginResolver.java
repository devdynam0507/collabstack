package net.collabstack.app.auth;

import java.util.Map;

import org.springframework.stereotype.Service;

import net.collabstack.app.auth.dto.SocialLoginRequest;
import net.collabstack.app.auth.type.AuthenticationProvider;
import net.collabstack.security.JwtProvider;

import com.auth0.jwt.interfaces.Claim;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleLoginResolver implements LoginResolver {

    private final JwtProvider jwtProvider;

    @Override
    public String resolveLogin(final SocialLoginRequest loginRequest) {
        if (loginRequest.getToken() == null) {
            throw new IllegalArgumentException("token은 필수입니다.");
        }
        if (loginRequest.getProvider() != this.getProvider()) {
            throw new IllegalArgumentException(
                    "잘못된 호출입니다. " + AuthenticationProvider.GOOGLE + "으로 로그인해야합니다.");
        }
        final Map<String, Claim> claimMap =
                jwtProvider.decryptWithNoAlgorithm(loginRequest.getToken());
        final String imageUrl = claimMap.get("picture").asString();
        final String name = claimMap.get("name").asString();
        final String email = claimMap.get("email").asString();
        return jwtProvider.encrypt("id", email, 60L * 60);
    }

    @Override
    public AuthenticationProvider getProvider() {
        return AuthenticationProvider.GOOGLE;
    }
}
