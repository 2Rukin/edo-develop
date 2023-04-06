package com.education.service.author.approval.impl;

import com.education.entity.Approval;
import com.education.repository.appeal.AppealRepository;
import com.education.repository.approval.ApprovalRepository;
import com.education.repository.member.MemberRepository;
import com.education.service.author.approval.ApprovalService;
import com.education.service.approvalBlock.ApprovalBlockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Сервис-слой для связи контроллера и репозитория сущности Approval
 */
@Service
@AllArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final ApprovalBlockService approvalBlockService;
    private final MemberRepository memberRepository;
    private final AppealRepository appealRepository;

    /**
     * Метод принимает сущность Approval и сохраняет её в БД
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Approval save(Approval approval) {
        return approvalRepository.save(approval);
    }

    /**
     * Метод принимает индекс и ищет сущность Approval с таким индексом в БД.
     * Возвращает найденную сущность.
     */
    @Transactional(readOnly = true)
    @Override
    public Approval findById(Long id) {
        return approvalRepository.findById(id).orElse(null);
    }

    /**
     * Метод возвращает все сущности Approval, которые хранятся в БД
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<Approval> findAll() {
        return approvalRepository.findAll();
    }

    /**
     * Метод принимает индексы и ищет сущности Approval с такими индексами в БД.
     * Возвращает найденные сущности.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<Approval> findAllById(Iterable<Long> ids) {
        return approvalRepository.findAllById(ids);
    }

    /**
     * Метод принимает индекс и удаляет сущность Approval с таким индексом из БД
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        Approval approval = findById(id);
        if (approval != null) {
            approval.getParticipantApprovalBlocks().forEach(approvalBlock -> approvalBlockService.delete(approvalBlock.getId()));
            approval.getSignatoryApprovalBlocks().forEach(approvalBlock -> approvalBlockService.delete(approvalBlock.getId()));
            memberRepository.deleteById(approval.getInitiator().getId());
        }
        approvalRepository.deleteById(id);
    }

    /**
     * Метод принимает индекс и добавляет сущности Approval,
     * с таким индексом, дату архивации (текущая дата).
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void moveToArchive(Long id) {
        approvalRepository.moveToArchive(id);
    }

    /**
     * Метод принимает индекс и ищет сущность Approval без даты архивации с таким индексом в БД.
     * Возвращает найденную сущность.
     */
    @Transactional(readOnly = true)
    @Override
    public Approval findByIdNotArchived(Long id) {
        return approvalRepository.findByIdAndArchivedDateNull(id);
    }

    /**
     * Метод возвращает все сущности Approval без даты архивации, которые хранятся в БД
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<Approval> findAllNotArchived() {
        return approvalRepository.findAllByArchivedDateIsNull();
    }

    /**
     * Метод принимает индексы и ищет сущности Approval без даты архивации с такими индексами в БД.
     * Возвращает найденные сущности.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<Approval> findByIdInAndArchivedDateNull(Iterable<Long> ids) {
        return approvalRepository.findByIdInAndArchivedDateNull(ids);
    }

    /**
     * Метод принимает сущность Approval и обновляет её в БД
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Approval update(Approval approval) {
        //Устанавливает статус у обращения "На рассмотрении", если он в статусе "новое"
        appealRepository.setStatusUnderConsideration(approval.getAppeal().getId());
        return approvalRepository.save(approval);
    }
}
