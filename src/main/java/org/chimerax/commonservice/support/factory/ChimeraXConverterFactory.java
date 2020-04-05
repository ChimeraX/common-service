package org.chimerax.commonservice.support.factory;

import org.chimerax.commonservice.annotations.Factory;
import org.chimerax.commonservice.api.service.ChimeraXDTOConverter;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 12:25 PM
 */

@Factory
public class ChimeraXConverterFactory {

    public <T extends ChimeraXDTOConverter<?, ?, ?>> T createDTOConverter(final Class<T> tClass) {

        return null;
    }
}
