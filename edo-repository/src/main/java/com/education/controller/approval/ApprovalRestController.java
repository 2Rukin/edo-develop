package com.education.controller.approval;

import com.education.entity.Approval;
import com.education.service.author.approval.ApprovalService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.ApprovalDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import static com.education.mapper.ApprovalMapper.APPROVAL_MAPPER;

/**
 * Rest-контроллер сущности Approval для отправки запросов от клиентов к БД
 */
@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/repository/approval")
@ApiOperation("Rest-контроллер для ApprovalDto, который отправляет запросы от клиентов к сервисам edo-service")
public class ApprovalRestController {
    private final ApprovalService approvalService;

    @ApiOperation(value = "Добавляет новую запись в таблицу Approval")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApprovalDto> save(@RequestBody ApprovalDto approvalDto) {
        log.info("Send a post-request to post new Approval to database");
        Approval savedApproval = approvalService.save(APPROVAL_MAPPER.toEntity(approvalDto));
        log.info("Response: {} was added to database", approvalDto);
        return new ResponseEntity<>(APPROVAL_MAPPER.toDto(savedApproval), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Возвращает лист согласования по id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApprovalDto> findById(@PathVariable("id") long id) {
        log.info("Send a get-request to get Approval with id = {} from database", id);
        var approvalDto = APPROVAL_MAPPER.toDto(approvalService.findById(id));
        log.info("Response from database: {}", approvalDto);
        return new ResponseEntity<>(approvalDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Возвращает все листы согласования")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ApprovalDto>> findAll() {
        log.info("Send a get-request to get all approvals from database");
        var listApprovalDto = APPROVAL_MAPPER.toDto(approvalService.findAll());
        log.info("Response from database: {}", listApprovalDto);
        return new ResponseEntity<>(listApprovalDto, HttpStatus.OK);
    }

    @ApiOperation("Возвращает все листы согласования по списку id")
    @GetMapping("/all/{ids}")
    private ResponseEntity<Collection<ApprovalDto>> findAllById(@PathVariable List<Long> ids) {
        log.info("Send a get-request to get all approvals from database by ids");
        var listApprovalDto = APPROVAL_MAPPER.toDto(approvalService.findAllById(ids));
        log.info("Response from database: {}", listApprovalDto);
        return new ResponseEntity<>(listApprovalDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Удаляет лист согласования")
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        log.info("Send a delete-request to delete approval with id = {} from database", id);
        approvalService.delete(id);
        log.info("Response: approval with id = {} was deleted from database", id);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "В строке таблицы Approval заполняет поле archived_date")
    @PatchMapping(value = "/move/{id}")
    public ResponseEntity<ApprovalDto> moveToArchive(@PathVariable Long id) {
        log.info("Adding archived date {} in approval with id: {}", ZonedDateTime.now(), id);
        approvalService.moveToArchive(id);
        log.info("Moving approval with id: {} to archive", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Находит строку таблицы Approval c полем acrhivedDate = null, по заданному id")
    @GetMapping(value = "/findByIdNotArchived/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApprovalDto> findByIdNotArchived(@PathVariable Long id) {
        log.info("Getting from database approval with field archived_date = null, with id: {}", id);
        var approval = approvalService.findByIdNotArchived(id);
        log.info("Approval: {}", approval);
        return new ResponseEntity<>(APPROVAL_MAPPER.toDto(approval), HttpStatus.OK);
    }

    @ApiOperation(value = "Находит все листы согласования с полем acrhivedDate = null")
    @GetMapping(value = "/findAllNotArchived", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ApprovalDto>> findAllNotArchived() {
        log.info("Getting from database all approvals with field archived_date = null");
        var listApprovalDto = approvalService.findAllNotArchived();
        log.info("List of approvals: {}", listApprovalDto);
        return new ResponseEntity<>(APPROVAL_MAPPER.toDto(listApprovalDto), HttpStatus.OK);
    }

    // findByIdInAndArchivedDateNull
    @ApiOperation("Возвращает все не перемещенные в архив листы согласования по списку id")
    @GetMapping(value = "/findByIdInAndArchivedDateNull/{ids}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ApprovalDto>> findByIdInAndArchivedDateNull(@PathVariable List<Long> ids) {
        log.info("Getting from database all approvals with field archived_date = null and with ids");
        var listApprovalDto = APPROVAL_MAPPER.toDto(approvalService.findByIdInAndArchivedDateNull(ids));
        log.info("List of approvals: {}", listApprovalDto);
        return new ResponseEntity<>(listApprovalDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Обновляет запись в таблице Approval")
    @PostMapping(value = "/update")
    public ResponseEntity<ApprovalDto> update(@RequestBody ApprovalDto approvalDto) {
        log.info("Send a post-request to update Approval from database");
        approvalService.update(APPROVAL_MAPPER.toEntity(approvalDto));
        log.info("Response: {} was updated to database", approvalDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
