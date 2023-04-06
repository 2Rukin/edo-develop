package com.education.service.filePool;

import com.education.entity.FilePool;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Nadezhda Pupina
 * Интерфейс с методами для FilePool
 */
public interface FilePoolService {

    /**
     * save - сохраняет новый файл в бд
     */
    FilePool save(FilePool filePool);

    /**
     * delete - удаляет файл из бд
     */
    void delete(Long id);

    /**
     * findById - находит файл по id
     */
    FilePool findById(Long id);

    FilePool findByUuid(UUID uuid);

    /**
     * findAllById - выводит список всех файлов по id
     */
    Collection<FilePool> findAllById(Iterable<Long> ids);

    /**
     * findAllById - выводит список всех файлов
     */
    Collection<FilePool> findAll();

    /**
     * moveToArchive - заполняет архивную дату
     */
    void moveToArchive(Long id);

    /**
     * findByIdNotArchived - находит файл без архивной даты по id
     */
    FilePool findByIdAndArchivedDateNull(Long id);

    /**
     * findAllByIdNotArchived - находит все файлы без архивной даты по id
     */
    Collection<FilePool> findByIdInAndArchivedDateNull(Iterable<Long> ids);


}
