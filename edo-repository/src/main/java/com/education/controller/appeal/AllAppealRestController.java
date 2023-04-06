package com.education.controller.appeal;

import com.education.service.appeal.AllAppealService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AllAppealDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import static com.education.mapper.AllAppealMapper.ALL_APPEAL_MAPPER;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/repository/allAppeals")
public class AllAppealRestController {

    private AllAppealService allAppealService;

    @ApiOperation(value = "Отображает все обращения",
            notes = "контроллер должен принимать в качестве параметров два параметра: последний взятый пользователь, количество пользователей для отображения")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AllAppealDto>> getAllAppealWithPagination(@RequestParam("creatorId") Long creatorId,
                                                                               @RequestParam("start") int start,
                                                                               @RequestParam("end") int end) {
        log.info("Getting from database all appeals");
        var appealDtoCollection = ALL_APPEAL_MAPPER.toDto(allAppealService.findAllEmployeeByIdWithPagination(creatorId, start, end));
        log.info("Appeals: {}", appealDtoCollection);
        return new ResponseEntity<>(appealDtoCollection, HttpStatus.OK);
    }

}