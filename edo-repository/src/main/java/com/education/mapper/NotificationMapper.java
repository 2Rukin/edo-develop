package com.education.mapper;

import com.education.entity.Notification;
import model.dto.NotificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface NotificationMapper extends AbstractMapper<Notification, NotificationDto> {
    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

}
