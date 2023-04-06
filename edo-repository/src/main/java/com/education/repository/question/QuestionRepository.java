package com.education.repository.question;

import com.education.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Nadezhda Pupina
 * JPA DATA Repository for edo-repository service
 */

public interface QuestionRepository extends JpaRepository<Question, Long> {
    /**
     * Запрос на установку даты в поле archived_date
     */
    @Modifying
    @Query(nativeQuery = true, value = "update question set archived_date = now() where id =:id and archived_date is null")
    void moveToArchive(@Param("id") Long id);

    /**
     * Запрос на поиск объекта вне архива по id
     */
    Question findByIdAndArchivedDateNull(@Param("id") Long id);

    /**
     * Запрос на поиск объектов вне архива по id
     */
    @Query("select u from Question u  where u.archivedDate is null and u.id in :idList")
    List<Question> findByIdInAndArchivedDateNull(@Param("idList") Iterable<Long> ids);

}
