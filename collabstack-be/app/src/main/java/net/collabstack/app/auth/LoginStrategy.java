package net.collabstack.app.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.collabstack.app.auth.type.AuthenticationProvider;

@Component
public class LoginStrategy {

    private final Map<AuthenticationProvider, LoginResolver> loginResolvers = new HashMap<>();

    @Autowired
    public LoginStrategy(final Set<LoginResolver> resolvers) {
        createStrategies(resolvers);
    }

    public LoginResolver strategy(final AuthenticationProvider provider) {
        return loginResolvers.get(provider);
    }

    private void createStrategies(final Set<LoginResolver> resolvers) {
        for (final LoginResolver resolver : resolvers) {
            loginResolvers.put(resolver.getProvider(), resolver);
        }
    }
}
