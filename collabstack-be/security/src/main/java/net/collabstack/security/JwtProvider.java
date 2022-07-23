package net.collabstack.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

@Component
public class JwtProvider {

    private String secretKey;
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secretKey);
        jwtVerifier = JWT.require(algorithm).build();
    }

    @Autowired
    public void setSecretKey(@Value("${jwt.secretkey}") final String secretKey) {
        this.secretKey = secretKey;
    }

    public <T> String encrypt(final String claimKey, final T value, final Long expiredSecond) {
        if (value instanceof Map || value instanceof List) {
            throw new IllegalArgumentException("지원되지 않는 claim type 입니다.");
        }
        return JWT.create()
                  .withClaim(claimKey, String.valueOf(value))
                  .withExpiresAt(getExpiredDate(expiredSecond))
                  .sign(algorithm);
    }

    public <T> String encryptWithoutAlgorithm(
            final String claimKey, final T value, final Long expiredSecond) {
        if (value instanceof List || value instanceof Map) {
            throw new IllegalArgumentException("지원되지 않는 claim type 입니다.");
        }
        return JWT.create()
                .withClaim(claimKey, String.valueOf(value))
                .withExpiresAt(getExpiredDate(expiredSecond))
                .sign(Algorithm.none());
    }

    public <T, V> String encryptMapWithoutAlgorithm(
            final Map<String, V> claims, final Long expiredSecond) {
        final JWTCreator.Builder builder = JWT.create();
        for (Map.Entry<String, V> entry : claims.entrySet()) {
            builder.withClaim(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return builder.withExpiresAt(getExpiredDate(expiredSecond))
                .sign(Algorithm.none());
    }

    public <T> T decrypt(final String token, final String claimKey, final Class<? extends T > type) {
        if (Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)) {
            throw new IllegalArgumentException("지원되지 않는 claim type 입니다.");
        }
        return (T) jwtVerifier.verify(token)
                .getClaim(claimKey)
                .as(type);
    }

    public Map<String, Claim> decryptWithNoAlgorithm(final String token) {
        return JWT.decode(token)
                .getClaims();
    }

    private Date getExpiredDate(final long expiredSecond) {
        final Date now = new Date();
        final Date expired = new Date();
        expired.setTime(now.getTime() + expiredSecond);
        return expired;
    }
}
