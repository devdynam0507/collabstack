package net.collabstack.app.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import net.collabstack.app.member.dto.MemberCreate;

import lombok.Data;

@Entity
@Data
public class Member {

    @Id
    private String email;

    private String imageUrl;

    private String company;

    private String githubUrl;

    @Column(nullable = false)
    private String name;

    public static Member from(final MemberCreate memberCreate) {
        final Member member = new Member();
        member.setEmail(memberCreate.getEmail());
        member.setName(memberCreate.getUsername());
        member.setImageUrl(memberCreate.getProfileImageUrl());
        member.setGithubUrl(memberCreate.getGithubUrl());
        member.setCompany(member.getCompany());
        return member;
    }
}
