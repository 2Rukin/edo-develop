package com.education.service.appeal;

import com.education.entity.Appeal;

import java.util.Collection;

/**
 * Service в "edo-repository", служит для связи контроллера и репозитория
 */
public interface AllAppealService {


    /**
     * Нахождение всех обращений c пагинцацией
     */
    Collection<Appeal> findAllEmployeeByIdWithPagination(Long creatorId, int first, int last);
}