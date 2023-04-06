package com.education.repository.resolution;

import com.education.entity.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Выполняет роль DAO, доступ к данным и методам
 */
@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Long> {
    /**
     * Выборка резолюции по id и при этом она не архивирована
     */
    @Query("SELECT res from Resolution res where res.id =:id and res.archivedDate is null")
    Resolution findByIdAndArchivedDateIsNull(@Param("id") Long id);

    /**
     * Выборка всех резолюций не архивированных резолюций
     */
    @Query("SELECT res from Resolution res where res.id =:id and res.archivedDate is null")
    Collection<Resolution> findAllByArchivedDateIsNull(@Param("id") Collection<Long> id);

    /**
     * Перемещение резолюции в архив
     */
    @Modifying
    @Query(nativeQuery = true, value = "update resolution set archived_date = now() where id =:id and archived_date is null")
    void movesToArchive(@Param("id") Long id);
}
