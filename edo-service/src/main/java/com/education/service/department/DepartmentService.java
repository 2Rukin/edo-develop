package com.education.service.department;


import model.dto.DepartmentDto;

import java.util.Collection;


/**
 * @author Usolkin Dmitry
 * Интерфейс сервиса в edo-service
 */
public interface DepartmentService {

    void save(DepartmentDto departmentDto);

    void removeToArchived(Long id);

    DepartmentDto findById(Long id);

    Collection<DepartmentDto> findByAllId(String ids);

    DepartmentDto findByIdNotArchived(Long id);

    Collection<DepartmentDto> findByAllIdNotArchived(String ids);

}
