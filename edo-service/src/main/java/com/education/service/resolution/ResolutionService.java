package com.education.service.resolution;

import model.dto.ResolutionDto;

import java.util.Collection;

/**
 * Интерфейс для связи edo-service c edo-repository
 */
public interface ResolutionService {
    /**
     * Сохраняет новую резолюцию
     */
    public ResolutionDto save(ResolutionDto resolutionDto);

    /**
     * Архивирует резолюцию
     */
    public void moveToArchive(Long id);

    /**
     * Поиск резолюции по id
     */
    public ResolutionDto findById(Long id);

    /**
     * Показать все резолюции
     */
    public Collection<ResolutionDto> findAllById(Long id);

    /**
     * Поиск исключительно не архивированной резолюции по id
     */
    public ResolutionDto findByIdNotArchived(Long id);

    /**
     * Показать все актуальные, не помещенные в архив резолюции
     */
    public Collection<ResolutionDto> findAllByIdNotArchived(Long id);

}
