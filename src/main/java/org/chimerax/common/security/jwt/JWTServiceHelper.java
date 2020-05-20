package org.chimerax.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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

    public UserDetails extractJWSUser(final String token) {
        return extractUser(extractJWSClaims(token));
    }

    public static UserDetails extractJWTUser(String token) {
        return extractUser(extractJWTClaims(token));
    }

    private static UserDetails extractUser(final Claims claims) {
        final String username = claims.getSubject();
        final List<GrantedAuthority> authorities = extractAuthorities(claims);
        return UserDetailsImpl.builder()
                .username(username)
                .authorities(authorities)
                .build();
    }

    private static List<GrantedAuthority> extractAuthorities(final Claims claims) {
        final List<String> authorities = claims.get(AUTHORITIES, List.class);
        if (authorities == null) {
            return new ArrayList<>();
        } else {
            return authorities.stream()
                    .map(GrantedAuthorityImpl::new)
                    .collect(Collectors.toList());
        }
    }

    private List<String> extractAuthorities(final UserDetails userDetails) {
        val authorities = userDetails.getAuthorities();
        if (authorities == null) {
            return new ArrayList<>();
        } else {
            return authorities
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }
    }

    public String generateToken(final UserDetails userDetails,
                                final Map<String, Object> headers,
                                final Map<String, Object> extra) {
        if (signingKeyId != null && !"".equals(signingKeyId)) {
            headers.put(SIGNING_KEY_ID, signingKeyId);
        }

        final Instant now = Instant.now();
        final Instant expiration = now.plusSeconds(validity);

        val authorities = extractAuthorities(userDetails);

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

    private Claims extractJWSClaims(final String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private static Claims extractJWTClaims(final String token) {
        return Jwts.parser()
                .parseClaimsJwt(transformJWSToJWT(token))
                .getBody();

    }

    private static String transformJWSToJWT(final String token) {
        return token.substring(0, token.lastIndexOf(".") + 1);
    }

}
