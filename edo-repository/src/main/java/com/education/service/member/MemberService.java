package com.education.service.member;

import com.education.entity.Member;

import java.util.Collection;

public interface MemberService {

    /**
     * Сохраняет нового участника
     */
    Member save(Member member);

    /**
     * Поиск участника по индексу
     */
    Member findById(Long id);

    /**
     * Поиск всех участников
     */
    Collection<Member> findAll();

    /**
     * Поиск всех участников по индексам
     */
    Collection<Member> findAllById(Iterable<Long> ids);

    /**
     * Удалить участника по индексу
     */
    void delete(Long id);

    /**
     * Сохраняет нового участника cо ссылкой на блок согласования
     */
    Member saveWithLinkToApprovalBlock(Member member, Long approvalBlockId);
}
