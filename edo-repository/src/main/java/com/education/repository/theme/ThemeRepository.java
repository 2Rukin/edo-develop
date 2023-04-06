package com.education.repository.theme;

import com.education.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author AlexeySpiridonov
 */

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    /**
     * Метод устанавливает дату архивации
     */
    @Modifying
    @Query(nativeQuery = true, value = "update theme set archived_date = now() where id =:id and archived_date is null")
    void moveToArchive(@Param("id") Long id);

    Theme findByIdAndArchivedDateNull(Long id);

    List<Theme> findByIdInAndArchivedDateNull(Iterable<Long> ids);
}
