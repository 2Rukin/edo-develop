package com.education.service.address.impl;

import com.education.service.address.AddressService;
import lombok.AllArgsConstructor;
import model.dto.AddressDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.education.util.URIBuilderUtil.buildURI;
import static model.constant.Constant.*;

/**
 * Service в "edo-service", служит для связи контроллера и RestTemplate
 */
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {



    /**
     * Поле "restTemplate" нужно для вызова RestTemplate,
     * который нужен для совершения CRUD-операции по заданному URL
     */
    private final RestTemplate restTemplate;


    /**
     * Метод, который возвращает адрес по Id
     */
    public String findById(long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, ADDRESS_URL).setPath("/").setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), String.class);
    }

    /**
     * Метод, который возвращает все адреса
     */
    public String findAll() {
        var builder = buildURI(EDO_REPOSITORY_NAME, ADDRESS_URL).setPath("/all");
        return restTemplate.getForObject(builder.toString(), String.class);
    }

    /**
     * Метод сохранения нового адреса в БД
     */
    public void save(AddressDto addressDto) {
        var builder = buildURI(EDO_REPOSITORY_NAME, ADDRESS_URL).setPath("/");
        restTemplate.postForObject(builder.toString(), addressDto, AddressDto.class);
    }

    /**
     * Метод удаления адреса из БД
     */
    public void delete(long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, ADDRESS_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        restTemplate.delete(builder.toString());
    }

}
