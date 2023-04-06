package com.education.repository.nomenclature;

import com.education.entity.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

 /** JPA DATA Repository for edo-repository service */
@Repository
public interface NomenclatureRepository extends JpaRepository<Nomenclature,Long> {

    /** Query request for set a date to archived_date field */
    @Modifying
    @Query(nativeQuery = true, value = "update nomenclature set archived_date = now() where id =:id")
    void moveToArchive(@Param("id") Long id);

    /** Query request for searching not archived entity by id */
    @Query("select u from Nomenclature u where u.archivedDate is null and u.id =:id")
    Optional<Nomenclature> findByIdNotArchived(@Param("id") Long id);

    /** Query request for searching not archived entities by ids */
    @Query("select u from Nomenclature u where u.archivedDate is null and u.id in :idList")
    List<Nomenclature> findAllByIdNotArchived(@Param("idList") Collection<Long> idList);

}
