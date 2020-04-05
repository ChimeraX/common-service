package org.chimerax.commonservice.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:22 AM
 */
public interface ChimeraXSearchService<ID, Entity, SearchDTO, DetailDTO> {

    List<SearchDTO> findAll();

    Page<SearchDTO> findAll(final Specification<Entity> spec, final Pageable pageable);

    Page<SearchDTO> findAll(final Pageable pageable);

    Optional<DetailDTO> findById(final ID id);

    boolean existsById(final ID id);
}
