package com.education.controller.resolution;

import com.education.service.resolution.ResolutionService;
import io.swagger.annotations.ApiOperation;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.ResolutionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * REST контроллер для отправки запросов к БД
 */
@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("api/service/resolution")
public class ResolutionController {

    /**
     * Служит для связи с сервисом ResolutionService
     */
    private ResolutionService resolutionService;

    @ApiOperation(value = "Добавление резолюции")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<ResolutionDto> saveResolution(@RequestBody ResolutionDto resolutionDto) {
        log.info("POST request has been sent");
        var save = resolutionService.save(resolutionDto);
        log.info("{} has has been added", resolutionDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Перемещение в архив")
    @PostMapping(value = "/move/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<ResolutionDto> moveToArchive(@PathVariable Long id) {
        log.info("PATCH request has been sent");
        resolutionService.moveToArchive(id);
        var resolutionDto = resolutionService.findById(id);
        log.info("Resolution with id = {} has been moved to archive", id);
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Поиск резолюции по id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<ResolutionDto> findById(@PathVariable Long id) {
        log.info("GET request to search for resolution with id = {} has been sent", id);
        var resolutionDto = resolutionService.findById(id);
        log.info("Resolution with id = {} was found", id);
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Поиск всех резолюций")
    @GetMapping(value = "/all/{id}", produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Collection<ResolutionDto>> findAll(@PathVariable Long id) {
        log.info("GET request to search for all resolutions has been sent");
        var resolutionDto = resolutionService.findAllById(id);
        log.info("Resolutions was found");
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Поиск не архивированной резолюции по id")
    @GetMapping(value = "/notArchived/{id}")
    public ResponseEntity<ResolutionDto> findByIdNotArchived(@PathVariable Long id) {
        log.info("GET request to search for an unarchived resolution has been sent");
        var resolutionDto = resolutionService.findByIdNotArchived(id);
        log.info("Resolution with id = {} was found", id);
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Поиск всех не архивированных резолюций")
    @GetMapping(value = "/notArchived/all/{id}")
    public ResponseEntity<Collection<ResolutionDto>> findAllByIdNotArchived(@PathVariable Long id) {
        log.info("GET request to search for all unarchived resolutions has been sent");
        var resolutionDto = resolutionService.findAllByIdNotArchived(id);
        log.info("Resolutions was found");
        return new ResponseEntity<>(resolutionDto, HttpStatus.OK);
    }


}
