package org.chimerax.commonservice.api.service;

import org.chimerax.commonservice.api.exception.NotFoundException;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:22 AM
 */
public interface ChimeraXService<ID, DTO> {

    void save(final DTO dto);

    void update(final DTO dto) throws NotFoundException;

    void deleteById(final ID id) throws NotFoundException;
}
