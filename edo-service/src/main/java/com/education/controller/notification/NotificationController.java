package com.education.controller.notification;

import com.education.service.notification.NotificationService;
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

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/service/notification")
@ApiOperation("NotificationController in module edo - service ")
public class NotificationController {
    private final NotificationService notificationService;

    @ApiOperation(value = "Get notification settings by ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationDto> findById(@PathVariable Long id) {
        log.info("Sending response with notification of assigned ID");
        NotificationDto notificationDto = notificationService.findById(id);
        log.info("Operation was successful, got notification by ID={}", id);
        return new ResponseEntity<>(notificationDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all notification settings")
    @GetMapping("/all")
    public ResponseEntity<Collection<NotificationDto>> findAll() {
        log.info("Sending response with all notifications");
        Collection<NotificationDto> notificationDto = notificationService.findAll();
        log.info("Operation was successful, got all notifications");
        return new ResponseEntity<>(notificationDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Get notification settings by IDs")
    @GetMapping("/all/{ids}")
    public ResponseEntity<Collection<NotificationDto>> findAllById(@PathVariable List<Long> ids) {
        log.info("Sending response with notifications of assigned IDs");
        Collection<NotificationDto> notificationDto = notificationService.findAllById(ids);
        log.info("Operation was successful, got notifications by IDs={}", ids);
        return new ResponseEntity<>(notificationDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Save notification settings")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationDto> save(@RequestBody NotificationDto notificationDto) {
        log.info("Saving notification settings");
        NotificationDto savedNotificationDto = notificationService.save(notificationDto);
        log.info("Operation was successful, saved notification with ID={}", savedNotificationDto.getId());
        return new ResponseEntity<>(savedNotificationDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete notification settings by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting notification settings with ID={}", id);
        notificationService.delete(id);
        log.info("Operation was successful, deleted notification with ID={}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
