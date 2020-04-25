package org.chimerax.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.val;
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

@RequiredArgsConstructor
public class JWTServiceHelper {

    private static final String AUTHORITIES = "auth";

    @Value("${jwt.token.secret:signingKey}")
    private String signingKey;

    @Value("${jwt.token.validity:1800}")
    private long validity;

    @Value("${jwt.token.issuer:}")
    private String issuer;

    public UserDetails extractUser(final String token) {
        return extractUser(token, true);
    }

    public UserDetails extractUser(final String token, final boolean secure) {
        final Claims claims = extractAllClaims(token, secure);
        final String username = claims.getSubject();
        final List<String> authorityKeys = claims.get(AUTHORITIES, List.class);
        final List<GrantedAuthority> authorities = authorityKeys.stream()
                .map(GrantedAuthorityImpl::new)
                .collect(Collectors.toList());
        return UserDetailsImpl.builder()
                .username(username)
                .authorities(authorities)
                .build();
    }

    private Claims extractAllClaims(final String token, final boolean secure) {
        if (secure) {
            return Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token)
                    .getBody();
        } else {
            return Jwts.parser()
                    .parseClaimsJwt(transformJWSToJWT(token))
                    .getBody();
        }
    }

    private String transformJWSToJWT(final String token) {
        return token.substring(0, token.lastIndexOf(".") + 1);
    }

    public String generateToken(final UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    public String generateToken(final UserDetails userDetails, final Map<String, Object> extra) {
        final Instant now = Instant.now();
        final Instant expiration = now.plusSeconds(validity);
        val authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return Jwts.builder()
                .claim(AUTHORITIES, authorities)
                .addClaims(extra)
                .setSubject(userDetails.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }
}
