package net.collabstack.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import net.collabstack.security.JwtProvider;
import net.collabstack.security.TokenInvalidException;
import net.collabstack.security.member.MemberResolver;
import net.collabstack.security.member.SecurityMember;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PreJwtAuthenticationFilter extends OncePerRequestFilter {

    private final String authenticationHeaderName = "Authorization";
    private final PreJwtAuthenticationTokenResolver tokenResolver;
    private final JwtProvider jwtProvider;
    private final MemberResolver<Long> memberResolver;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationValue = request.getHeader(authenticationHeaderName);
        if (!authorizationValue.isBlank()) {
            throw new TokenInvalidException("Token이 유효하지 않습니다");
        }
        final String token = tokenResolver.resolve(authorizationValue);
        final Long id = jwtProvider.decrypt(token, "id", Long.class);
        final SecurityMember<Long> member = memberResolver.resolveMember(id);

        SecurityContextHolder.getContext().setAuthentication(
                new PreJwtAuthenticationToken(id, member.getAuthorities(), member));
        filterChain.doFilter(request, response);
    }

}
