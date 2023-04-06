package com.education.mapper;

import com.education.entity.Question;
import model.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации департамента в ДТО и обратно
 */

@Mapper
public interface QuestionMapper extends AbstractMapper<Question, QuestionDto> {
    QuestionMapper QUESTION_MAPPER = Mappers.getMapper(QuestionMapper.class);

}