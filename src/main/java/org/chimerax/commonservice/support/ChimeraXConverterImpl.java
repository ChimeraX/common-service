package org.chimerax.commonservice.support;

import org.chimerax.commonservice.api.dto.ChimeraXDTO;
import org.chimerax.commonservice.api.entity.ChimeraXEntity;
import org.chimerax.commonservice.api.service.ChimeraXConverter;
import org.chimerax.commonservice.api.service.ChimeraXDTOConverter;
import org.chimerax.commonservice.api.service.ChimeraXEntityConverter;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:30 AM
 */

public abstract class ChimeraXConverterImpl<
        ID,
        Entity extends ChimeraXEntity<ID>,
        SearchDTO extends ChimeraXDTO<ID, Entity>,
        DetailDTO extends ChimeraXDTO<ID, Entity>,
        DTO extends ChimeraXDTO<ID, Entity>>  implements ChimeraXConverter<Entity, SearchDTO, DetailDTO, DTO> {

    protected ChimeraXDTOConverter<ID, Entity, SearchDTO> searchDTOConverter;
    protected ChimeraXDTOConverter<ID, Entity, DetailDTO> detailDTOConverter;
    protected ChimeraXEntityConverter<ID, Entity, DTO> dtoConverter;

    @Override
    public SearchDTO convertToSearchDTO(final Entity entity) {
        return searchDTOConverter.convert(entity);
    }

    @Override
    public DetailDTO convertToDetailDTO(final Entity entity) {
        return detailDTOConverter.convert(entity);
    }

    @Override
    public Entity convertToEntity(final DTO dto) {
        return dtoConverter.convert(dto);
    }

    @Override
    public Entity update(final Entity entity, final DTO dto) {
        return dtoConverter.update(entity, dto);
    }
}
