package com.education.service.appeal.impl;

import com.education.entity.Appeal;
import com.education.repository.appeal.AppealRepository;
import com.education.service.appeal.AppealService;
import lombok.AllArgsConstructor;
import model.enum_.Status;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Сервис-слой для сущности Appeal
 */
@Service
@AllArgsConstructor
public class AppealServiceImpl implements AppealService {

    private AppealRepository appealRepository;

    /**
     * Сохранение нового сообщения
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Appeal save(Appeal appeal) {
        return appealRepository.saveAndFlush(appeal);
    }

    /**
     * Удаление обращения
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        appealRepository.deleteById(id);
    }

    /**
     * Нахождение обращения по id
     */
    @Override
    @Transactional(readOnly = true)
    public Appeal findById(Long id) {
        return appealRepository.findById(id).orElse(null);
    }

    /**
     * Нахождение всех обращений
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Appeal> findAll() {
        return appealRepository.findAll();
    }

    /**
     * Перенос в архив обращения и изменение статуса на (ARCHIVE)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        appealRepository.findByIdAndArchivedDateIsNull(id).ifPresent(entity -> {
            entity.setAppealsStatus(Status.ARCHIVE);
            entity.setArchivedDate(ZonedDateTime.now());
        });

    }

    /**
     * Нахождение обращения по id не из архива
     */
    @Override
    @Transactional(readOnly = true)
    public Appeal findByIdNotArchived(Long id) {
        return appealRepository.findByIdNotArchived(id).orElse(null);
    }

    /**
     * Нахождение всех обращений не из архива
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Appeal> findAllNotArchived() {
        return appealRepository.findAllNotArchived();
    }

    /**
     * Нахождение обращения по Questions id
     */
    @Override
    @Transactional(readOnly = true)
    public Appeal findAppealByQuestionsId(Long id) {

        return appealRepository.findAppealByQuestionsId(id).orElseThrow(() -> new NoSuchElementException("Ошибка при связывании объектов"));

    }

}