package net.collabstack.app.member;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import net.collabstack.app.member.domain.Member;
import net.collabstack.security.member.MemberResolver;
import net.collabstack.security.member.SecurityMember;

import lombok.RequiredArgsConstructor;

/**
 * Spring security 에서 인증 필터를 거칠 때 MemberResolver를 이용하여 유저 정보를 얻어옵니다.
 * */
@Service
@RequiredArgsConstructor
public class MemberResolverImpl implements MemberResolver<String> {

    private final MemberService memberService;

    @Override
    public SecurityMember<String> resolveMember(final String id) {
        final Member member = memberService.getMember(id);
        return new SecurityMember<>(id,
                                    member.getName(),
                                    false,
                                    List.of(new SimpleGrantedAuthority("Member")));
    }
}
