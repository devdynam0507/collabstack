package net.collabstack.app.member.dto;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class MemberCreate {

    private String email;
    private String username;
    private String profileImageUrl;
    @Nullable
    private String githubUrl;
    private String company;

    public static MemberCreate from(final Function<MemberCreate, MemberCreate> supplier) {
        final MemberCreate memberCreate = new MemberCreate();
        return supplier.apply(memberCreate);
    }
}
