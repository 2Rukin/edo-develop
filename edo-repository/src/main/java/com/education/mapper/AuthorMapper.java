package com.education.mapper;

import com.education.entity.Author;
import model.dto.AuthorDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации департамента в ДТО и обратно
 */

@Mapper
public interface AuthorMapper extends AbstractMapper<Author, AuthorDto> {
    AuthorMapper AUTHOR_MAPPER = Mappers.getMapper(AuthorMapper.class);
}
