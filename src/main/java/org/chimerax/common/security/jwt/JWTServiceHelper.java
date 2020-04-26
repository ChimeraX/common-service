package org.chimerax.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;
import java.security.Key;
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

@Builder
public class JWTServiceHelper {

    private static final String AUTHORITIES = "auth";
    private static final String SIGNING_KEY_ID = "kid";

    private String signingKeyId;

    private Key signingKey;

    private long validity;

    private String issuer;

    public UserDetails extractUser(final String token) {
        final Claims claims = extractAllClaims(token);
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

    public String generateToken(final UserDetails userDetails,
                                final Map<String, Object> headers,
                                final Map<String, Object> extra) {
        headers.put(SIGNING_KEY_ID, signingKeyId);

        final Instant now = Instant.now();
        final Instant expiration = now.plusSeconds(validity);

        val authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setHeader(headers)
                .claim(AUTHORITIES, authorities)
                .addClaims(extra)
                .setSubject(userDetails.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    private Claims extractAllClaims(final String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
