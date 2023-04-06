package com.education.service.address;

import model.dto.AddressDto;


/**
 * Service в "edo-service", служит для связи контроллера и RestTemplate
 */
public interface AddressService {

    /**
     * Метод, который возвращает адрес по Id
     */
    String findById(long id);

    /**
     * Метод, который возвращает все адреса
     */
    String findAll();

    /**
     * Метод сохранения нового адреса в БД
     */
    void save(AddressDto addressDto);

    /**
     * Метод удаления адреса из БД
     */
    void delete(long id);

}
