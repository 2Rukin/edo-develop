package com.education.service.notification;

import model.dto.NotificationDto;

import java.util.Collection;
import java.util.List;

public interface NotificationService {
    NotificationDto findById(Long id);
    Collection<NotificationDto> findAll();
    Collection<NotificationDto> findAllById(List<Long> ids);
    NotificationDto save(NotificationDto notificationDto);
    void delete(Long id);
}

