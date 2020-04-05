package org.chimerax.commonservice.support;

import lombok.AllArgsConstructor;
import org.chimerax.commonservice.api.dto.ChimeraXDTO;
import org.chimerax.commonservice.api.entity.ChimeraXEntity;
import org.chimerax.commonservice.api.exception.NotFoundException;
import org.chimerax.commonservice.api.repository.ChimeraXRepository;
import org.chimerax.commonservice.api.service.ChimeraXConverter;
import org.chimerax.commonservice.api.service.ChimeraXSearchService;
import org.chimerax.commonservice.api.service.ChimeraXService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 05-Apr-20
 * Time: 10:22 AM
 */

@AllArgsConstructor
public abstract class ChimeraXServiceImpl<ID, Entity extends ChimeraXEntity<ID>, SearchDTO,
        DetailDTO, DTO extends ChimeraXDTO<ID, Entity>> implements ChimeraXService<ID, DTO>,
        ChimeraXSearchService<ID, Entity, SearchDTO, DetailDTO> {

    protected ChimeraXRepository<ID, Entity> repository;
    protected ChimeraXConverter<Entity, SearchDTO, DetailDTO, DTO> converter;

    @Override
    public List<SearchDTO> findAll() {
        return repository.findAll().stream().map(converter::convertToSearchDTO).collect(Collectors.toList());
    }

    @Override
    public Page<SearchDTO> findAll(final Specification<Entity> specification, final Pageable pageable) {
        return repository.findAll(specification, pageable).map(converter::convertToSearchDTO);
    }

    @Override
    public Page<SearchDTO> findAll(final Pageable pageable) {
        return repository.findAll(pageable).map(converter::convertToSearchDTO);
    }

    @Override
    public Optional<DetailDTO> findById(final ID id) {
        return repository.findById(id).map(converter::convertToDetailDTO);
    }

    @Override
    public boolean existsById(final ID id) {
        return repository.existsById(id);
    }

    @Override
    public void save(final DTO dto) {
        final Entity entity = converter.convertToEntity(dto);
        repository.save(entity);
    }

    @Override
    public void update(final DTO dto) throws NotFoundException {
        final Optional<Entity> optional = repository.findById(dto.getId());
        final Entity entity = optional.orElseThrow(NotFoundException::new);
        repository.save(converter.update(entity, dto));
    }

    @Override
    public void deleteById(final ID id) throws NotFoundException {
        final Optional<Entity> optional = repository.findById(id);
        final Entity entity = optional.orElseThrow(NotFoundException::new);
        repository.delete(entity);
    }
}
