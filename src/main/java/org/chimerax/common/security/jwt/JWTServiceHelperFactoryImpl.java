package org.chimerax.common.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.chimerax.common.exception.NoSuchKeyException;

import javax.crypto.spec.SecretKeySpec;
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

    private JWTServiceHelper jwtServiceHelper = getDefaultServiceHelper();

    @Override
    public JWTServiceHelper get(String signingKeyId) throws NoSuchKeyException {
        return jwtServiceHelper;
    }

    @Override
    public JWTServiceHelper getRandomHelper() {
        return jwtServiceHelper;
    }

    private JWTServiceHelper getDefaultServiceHelper() {
        return JWTServiceHelper.builder()
                .issuer(getIssuer())
                .signingKey(getKey())
                .validity(getValidity())
                .build();
    }

    private Key getKey() {
        if (null == getSecret() || "".equals(getSecret())) {
            return MacProvider.generateKey(SignatureAlgorithm.HS256);
        } else {
            return new SecretKeySpec(getSecret().getBytes(), SignatureAlgorithm.HS256.getJcaName());
        }
    }
}
