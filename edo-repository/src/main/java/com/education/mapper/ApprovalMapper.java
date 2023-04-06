package com.education.mapper;

import com.education.entity.Approval;
import model.dto.ApprovalDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации листа согласования в ДТО и обратно
 */
@Mapper
public interface ApprovalMapper extends AbstractMapper<Approval, ApprovalDto> {
    ApprovalMapper APPROVAL_MAPPER = Mappers.getMapper(ApprovalMapper.class);
}
