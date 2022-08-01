package net.collabstack.app.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.collabstack.app.auth.dto.SocialLoginRequest;
import net.collabstack.app.auth.dto.SocialLoginResponse;
import net.collabstack.common.CommonResponse;
import net.collabstack.common.ResultCode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final LoginStrategy loginStrategy;

    @PostMapping
    public CommonResponse<SocialLoginResponse> login(@RequestBody final SocialLoginRequest loginRequest) {
        if (loginRequest.getProvider() == null) {
            throw new IllegalArgumentException("소셜 로그인 provider는 필수입니다.");
        }
        final String token = loginStrategy.strategy(loginRequest.getProvider())
                                          .resolveLogin(loginRequest);
        final SocialLoginResponse response = new SocialLoginResponse();
        response.setAccessToken(token);
        return CommonResponse.success(ResultCode.OK, "인증되었습니다.",  );
    }
}
