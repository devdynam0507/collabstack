package net.collabstack.security.member;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class SecurityMember<T> {

    private final T id;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
}
