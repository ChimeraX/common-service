package org.chimerax.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public UserDetails extractUser(final String token) {
        final String signingKeyId = extractSigningKeyId(token);
        return jwtServiceHelperFactory
                .get(signingKeyId)
                .extractUser(token);
    }

    @Override
    public String generateToken(
            final UserDetails userDetails,
            final Map<String, Object> headers,
            final Map<String, Object> extra) {
        return jwtServiceHelperFactory
                .getDefaultJWTServiceHelper()
                .generateToken(userDetails, headers, extra);
    }

    @Override
    public String generateToken(
            final UserDetails userDetails,
            final String signingKeyId,
            final Map<String, Object> headers,
            final Map<String, Object> extra) {
        headers.put(SIGNING_KEY_ID, signingKeyId);
        return jwtServiceHelperFactory
                .get(signingKeyId)
                .generateToken(userDetails, headers, extra);
    }

    private String extractSigningKeyId(final String token) {
        return (String) extractHeader(token).get(SIGNING_KEY_ID);
    }


    private Header extractHeader(final String token) {
        return Jwts.parser()
                .parseClaimsJwt(transformJWSToJWT(token))
                .getHeader();

    }

    private String transformJWSToJWT(final String token) {
        return token.substring(0, token.lastIndexOf(".") + 1);
    }

}
