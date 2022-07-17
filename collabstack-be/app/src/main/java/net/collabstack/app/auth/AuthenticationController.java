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

    private final LoginResolver loginResolver;

    @PostMapping
    public CommonResponse<SocialLoginResponse> login(@RequestBody final SocialLoginRequest loginRequest) {
        final String token = loginResolver.resolveLogin(loginRequest);
        final SocialLoginResponse response = new SocialLoginResponse();
        response.setAccessToken(token);
        return CommonResponse.success(ResultCode.OK, "인증되었습니다.", response);
    }

}
