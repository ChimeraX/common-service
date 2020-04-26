package org.chimerax.common.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.chimerax.common.exception.NoSuchKeyException;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 1:03 PM
 */

public class JWTServiceHelperFactoryImpl extends JWTServiceHelperFactory {

    private Key key = MacProvider.generateKey(SignatureAlgorithm.HS256);

    private JWTServiceHelper jwtServiceHelper = JWTServiceHelper.builder()
            .issuer(getIssuer())
            .signingKeyId(UUID.randomUUID().toString())
            .signingKey(key)
            .validity(getValidity())
            .build();

    @Override
    public JWTServiceHelper get(String signingKeyId) throws NoSuchKeyException {
        return jwtServiceHelper;
    }

    @Override
    public JWTServiceHelper getRandomHelper() {
        return jwtServiceHelper;
    }
}
