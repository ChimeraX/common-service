package org.chimerax.commonservice.api.dto;

import org.chimerax.commonservice.api.entity.ChimeraXEntity;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:31 AM
 */
public interface ChimeraXDTO<ID> {

    ID getId();

    void setId(ID id);
}
