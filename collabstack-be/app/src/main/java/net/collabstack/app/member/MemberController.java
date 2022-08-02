package net.collabstack.app.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.collabstack.app.member.domain.Member;
import net.collabstack.app.member.dto.MemberResponse;
import net.collabstack.common.CommonResponse;
import net.collabstack.common.ResultCode;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @GetMapping("/{email}")
    public CommonResponse<MemberResponse> getMember(@PathVariable("email") final String email) {
        final Member member = memberService.getMember(email);
        final MemberResponse memberResponse = MemberResponse.from(member);
        return CommonResponse.success(ResultCode.CREATED, "멤버를 성공적으로 조회하였습니다.", memberResponse);
    }

}
