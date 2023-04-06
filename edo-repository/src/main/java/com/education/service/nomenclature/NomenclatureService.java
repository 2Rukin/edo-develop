package com.education.service.nomenclature;

import com.education.entity.Nomenclature;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *  Service from edo-repository module. For calling another service from edo-repository.
 */
public interface NomenclatureService {

    /**
     * Method saves new entity in DB by accepting json-body object
     */
    Nomenclature save(Nomenclature nomenclature);

    /**
     * the Method fills in the field with the value and set date
     */
    void moveToArchive(Long id);

    /**
     * Method searches for an entity of Nomenclature
     */
    Nomenclature findById(Long id);

    /**
     * Method searches for set of entities of Nomenclature by their ids: "?id = 1,2,3,4,5,6... "
     */
    Collection<Nomenclature> findAllById(Collection<Long> nomenclature);

    /**
     * Method searches for an entity of Nomenclature that archiveDate fild is null
     */
    Optional<Nomenclature> findByIdNotArchived(Long id);

    /**
     * Method searches for set of entities of Nomenclature that archiveDate filds are null
     */
    List<Nomenclature> findAllByIdNotArchived(Collection<Long> id);

    /**
     * Delete entity of Nomenclature by its id
     */
    void deleteById(Long id);

}
