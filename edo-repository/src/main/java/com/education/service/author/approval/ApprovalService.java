package com.education.service.author.approval;

import com.education.entity.Approval;

import java.util.Collection;

public interface ApprovalService {

    /**
     * Сохраняет новый лист согласования
     */
    Approval save(Approval approval);

    /**
     * Поиск листа согласования по индексу
     */
    Approval findById(Long id);

    /**
     * Поиск всех листов согласования
     */
    Collection<Approval> findAll();

    /**
     * Поиск всех листов согласования по индексам
     */
    Collection<Approval> findAllById(Iterable<Long> ids);

    /**
     * Удалить лист согласования по индексу
     */
    void delete(Long id);

    /**
     * Перенести в архив лист согласования
     */
    void moveToArchive(Long id);

    /**
     * Поиск листа согласования не из архива по индексу
     */
    Approval findByIdNotArchived(Long id);

    /**
     * Поиск всех листов согласования не из архива
     */
    Collection<Approval> findAllNotArchived();

    /**
     * Поиск всех листов согласования не из архива по индексам
     */
    Collection<Approval> findByIdInAndArchivedDateNull(Iterable<Long> ids);

    /**
     * Обновление листа согласования
     */
    Approval update(Approval approval);
}
