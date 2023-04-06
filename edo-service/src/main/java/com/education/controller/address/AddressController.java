package com.education.controller.address;

import com.education.service.address.AddressService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Rest-контроллер в "edo-service", служит для отправки запросов
 * от клиента(edo-service) к другому микросервису(edo-repository) используя RestTemplate
 * и для получения данных в ответ.
 * "@Log4j2" нужна для создания логов, для удобной отладки программы
 */
@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/api/service/address")
public class AddressController {

    /**
     * Поле "addressService" нужно для вызова Service-слоя (edo-service),
     * который нужен для связи с RestTemplate (edo-service)
     */
    AddressService addressService;

    //GET ONE /api/service/address/{id}
    @ApiOperation(value = "Возвращает адрес по id", notes = "Адрес должен существовать")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findById(@PathVariable("id") long id) {
        log.info("Send a get-request to get Address with id = {} from edo-repository " +
                "(RestTemplate on edo-service side)", id);
        var addressDto = addressService.findById(id);
        log.info("Response from edo-repository: {}", addressDto);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }

    //GET ALL /api/service/address/getAllAdresseRestTemplate
    @ApiOperation(value = "Возвращает все адреса", notes = "Адреса должны существовать")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAll() {
        log.info("Send a get-request to get all Addresse from edo-repository" +
                " (RestTemplate on edo-service side)");
        var addressDtos = addressService.findAll();
        log.info("Response from edo-repository: {}", addressDtos);
        return new ResponseEntity<>(addressDtos, HttpStatus.OK);
    }

    //POST /api/service/address
    @ApiOperation(value = "Создает адрес в БД", notes = "Адрес должен существовать")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> save(@RequestBody AddressDto addressDto) {
        log.info("Send a post-request to edo-repository to post new Address to database" +
                " (RestTemplate on edo-service side)");
        addressService.save(addressDto);
        log.info("Response: {} was added to database", addressDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //DELETE /api/service/address/{id}
    @ApiOperation(value = "Удаляет адрес из БД", notes = "Адрес должен существовать")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> delete(@PathVariable("id") long id) {
        log.info("Send a delete-request to edo-repository to delete Address with id = {}" +
                " from database (RestTemplate on edo-service side)", id);
        addressService.delete(id);
        log.info("Response: Address with id = {} was deleted from database", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
