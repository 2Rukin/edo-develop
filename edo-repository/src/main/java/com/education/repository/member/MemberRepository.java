package com.education.repository.member;

import com.education.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Служит для отправки запросов к БД.
 * Взаимодействует с сущностью Member.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
