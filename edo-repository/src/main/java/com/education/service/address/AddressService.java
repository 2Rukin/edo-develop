package com.education.service.address;

import com.education.entity.Address;

import java.util.Collection;

/**
 * Service в "edo-repository", служит для связи контроллера и репозитория
 */
public interface AddressService {

    /**
     * Метод сохранения нового адреса в БД
     */
    void save(Address address);

    /**
     * Метод удаления адреса из БД
     */
    void delete(Address address);

    /**
     * Метод, который возвращает адрес по Id
     */
    Address findById(Long id);

    /**
     * Метод, который возвращает адреса по их Id
     */
    Collection<Address> findAllById(Iterable<Long> ids);

    /**
     * Метод, который возвращает все адреса
     */
    Collection<Address> findAll();

    /**
     * Метод, который сохраняет коллекцию адресов
     */
    void saveCollection(Collection<Address> addresses);
}
