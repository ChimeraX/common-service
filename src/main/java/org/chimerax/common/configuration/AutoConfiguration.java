package org.chimerax.common.configuration;

import org.chimerax.common.security.jwt.JWTFilter;
import org.chimerax.common.security.jwt.JWTServiceHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 25-Apr-20
 * Time: 1:13 PM
 */

@Configuration
public class AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JWTServiceHelper jwtServiceHelper() {
        return new JWTServiceHelper();
    }

    @Bean
    @ConditionalOnMissingBean
    public JWTFilter jwtFilter(final JWTServiceHelper jwtServiceHelper) {
        return new JWTFilter(jwtServiceHelper);
    }

}
