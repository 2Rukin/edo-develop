package com.education.controller.appeal;

import com.education.service.appeal.AllAppealService;
import com.education.service.appeal.AppealService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AllAppealDto;
import model.dto.AppealDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/service/allAppeals")
public class AllAppealRestController {

    private AllAppealService allAppealService;


    @ApiOperation(value = "Получает все обращения", notes = "Строка в Appeal должна существовать")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AllAppealDto>> getAllAppeals(@RequestParam("creatorId") Long creatorId, @RequestParam("start") int start, @RequestParam("end") int end) {
        log.info("Getting from database all appeals");
        var appealDtoCollection = allAppealService.getAllAppeals(creatorId, start, end);
        log.info("Appeals: {}", appealDtoCollection);
        return new ResponseEntity<>(appealDtoCollection, HttpStatus.OK);
    }


}
