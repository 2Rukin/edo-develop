package com.education.controller.address;

import com.education.service.address.AddressService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static com.education.mapper.AddressMapper.ADDRESS_MAPPER;

/**
 * Rest-контроллер в "edo-repository", служит для отправки запросов
 * от клиента(которым может быть другой микросервис) к БД.
 * "@Log4j2" нужна для создания логов, для удобной отладки программы
 */
@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/api/repository/address")
public class AddressController {

    /**
     * Поле "addressService" нужно для вызова Service-слоя (edo-repository),
     * который нужен для связи с репозиторием (edo-repository)
     */
    private final AddressService addressService;

    //GET ONE /api/repository/address/{id}
    @ApiOperation(value = "Возвращает адрес по id", notes = "Адрес должен существовать")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> findById(@PathVariable("id") long id) {
        log.info("Send a get-request to get Address with id = {} from database", id);
        var addressDto = ADDRESS_MAPPER.toDto(addressService.findById(id));
        log.info("Response from database: {}", addressDto);
        return new ResponseEntity<>(addressDto, HttpStatus.OK);
    }


    //GET ALL /api/repository/address/all
    @ApiOperation(value = "Возвращает все адреса", notes = "Адреса должны существовать")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AddressDto>> findAll() {
        log.info("Send a get-request to get all Addresse from database");
        var addressDtos = ADDRESS_MAPPER.toDto(addressService.findAll());
        log.info("Response from database: {}", addressDtos);
        return new ResponseEntity<>(addressDtos, HttpStatus.OK);
    }

    //POST /api/repository/address
    @ApiOperation(value = "Создает адрес в БД", notes = "Адрес должен существовать")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDto> save(@RequestBody @Valid AddressDto addressDto) {
        log.info("Send a post-request to post new Address to database");
        addressService.save(ADDRESS_MAPPER.toEntity(addressDto));
        log.info("Response: {} was added to database", addressDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //DELETE /api/repository/address/{id}
    @ApiOperation(value = "Удаляет адрес из БД", notes = "Адрес должен существовать")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") long id) {
        log.info("Send a delete-request to delete Address with id = {} from database", id);
        addressService.delete(addressService.findById(id));
        log.info("Response: Address with id = {} was deleted from database", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
