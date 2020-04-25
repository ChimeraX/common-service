package org.chimerax.common.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.util.Date;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 25-Apr-20
 * Time: 3:48 AM
 */

@Data
public class ChimeraXEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Date().getTime();
    }
}
