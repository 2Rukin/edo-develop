package com.education.repository.approval;

import com.education.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Служит для отправки запросов к БД.
 * Взаимодействует с сущностью Approval.
 */
@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Long> {

    /**
     * Ставит дату архивации для листа согласования
     */
    @Modifying
    @Query(nativeQuery = true, value = "update approval set archived_date = now() where id =:id and archived_date is null")
    void moveToArchive(@Param("id") Long id);

    /**
     * Достаёт все листы согласования без даты архивации
     */
    Collection<Approval> findAllByArchivedDateIsNull();

    /**
     * Достаёт лист согласования без даты архивации по индексу
     */
    Approval findByIdAndArchivedDateNull(Long id);

    /**
     * Достаёт все листы согласования без даты архивации по указанным индексам
     */
    Collection<Approval> findByIdInAndArchivedDateNull(Iterable<Long> ids);
}
