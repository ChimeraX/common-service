package org.chimerax.common.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:22 AM
 */

@NoRepositoryBean
public interface ChimeraXRepository<ID, Entity> extends JpaRepositoryImplementation<Entity, ID> {
}
