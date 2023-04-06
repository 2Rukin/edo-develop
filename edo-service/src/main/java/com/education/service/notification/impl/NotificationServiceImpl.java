package com.education.service.notification.impl;

import com.education.service.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.NotificationDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import static com.education.util.URIBuilderUtil.buildURI;

import static model.constant.Constant.EDO_REPOSITORY_NAME;
import static model.constant.Constant.NOTIFICATION_URL;


@Service
@Log4j2
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final RestTemplate restTemplate;

    @Override
    public NotificationDto findById(Long id) {
        log.info("Sent a request to receive the notification in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, NOTIFICATION_URL)
                .setPath("/")
                .setPath(String.valueOf(id)).toString();
        return restTemplate.getForObject(builder, NotificationDto.class);
    }

    @Override
    public Collection<NotificationDto> findAll() {
        log.info("Sent a request to receive all notifications in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, NOTIFICATION_URL)
                .setPath("/all").toString();
        return restTemplate.getForObject(builder, Collection.class);
    }

    @Override
    public Collection<NotificationDto> findAllById(List<Long> ids) {
        log.info("Sent a request to receive notifications by ids in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, NOTIFICATION_URL)
                .setPath("/all/")
                .setPath(ids.toString()).toString();
        return restTemplate.getForObject(builder, Collection.class);
    }
    @Override
    public NotificationDto save(NotificationDto notification) {
        log.info("Sent a request to save the notification in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, NOTIFICATION_URL)
                .setPath("/save").toString();
        return restTemplate.postForObject(builder, notification, NotificationDto.class);
    }

    @Override
    public void delete(Long id) {
        log.info("Sent a request to delete the notification in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, NOTIFICATION_URL)
                .setPath("/")
                .setPath(String.valueOf(id)).toString();
        restTemplate.delete(builder);
    }

}