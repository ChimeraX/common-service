package org.chimerax.common.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 26-Apr-20
 * Time: 12:42 AM
 */
public interface JWTService {

    UserDetails extractUser(final String token);

    String generateToken(final UserDetails userDetails,
                         final String signingKeyId,
                         final Map<String, Object> headers,
                         final Map<String, Object> extra);

    String generateToken(final UserDetails userDetails,
                         final Map<String, Object> headers,
                         final Map<String, Object> extra);
}