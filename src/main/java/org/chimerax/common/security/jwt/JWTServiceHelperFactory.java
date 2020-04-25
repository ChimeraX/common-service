package org.chimerax.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.*;
import org.chimerax.common.exception.NoSuchSecretException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 1:03 PM
 */

@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class JWTServiceHelperFactory {

    private static final String AUTHORITIES = "auth";

    @Value("${jwt.token.secret:}")
    private String defaultSigningKey;

    @Value("${jwt.token.validity:1800}")
    private long validity;

    @Value("${jwt.token.issuer:}")
    private String issuer;

    @Setter(AccessLevel.PROTECTED)
    private JWTServiceHelper defaultJWTServiceHelper;

    @PostConstruct
    private void postConstruct() {
        final JWTServiceHelper jwtServiceHelper = JWTServiceHelper
                .builder()
                .issuer(issuer)
                .validity(validity)
                .signingKey(defaultSigningKey)
                .build();
        setDefaultJWTServiceHelper(jwtServiceHelper);
    }

    public abstract JWTServiceHelper get(final String signingKeyId) throws NoSuchSecretException;

}
