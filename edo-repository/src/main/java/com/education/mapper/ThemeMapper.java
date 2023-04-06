package com.education.mapper;

import com.education.entity.Theme;
import model.dto.ThemeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ThemeMapper extends AbstractMapper<Theme, ThemeDto> {
    ThemeMapper THEME_MAPPER = Mappers.getMapper(ThemeMapper.class);

}
