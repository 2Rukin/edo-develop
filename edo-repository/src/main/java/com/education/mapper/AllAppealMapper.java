package com.education.mapper;

import com.education.entity.Appeal;
import model.dto.AllAppealDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации департамента в ДТО и обратно
 */

@Mapper
public interface AllAppealMapper extends AbstractMapper<Appeal, AllAppealDto> {
    AllAppealMapper ALL_APPEAL_MAPPER = Mappers.getMapper(AllAppealMapper.class);

}
