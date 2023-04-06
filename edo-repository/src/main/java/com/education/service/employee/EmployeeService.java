package com.education.service.employee;

import com.education.entity.Employee;
import model.dto.EmployeeDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Kiladze George
 * интерфейс сервиса в edo-repository
 */
public interface EmployeeService {

    void save(Employee employee);

    void moveToArchived(Long id);

    Employee findById(Long id);

    Collection<Employee> findAll();

    Collection<Employee> findAllById(Iterable<Long> ids);

    Employee findByIdAndArchivedDateNull(Long id);

    Collection<Employee> findByIdInAndArchivedDateNull(Iterable<Long> ids);

    @Transactional(readOnly = true)
    Collection<Employee> findAllByFullName(String fullName);

    Collection<Employee> saveCollection(Collection<Employee> employees);
}
