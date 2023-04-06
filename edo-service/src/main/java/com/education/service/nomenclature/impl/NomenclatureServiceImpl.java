package com.education.service.nomenclature.impl;


import com.education.service.nomenclature.NomenclatureService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.NomenclatureDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.education.util.URIBuilderUtil.buildURI;
import static model.constant.Constant.*;

@AllArgsConstructor
@Service
@Log4j2
public class NomenclatureServiceImpl implements NomenclatureService {

    private RestTemplate restTemplate;

    @Override
    public void save(NomenclatureDto nomenclatureDto) {
        var builder = buildURI(EDO_REPOSITORY_NAME, NOMENCLATURE_URL)
                .setPath("/add");
        restTemplate.postForObject(builder.toString(), nomenclatureDto, NomenclatureDto.class);
    }

    @Override
    public NomenclatureDto findById(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, NOMENCLATURE_URL)
                .setPath("/find/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), NomenclatureDto.class);
    }

    @Override
    public List<NomenclatureDto> findAllById(String ids) {
        var builder = buildURI(EDO_REPOSITORY_NAME, NOMENCLATURE_URL)
                .setPath("/allId?id=")
                .setPath(String.valueOf(ids));
        return restTemplate.getForObject(builder.toString(), List.class);
    }

    @Override
    public void deleteById(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, NOMENCLATURE_URL)
                .setPath("/delete/")
                .setPath(String.valueOf(id));
        restTemplate.delete(builder.toString());
    }

    @Override
    public void moveToArchive(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, NOMENCLATURE_URL)
                .setPath("/move/")
                .setPath(String.valueOf(id));
        restTemplate.postForObject(builder.toString(),null, NomenclatureDto.class);
    }

    @Override
    public NomenclatureDto findByIdNotArchived(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, NOMENCLATURE_URL)
                .setPath("/find_not_archived/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), NomenclatureDto.class);
    }

    @Override
    public List<NomenclatureDto> findAllByIdNotArchived(String ids) {
        var builder = buildURI(EDO_REPOSITORY_NAME, NOMENCLATURE_URL)
                .setPath("/find_not_archived_List?id=")
                .setPath(String.valueOf(ids));
        return restTemplate.getForObject(builder.toString(), List.class);
    }
}
