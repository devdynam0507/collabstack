package net.collabstack.security.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PreJwtAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String authenticationHeaderName = "Authorization";
    private final PreJwtAuthenticationTokenResolver tokenResolver;

    @Override
    protected Object getPreAuthenticatedPrincipal(final HttpServletRequest request) {
        final String authorizationValue = extractAuthorizationHeader(request);
        if (authorizationValue == null) {
            return null;
        }
        return tokenResolver.resolve(authorizationValue);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(final HttpServletRequest request) {
        final String authorizationValue = extractAuthorizationHeader(request);
        if (authorizationValue == null) {
            return null;
        }
        return tokenResolver.resolve(authorizationValue);
    }

    private String extractAuthorizationHeader(final HttpServletRequest request) {
        final String authorizationValue = request.getHeader(authenticationHeaderName);
        return authorizationValue;
    }
}
