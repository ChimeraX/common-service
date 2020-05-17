package org.chimerax.common.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.chimerax.common.exception.NoSuchKeyException;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 1:03 PM
 */

public class JWTServiceHelperFactoryImpl extends JWTServiceHelperFactory {

    private JWTServiceHelper jwtServiceHelper;

    @PostConstruct
    private void postConstruct() {
        jwtServiceHelper = JWTServiceHelper.builder()
                .issuer(getIssuer())
                .signingKeyId("")
                .signingKey(buildKey())
                .validity(getValidity())
                .build();
    }

    @Override
    public JWTServiceHelper get(String signingKeyId) throws NoSuchKeyException {
        return jwtServiceHelper;
    }

    @Override
    public JWTServiceHelper getRandomHelper() {
        return jwtServiceHelper;
    }

    private Key buildKey() {
        if ("".equals(getSecret())) {
            return MacProvider.generateKey(SignatureAlgorithm.HS256);
        } else {
            return new SecretKeySpec(getSecret().getBytes(), SignatureAlgorithm.HS256.getJcaName());
        }
    }
}
