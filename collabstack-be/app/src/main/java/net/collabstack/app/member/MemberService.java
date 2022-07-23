package net.collabstack.app.member;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.collabstack.app.member.domain.Member;
import net.collabstack.app.member.domain.MemberRepository;
import net.collabstack.app.member.dto.MemberCreate;
import net.collabstack.app.member.exception.MemberNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member getMember(final String id) {
        final Optional<Member> memberOptional = memberRepository.findById(id);
        return memberOptional.orElseThrow(() -> new MemberNotFoundException(id + "를 찾을 수 없습니다", id));
    }

    @Transactional
    public Member createMember(final MemberCreate memberCreate) {
        final Optional<Member> memberOptional = memberRepository.findById(memberCreate.getEmail());
        final Member newMember = Member.from(memberCreate);
        return memberOptional.orElseGet(() -> memberRepository.save(newMember));
    }
}
