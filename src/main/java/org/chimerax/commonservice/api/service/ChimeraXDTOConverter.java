package org.chimerax.commonservice.api.service;

import org.chimerax.commonservice.api.dto.ChimeraXDTO;
import org.chimerax.commonservice.api.entity.ChimeraXEntity;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:30 AM
 */
public interface ChimeraXDTOConverter<ID, Entity extends ChimeraXEntity<ID>, DTO extends ChimeraXDTO<ID, Entity>> {

    DTO convert(final Entity entity);
}
