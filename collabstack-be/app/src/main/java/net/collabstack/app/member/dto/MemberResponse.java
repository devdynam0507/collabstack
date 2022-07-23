package net.collabstack.app.member.dto;

import net.collabstack.app.member.domain.Member;

import lombok.Data;

@Data
public class MemberResponse {

    private String username;
    private String imageUrl;
    private String company;
    private String githubUrl;

    public static MemberResponse from(final Member member) {
        final MemberResponse memberResponse = new MemberResponse();
        memberResponse.setUsername(member.getName());
        memberResponse.setImageUrl(member.getImageUrl());
        memberResponse.setCompany(member.getCompany());
        memberResponse.setGithubUrl(member.getGithubUrl());
        return memberResponse;
    }
}
