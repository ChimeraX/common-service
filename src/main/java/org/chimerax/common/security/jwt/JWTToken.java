package org.chimerax.common.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 24-Apr-20
 * Time: 9:25 PM
 */

@AllArgsConstructor
public class JWTToken implements Authentication {

    private UserDetails userDetails;
    private Details details;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public String getCredentials() {
        return userDetails.getUsername();
    }

    @Override
    public Details getDetails() {
        return details;
    }

    @Override
    public UserDetails getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException { }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }

    @AllArgsConstructor
    public static class Details {
        private HttpServletRequest request;

        public String getHeader(String name) {
            return request.getHeader(name);
        }

        public Cookie[] getCookies() {
            return request.getCookies();
        }
    }
}
