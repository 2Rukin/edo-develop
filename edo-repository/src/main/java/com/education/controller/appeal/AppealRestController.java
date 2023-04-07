package com.education.controller.appeal;

import com.education.entity.Appeal;
import com.education.service.appeal.AppealService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AppealDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.education.mapper.AppealMapper.APPEAL_MAPPER;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/repository/appeal")
public class AppealRestController {

    private AppealService appealService;

    @ApiOperation(value = "В строке таблицы Appeal заполняет поле archivedDate,изменяет поле status", notes = "Строка в Appeal должна существовать")
    @PutMapping(value = "/move/{id}")
    public ResponseEntity<Void> moveToArchive(@PathVariable Long id) {
        log.info("Adding archived date {} in Appeal with id: {}", ZonedDateTime.now(), id);
        appealService.moveToArchive(id);
        log.info("Moving appeal with id: {} to archive is success!", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Находит все строки таблицы Appeal с полем acrhivedDate = null",
            notes = "Строка в Appeal должна существовать")
    @GetMapping(value = "/findAllNotArchived", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AppealDto>> findAllNotArchived() {
        log.info("Getting from database all appeals with field acrhivedDate = null");
        var appealDtoCollection = APPEAL_MAPPER.toDto(appealService.findAllNotArchived());
        log.info("Appeals: {}", appealDtoCollection);
        return new ResponseEntity<>(appealDtoCollection, HttpStatus.OK);
    }

    @ApiOperation(value = "Находит строку таблицы Appeal c полем acrhivedDate = null, по заданному id",
            notes = "Строка в Appeal должна существовать")
    @GetMapping(value = "/findByIdNotArchived/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppealDto> findByIdNotArchived(@PathVariable Long id) {
        log.info("Getting from database appeal with field acrhivedDate = null, with id: {}", id);
        var appeal = appealService.findByIdNotArchived(id);
        log.info("Appeal: {}", appeal);
        return new ResponseEntity<>(APPEAL_MAPPER.toDto(appeal), HttpStatus.OK);
    }

    @ApiOperation(value = "Находит все строки таблицы Appeal",
            notes = "Строка в Appeal должна существовать")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AppealDto>> getAllAppeal() {
        log.info("Getting from database all appeals");
        var appealDtoCollection = APPEAL_MAPPER.toDto(appealService.findAll());
        log.info("Appeals: {}", appealDtoCollection);
        return new ResponseEntity<>(appealDtoCollection, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаляет строку таблицы Appeal по id",
            notes = "Строка в Appeal должна существовать")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteAppeal(@PathVariable(value = "id") Long id) {
        log.info("Deleting from database appeal with id: {}", id);
        appealService.delete(id);
        log.info("Deleting from database appeal with id: {}, success!", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавляет новую строку таблицы Appeal", notes = "Строка в Appeal должна существовать")

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppealDto> saveAppeal(@Valid @RequestBody AppealDto appealDto ) {


        log.info("Creating appeal");
        var appeal = appealService.save(APPEAL_MAPPER.toEntity(appealDto));
        log.info("Creating appeal {}, success!", appeal);
        return new ResponseEntity<>(APPEAL_MAPPER.toDto(appealService.findById(appeal.getId())), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Находит строку таблицы Appeal по id",
            notes = "Строка в Appeal должна существовать")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppealDto> getAppealById(@PathVariable Long id) {
        log.info("Getting from database appeal with id: {}", id);
        var appeal = appealService.findById(id);
        log.info("Appeal: {}", appeal);
        return new ResponseEntity<>(APPEAL_MAPPER.toDto(appeal), HttpStatus.OK);
    }

    @ApiOperation(value = "Находит строку таблицы Appeal по Questions id",
            notes = "Строка в Appeal должна существовать")
    @GetMapping(value = "/findAppealByQuestionsId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppealDto> findAppealByQuestionsId(@PathVariable Long id) {
        log.info("Getting from database appeal with questions id: {}", id);
        var appeal = appealService.findAppealByQuestionsId(id);
        log.info("Appeal: {}", appeal);
        return new ResponseEntity<>(APPEAL_MAPPER.toDto(appeal), HttpStatus.OK);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<String> validUserException(MethodArgumentNotValidException ex) {
        log.warn(ex.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
    }
}
