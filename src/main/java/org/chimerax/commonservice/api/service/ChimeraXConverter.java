package org.chimerax.commonservice.api.service;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:30 AM
 */

public interface ChimeraXConverter<
        Entity,
        SearchDTO,
        DetailDTO,
        DTO> {

    SearchDTO convertToSearchDTO(final Entity entity);

    DetailDTO convertToDetailDTO(final Entity entity);

    Entity convertToEntity(final DTO dto);

    Entity update(final Entity entity, final DTO dto);
}
