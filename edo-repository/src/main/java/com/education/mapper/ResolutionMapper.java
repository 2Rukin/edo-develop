package com.education.mapper;

import com.education.entity.Resolution;
import model.dto.ResolutionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации департамента в ДТО и обратно
 */

@Mapper(componentModel = "spring")
public interface ResolutionMapper extends AbstractMapper<Resolution, ResolutionDto> {
    ResolutionMapper RESOLUTION_MAPPER = Mappers.getMapper(ResolutionMapper.class);
}
