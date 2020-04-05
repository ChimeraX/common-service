package org.chimerax.commonservice.api.entity;

import java.util.Date;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:52 AM
 */
public interface ChimeraXEntity<ID> {
    ID getId();

    void setId(ID id);

    Date getModified();

    void setModified(Date modified);

    Date getCreated();

    void setCreated(Date created);
}
