package com.education.mapper;

import com.education.entity.Nomenclature;
import model.dto.NomenclatureDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации департамента в ДТО и обратно
 */

@Mapper
public interface NomenclatureMapper extends AbstractMapper<Nomenclature, NomenclatureDto> {
    NomenclatureMapper NOMENCLATURE_MAPPER = Mappers.getMapper(NomenclatureMapper.class);

}
