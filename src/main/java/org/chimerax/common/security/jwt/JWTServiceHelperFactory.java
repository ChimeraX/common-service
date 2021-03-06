package org.chimerax.common.security.jwt;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.chimerax.common.exception.NoSuchKeyException;
import org.springframework.beans.factory.annotation.Value;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 1:03 PM
 */

@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class JWTServiceHelperFactory {

    private static final String AUTHORITIES = "auth";

    @Value("${jwt.token.validity:1800}")
    private long validity;

    @Value("${jwt.token.issuer:}")
    private String issuer;

    @Value("${jwt.token.secret:}")
    private String secret;

    public abstract JWTServiceHelper get(final String signingKeyId) throws NoSuchKeyException;

    public abstract JWTServiceHelper getRandomHelper();
}
