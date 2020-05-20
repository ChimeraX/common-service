package org.chimerax.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 26-Apr-20
 * Time: 12:42 AM
 */

@AllArgsConstructor
public class JWTServiceImpl implements JWTService {

    private JWTServiceHelperFactory jwtServiceHelperFactory;
    private static final String SIGNING_KEY_ID = "kid";

    @Override
    public UserDetails extractJWSUser(final String token) {
        final String signingKeyId = extractSigningKeyId(token);
        return jwtServiceHelperFactory
                .get(signingKeyId)
                .extractJWSUser(token);
    }

    @Override
    public UserDetails extractJWTUser(final String token) {
        return JWTServiceHelper.extractJWTUser(token);
    }

    @Override
    public String generateToken(
            final UserDetails userDetails,
            final Map<String, Object> headers,
            final Map<String, Object> extra) {
        return jwtServiceHelperFactory
                .getRandomHelper()
                .generateToken(userDetails, headers, extra);
    }

    private String extractSigningKeyId(final String token) {
        return (String) extractHeader(token).get(SIGNING_KEY_ID);
    }


    private Header extractHeader(final String token) {
        return extractJWT(token).getHeader();
    }

    private Claims extractClaims(final String token) {
        return extractJWT(token).getBody();

    }

    private Jwt<Header, Claims> extractJWT(final String token) {
        return Jwts.parser()
                .parseClaimsJwt(transformJWSToJWT(token));

    }

    private String transformJWSToJWT(final String token) {
        return token.substring(0, token.lastIndexOf(".") + 1);
    }

}
