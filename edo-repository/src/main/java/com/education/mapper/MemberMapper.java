package com.education.mapper;

import com.education.entity.Member;
import model.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации участника или подписанта листа согласования в ДТО и обратно
 */
@Mapper
public interface MemberMapper extends AbstractMapper<Member, MemberDto> {
    MemberMapper MEMBER_MAPPER = Mappers.getMapper(MemberMapper.class);
}
