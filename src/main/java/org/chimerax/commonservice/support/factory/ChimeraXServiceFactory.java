package org.chimerax.commonservice.support.factory;

import lombok.AllArgsConstructor;
import org.chimerax.commonservice.annotations.Factory;
import org.chimerax.commonservice.api.service.ChimeraXService;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 11:58 AM
 */
@Factory
@AllArgsConstructor
public class ChimeraXServiceFactory {

    private ConfigurableApplicationContext context;

    public <T extends ChimeraXService<?, ?>> T createService(final Class<T> tClass) {

        return null;
    }



}
