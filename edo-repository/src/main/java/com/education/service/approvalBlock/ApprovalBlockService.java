package com.education.service.approvalBlock;

import com.education.entity.ApprovalBlock;

import java.util.Collection;

public interface ApprovalBlockService {

    /**
     * Сохраняет новый блок листа согласования
     */
    ApprovalBlock save(ApprovalBlock approvalBlock);

    /**
     * Поиск блока листа согласования по индексу
     */
    ApprovalBlock findById(Long id);

    /**
     * Поиск всех блоков листа согласования
     */
    Collection<ApprovalBlock> findAll();

    /**
     * Поиск всех блоков листа согласования по индексам
     */
    Collection<ApprovalBlock> findAllById(Iterable<Long> ids);

    /**
     * Удалить блок листа согласования по индексу
     */
    void delete(Long id);

    /**
     * Сохраняет новый блок листа согласования со ссылкой на лист согласования
     */
    ApprovalBlock saveWithLinkToApproval(ApprovalBlock approvalBlock, Long approvalId);
}
