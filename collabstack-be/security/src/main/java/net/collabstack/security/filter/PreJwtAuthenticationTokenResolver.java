package net.collabstack.security.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import net.collabstack.security.TokenInvalidException;

@Component
public class PreJwtAuthenticationTokenResolver {

    private static final Pattern bearerTokenPattern = Pattern.compile("[Bb]earer (.*)");

    public String resolve(final String authorizationValue) {
        if (authorizationValue.isBlank()) {
            throw new TokenInvalidException("Token이 유효하지 않습니다");
        }
        final Matcher matcher = bearerTokenPattern.matcher(authorizationValue);
        if (!matcher.matches()) {
            throw new TokenInvalidException("Bearer로 감싸지지 않았습니다.");
        }
        return matcher.group(1);
    }
}
