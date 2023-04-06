package com.education.repository.approvalBlock;

import com.education.entity.ApprovalBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Служит для отправки запросов к БД.
 * Взаимодействует с сущностью ApprovalBlock.
 */
@Repository
public interface ApprovalBlockRepository extends JpaRepository<ApprovalBlock, Long> {
}
