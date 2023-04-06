package com.education.repository.filePool;

import com.education.entity.FilePool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * @author Nadezhda Pupina
 * Наследует Jpa repository, реализует необходимые методы
 */
@Repository
public interface FilePoolRepository extends JpaRepository<FilePool, Long> {
    /**
     * Возвращает по id файл вне архива
     * @return
     */
    FilePool findByIdAndArchivedDateNull(Long id);
    /**
     * Возвращает по id все файлы вне архива
     * @return
     */
    List<FilePool> findByIdInAndArchivedDateNull(Iterable<Long> ids);

    /**
     * При архивации файла добавляет дату архивации
     * @return
     */
    @Modifying
    @Query(nativeQuery = true, value = "update file_pool set archived_date = now() where id =:id and archived_date is null")
    void moveToArchived(@Param(value = "id") Long id);


    FilePool getFilePoolByStorageFileId(UUID uuid);
}

