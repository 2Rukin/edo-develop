package com.education.service.resolution.impl;

import com.education.service.resolution.ResolutionService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import model.constant.Constant;
import model.dto.ResolutionDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service в "edo-rest", служит для связи контроллера и RestTemplate
 */

@Service
@AllArgsConstructor
public class ResolutionServiceImpl implements ResolutionService {


    private final RestTemplate restTemplate;

    /**
     * Отправляет запрос в edo-service на сохранение ResolutionDTO
     */

    @Override
    public ResolutionDto save(ResolutionDto resolutionDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String uri = URIBuilderUtil.buildURI(Constant.EDO_SERVICE_NAME, "api/service/resolution/add").toString();

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(resolutionDto, headers), ResolutionDto.class).getBody();
    }

}
