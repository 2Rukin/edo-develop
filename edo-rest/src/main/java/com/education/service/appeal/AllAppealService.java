package com.education.service.appeal;

import model.dto.AllAppealDto;

import java.util.Collection;

public interface AllAppealService {

    /**
     * Метод получения пользователя по id
     */
    Collection<AllAppealDto> getAllAppeals(Long creatorId, int start, int end);
}
