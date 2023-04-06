package com.education.service.theme;

import com.education.entity.Theme;

import java.util.Collection;

/**
 *@author AlexeySpiridonov
 */

public interface ThemeService {

    /**
     * save - сохраняет новый файл в бд
     */
    void save(Theme theme);

    /**
     * delete - удаляет файл из бд
     */
    void delete(Long id);

    /**
     * findById - находит файл по id
     */
    Theme findById(Long id);

    /**
     * findAllById - выводит список всех файлов по id
     */
    Collection<Theme> findAllById(Iterable<Long> ids);

    /**
     * findAll - возвращает все темы
     */
    Collection<Theme> findAll();

    /**
     * moveToArchive - заполняет архивную дату
     */
    void moveToArchive(Long id);

    /**
     * findByIdNotArchived - находит файл без архивной даты по id
     */
    Theme findByIdAndArchivedDateNull(Long id);

    /**
     * findAllByIdNotArchived - находит все файлы без архивной даты по id
     */
    Collection<Theme> findByIdInAndArchivedDateNull(Iterable<Long> ids);
}
