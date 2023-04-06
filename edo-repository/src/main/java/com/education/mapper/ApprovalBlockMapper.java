package com.education.mapper;

import com.education.entity.ApprovalBlock;
import model.dto.ApprovalBlockDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации блока листа согласования в ДТО и обратно
 */
@Mapper
public interface ApprovalBlockMapper extends AbstractMapper<ApprovalBlock, ApprovalBlockDto> {
    ApprovalBlockMapper APPROVAL_BLOCK_MAPPER = Mappers.getMapper(ApprovalBlockMapper.class);
}
