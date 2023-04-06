package com.education.service.notification.impl;

import com.education.entity.Notification;
import com.education.repository.notification.NotificationRepository;
import com.education.service.notification.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@AllArgsConstructor
@Service

public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }


    @Override
    @Transactional(readOnly = true)
    public Collection<Notification> findAllById(Iterable<Long> ids) {
        return notificationRepository.findAllById(ids);
    }
    @Override
    @Transactional(readOnly = true)
    public Notification findById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Notification> findAll() {
        return notificationRepository.findAll();
    }
}
