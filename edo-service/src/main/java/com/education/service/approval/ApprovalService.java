package com.education.service.approval;

import model.dto.ApprovalDto;

import java.util.Collection;

public interface ApprovalService {
    /**
     * Сохраняет новый лист согласования
     */
    ApprovalDto save(ApprovalDto approvalDto);

    /**
     * Поиск листа согласования по индексу
     */
    ApprovalDto findById(Long id);

    /**
     * Поиск всех листов согласования
     */
    Collection<ApprovalDto> findAll();

    /**
     * Поиск всех листов согласования по индексам
     */
    Collection<ApprovalDto> findAllById(Iterable<Long> ids);

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
    ApprovalDto findByIdNotArchived(Long id);

    /**
     * Поиск всех листов согласования не из архива
     */
    Collection<ApprovalDto> findAllNotArchived();

    /**
     * Поиск всех листов согласования не из архива по индексам
     */
    Collection<ApprovalDto> findByIdInAndArchivedDateNull(Iterable<Long> ids);

    /**
     * Обновление листа согласования
     */
    ApprovalDto update(ApprovalDto approvalDto);

    /**
     * Направление листа согласования
     */
    void sendForApproval(Long id);
}
