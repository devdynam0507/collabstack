package net.collabstack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import net.collabstack.security.filter.PreJwtAuthenticationFilter;
import net.collabstack.security.filter.PreJwtAuthenticationProvider;
import net.collabstack.security.filter.PreJwtAuthenticationTokenResolver;
import net.collabstack.security.member.MemberResolver;

@EnableWebSecurity
@Configuration
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private MemberResolver<Long> memberResolver;

    @Autowired
    private PreJwtAuthenticationTokenResolver tokenResolver;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource());
        http.csrf().disable();
        http.formLogin().disable();
        http.logout().disable();
        http.httpBasic().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/v1/login").permitAll()
            .and().authorizeRequests()
            .antMatchers(
                    "/favicon.ico",
                    "/h2-console/**",
                    "/hello",
                    "/error"
            ).hasAnyRole()
            .and()
            .authorizeRequests().anyRequest().hasRole("Member")
            .and()
            .addFilterAt(preJwtAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);
        return http.build();
    }

    @Bean
    PreJwtAuthenticationFilter preJwtAuthenticationFilter() {
        final PreJwtAuthenticationFilter filter = new PreJwtAuthenticationFilter(tokenResolver);
        filter.setAuthenticationManager(new ProviderManager(preJwtAuthenticationProvider()));
        return filter;
    }

    @Bean
    PreJwtAuthenticationProvider preJwtAuthenticationProvider() {
        return new PreJwtAuthenticationProvider(jwtProvider, memberResolver);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("https://collabstack.net");
        corsConfiguration.addAllowedOrigin("https://www.collabstack.net");
        corsConfiguration.addAllowedOrigin("http://localhost:8700");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
