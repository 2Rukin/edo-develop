package com.education.service.notification;

import com.education.entity.Employee;
import com.education.entity.FilePool;
import com.education.entity.Notification;

import java.util.Collection;

public interface NotificationService {
    Notification save(Notification notification);
    void delete(Notification notification);
    Collection<Notification> findAllById(Iterable<Long> ids);
    Notification findById (Long id);
    Collection<Notification> findAll();
}
