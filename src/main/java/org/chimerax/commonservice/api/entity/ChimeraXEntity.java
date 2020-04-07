package org.chimerax.commonservice.api.entity;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:52 AM
 */
public interface ChimeraXEntity<ID> {
    ID getId();

    void setId(ID id);
}
