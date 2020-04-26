package org.chimerax.common.configuration;

import org.chimerax.common.exception.NoSuchKeyException;
import org.chimerax.common.security.jwt.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 25-Apr-20
 * Time: 1:13 PM
 */

@Configuration
public class AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(JWTServiceHelperFactory.class)
    public JWTServiceHelperFactory jwtServiceHelperFactory() {
        return new JWTServiceHelperFactoryImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(JWTServiceHelperFactory.class)
    public JWTService jwtService(final JWTServiceHelperFactory factory) {
        return new JWTServiceImpl(factory);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(JWTService.class)
    public JWTFilter jwtFilter(final JWTService jwtService) {
        return new JWTFilter(jwtService);
    }


}
