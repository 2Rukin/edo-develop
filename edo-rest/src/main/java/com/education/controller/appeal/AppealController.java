package com.education.controller.appeal;

import com.education.service.appeal.AppealService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AppealDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest-контроллер в "edo-rest", служит для отправки обращения (Appeal) в БД используя RestTemplate
 */
@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/api/edo/appeal")
public class AppealController {
    private final AppealService appealService;

    @ApiOperation(value = "Принимает обращение, отправляет на edo-service", notes = "Обращение должен существовать")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppealDto> save(@RequestBody AppealDto appealDto) {
        log.info("Отправить пост-запрос в edo-service");
        var save = appealService.save(appealDto);
        log.info(" пост-запрос отправлен в edo-service");
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @ApiOperation(value = "В строке таблицы Appeal заполняет поле archivedDate", notes = "Строка в Appeal должна существовать")
    @PutMapping(value = "/move/{id}")
    public ResponseEntity<AppealDto> moveToArchive(@PathVariable Long id) {
        appealService.moveToArchive(id);
        log.info("Moving appeal with id: {} to edo-service is success!", id);
        return new ResponseEntity<>(appealService.findById(id), HttpStatus.OK);
    }
}
