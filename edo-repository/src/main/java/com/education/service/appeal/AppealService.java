package com.education.service.appeal;

import com.education.entity.Appeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Service в "edo-repository", служит для связи контроллера и репозитория
 */
public interface AppealService {

    /**
     * Сохранение обращения
     */
    Appeal save(Appeal appeal);

    /**
     * Удаление обращения
     */
    void delete(Long id);

    /**
     * Нахождение обращения по id
     */
    Appeal findById(Long id);

    /**
     * Нахождение всех обращений
     */
    Collection<Appeal> findAll();

    /**
     * Перенос в архив обращения
     */
    void moveToArchive(Long id);

    /**
     * Нахождение обращения по id не из архива
     */
    Appeal findByIdNotArchived(Long id);

    /**
     * Нахождение всех обращений не из архива
     */
    Collection<Appeal> findAllNotArchived();

    /**
     * Нахождение обращения по Questions id
     */
    Appeal findAppealByQuestionsId(Long id);
}