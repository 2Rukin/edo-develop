package com.education.mapper;

import com.education.entity.Appeal;
import model.dto.AppealDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации департамента в ДТО и обратно
 */

@Mapper
public interface AppealMapper extends AbstractMapper<Appeal, AppealDto> {
    AppealMapper APPEAL_MAPPER = Mappers.getMapper(AppealMapper.class);

}
