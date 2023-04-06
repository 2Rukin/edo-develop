package com.education.controller.notification;

import com.education.mapper.NotificationMapper;
import com.education.service.notification.impl.NotificationServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.NotificationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@Log4j2
@ApiOperation(value = "Контроллер сотрудника")
@RestController
@RequestMapping("/api/repository/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationServiceImpl notificationService;

    private final NotificationMapper notificationMapper;

    @ApiOperation(value = "Получение настроек оповещения по идентификатору")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationDto> findById(@PathVariable Long id) {
        log.info("Send a response with the notification of the assigned id");
        NotificationDto notificationDto = notificationMapper.toDto(notificationService.findById(id));
        log.info("The operation was successful, we got the notification by id ={}", id);
        return new ResponseEntity<>(notificationDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение всех настроек оповещений")
    @GetMapping("/all")
    public ResponseEntity<Collection<NotificationDto>> findAll() {
        log.info("Send a response with the notifications");
        Collection<NotificationDto> notificationDto = notificationMapper.toDto(notificationService.findAll());
        log.info("The operation was successful, we got the all notifications");
        return new ResponseEntity<>(notificationDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Получение настроек оповещения по идентификаторам")
    @GetMapping("/all/{ids}")
    public ResponseEntity<Collection<NotificationDto>> findAllById(@PathVariable List<Long> ids) {
        log.info("Send a response with the notification of the assigned IDs");
        Collection<NotificationDto> notificationDto = notificationMapper.toDto(notificationService.findAllById(ids));
        log.info("The operation was successful, we got the notification by id = {} ", ids);
        return new ResponseEntity<>(notificationDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Сохраняет настройки оповещения")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto) {
        log.info("Starting the save operation");
        notificationService.save(notificationMapper.toEntity(notificationDto));
        log.info("Saving the notification");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}