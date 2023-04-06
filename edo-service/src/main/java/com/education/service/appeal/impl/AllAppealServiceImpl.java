package com.education.service.appeal.impl;

import com.education.service.appeal.AllAppealService;
import lombok.AllArgsConstructor;
import model.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import static com.education.util.URIBuilderUtil.buildURI;
import static model.constant.Constant.EDO_REPOSITORY_NAME;

/**
 * Сервис-слой для Appeal
 */
@Service
@AllArgsConstructor
public class AllAppealServiceImpl implements AllAppealService {

    private final RestTemplate restTemplate;
    private static final String URL = "/api/repository/allAppeals";


    /**
     * Получение всех обращений
     */

    @Override
    public Collection<AllAppealDto> getAllAppeals(Long creatorId, int start, int end) {
        var builder = buildURI(EDO_REPOSITORY_NAME, URL)
                .addParameter("creatorId", String.valueOf(creatorId))
                .addParameter("start", String.valueOf(start))
                .addParameter("end", String.valueOf(end)).toString();
        return restTemplate.getForObject(builder, List.class);
    }
}
