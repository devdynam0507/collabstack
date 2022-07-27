package net.collabstack.security.filter;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import net.collabstack.security.JwtProvider;
import net.collabstack.security.TokenInvalidException;
import net.collabstack.security.member.MemberResolver;
import net.collabstack.security.member.SecurityMember;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PreJwtAuthenticationProvider implements AuthenticationProvider {

    private final static Collection<SimpleGrantedAuthority> anonymousRoles =
            List.of(new SimpleGrantedAuthority("Anonymous"));

    private final JwtProvider jwtProvider;
    private final MemberResolver<String> memberResolver;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        if (supports(authentication.getClass())) {
            final String token = authentication.getPrincipal().toString();
            if (token == null) {
                final SecurityMember<Void> anonymous =
                        new SecurityMember<>(
                                null, "anonymous", anonymousRoles);
                return new PreAuthenticatedAuthenticationToken(anonymousRoles, "", anonymousRoles);
            }
            final String id = jwtProvider.decrypt(token, "id", String.class);
            final SecurityMember<String> member = memberResolver.resolveMember(id);
            return new PreAuthenticatedAuthenticationToken(
                    member, "", List.of(new SimpleGrantedAuthority("Member")));
        }
        throw new TokenInvalidException("토큰을 찾을 수 없습니다.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
