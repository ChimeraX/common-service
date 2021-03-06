package org.chimerax.common.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 21-Apr-20
 * Time: 12:46 PM
 */

@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final int TOKEN_STARTING_POSITION = "Bearer ".length();

    private JWTService jwtService;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header != null) {
            final String token = header.substring(TOKEN_STARTING_POSITION);

            final UserDetails userDetails = jwtService.extractJWSUser(token);

            final JWTToken.Details details = new JWTToken.Details(request);

            SecurityContextHolder.getContext().setAuthentication(new JWTToken(userDetails, details));
        }

        filterChain.doFilter(request, response);
    }

}
