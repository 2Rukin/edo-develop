package com.education.service.appeal;

import model.dto.AllAppealDto;

import java.util.Collection;


/**
 * Service в "edo-service", служит для связи контроллера и RestTemplate
 */
public interface AllAppealService {

    /**
     * Получение всех обращений
     */
    Collection<AllAppealDto> getAllAppeals(Long creatorId, int start, int end);

}
