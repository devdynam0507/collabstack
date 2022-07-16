package net.collabstack.security.filter;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PreJwtAuthenticationToken extends AbstractAuthenticationToken {

    private Long id;
    private UserDetails user;

    public PreJwtAuthenticationToken(final Long id,
                                     final Collection<? extends GrantedAuthority> authorities,
                                     final UserDetails user) {
        super(authorities);
        this.id = id;
        this.user = user;
    }

    @Override
    public UserDetails getPrincipal() {
        return user;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public Long getId() {
        return id;
    }
}
