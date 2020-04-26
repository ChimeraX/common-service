package org.chimerax.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 26-Apr-20
 * Time: 12:30 AM
 */

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NoSuchKeyException extends RuntimeException {

    public NoSuchKeyException(final String secretId) {
        super("No such singing key: " + secretId);
    }
}
