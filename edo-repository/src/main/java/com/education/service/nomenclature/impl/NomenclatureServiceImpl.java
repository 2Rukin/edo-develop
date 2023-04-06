package com.education.service.nomenclature.impl;

import com.education.entity.Nomenclature;
import com.education.repository.nomenclature.NomenclatureRepository;
import com.education.service.nomenclature.NomenclatureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


/** Implementation of edo-service service */
@AllArgsConstructor
@Service
public class NomenclatureServiceImpl implements NomenclatureService {

    private NomenclatureRepository repository;

    /**
     * Method saves new entity in DB by accepting json-body object
     */
    @Transactional(rollbackFor = Exception.class)
    public Nomenclature save(Nomenclature nomenclature) {
        return repository.saveAndFlush(nomenclature);
    }

    /**
     * the Method fills in the field with the value and set date
     */
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        repository.moveToArchive(id);
    }

    /**
     * Method searches for an entity of Nomenclature
     */
    public Nomenclature findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Method searches for set of entities of Nomenclature by their ids: "?id = 1,2,3,4,5,6... "
     */
    public List<Nomenclature> findAllById(Collection<Long> nomenclature) {
        return repository.findAllById(nomenclature);
    }

    /**
     * Method searches for an entity of Nomenclature that archiveDate fild is null
     */
    @Override
    public Optional<Nomenclature> findByIdNotArchived(Long id) {
        return repository.findByIdNotArchived(id);
    }

    /**
     * Method searches for set of entities of Nomenclature that archiveDate filds are null
     */
    @Override
    public List<Nomenclature> findAllByIdNotArchived(Collection<Long> idList) {
        return repository.findAllByIdNotArchived(idList);
    }

    /**
     * Delete entity of Nomenclature by its id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
