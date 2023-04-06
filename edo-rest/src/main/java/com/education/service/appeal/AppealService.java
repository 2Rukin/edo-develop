package com.education.service.appeal;

import model.dto.AppealDto;

public interface AppealService {
    AppealDto save(AppealDto appealDto);

    /**
     * Метод отправки сообщения в архив
     */
    void moveToArchive(Long id);

    /**
     * Метод получения пользователя по id
     */
    AppealDto findById(Long id);
}
