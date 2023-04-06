package com.education.mapper;

import com.education.entity.Employee;
import model.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Интерфейс для реализации конвертации работника в ДТО и обратно
 */

@Mapper
public interface EmployeeMapper extends AbstractMapper<Employee, EmployeeDto> {
    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

}
