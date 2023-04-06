package com.education.service.employee.impl;

import com.education.entity.Address;
import com.education.entity.Department;
import com.education.entity.Employee;
import com.education.repository.employee.EmployeeRepository;
import com.education.service.address.AddressService;
import com.education.service.department.DepartmentService;
import com.education.service.employee.EmployeeService;
import com.github.aleksandy.petrovich.Case;
import com.github.aleksandy.petrovich.Petrovich;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Kiladze George
 * Сервис, который использует методы Repository
 * Сервис рабоатет с сущностью Employee
 * Сохраняет, архивирует
 * предоставляет по id сотрудника, всех сотрудников с указанными id,
 * всех сотрудников или сотрудниковЮ которые не имеют дату архивации
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressService addressService;
    private final DepartmentService departmentService;

    /**
     * добавляет сотрудника
     *
     * @param employee
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Employee employee) {
        employee.setCreationDate(ZonedDateTime.now());
        employeeRepository.save(employee);
    }

    /**
     * архивация сотрудника с занесением времени архивации
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchived(Long id) {
        employeeRepository.moveToArchived(id);
    }

    /**
     * предоставляет сотрудника по id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    /**
     * предоставляет всех сотрудников
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * предоставляет сотрудников по нескольким id
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findAllById(Iterable<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

    /**
     * предоставляет заархивированного сотрудника по id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Employee findByIdAndArchivedDateNull(Long id) {
        return employeeRepository.findByIdAndArchivedDateNull(id);
    }

    /**
     * предоставляет заархивированных сотрудников по нескольким id
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByIdInAndArchivedDateNull(Iterable<Long> ids) {
        return employeeRepository.findByIdInAndArchivedDateNull(ids);
    }

    /**
     * предоставляет сторудников по ФИО
     *
     * @param fullName
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Employee> findAllByFullName(String fullName) {
        return employeeRepository.findAllByFullName(fullName);
    }


    /**
     * Сохранение коллекции сотрудников
     *
     * @param employees
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Collection<Employee> saveCollection(Collection<Employee> employees) {

        Collection<Address> addresses = new ArrayList<>();
        Collection<Department> departments = new ArrayList<>();

        Collection<Employee> employeesFromDb = employeeRepository.findAll();
        Collection<Department> departmentsFromDb = departmentService.findAll();

        employees.forEach(employee -> {
            employeesFromDb.forEach(employeeFromDb -> {
                if (employeeFromDb.getExternalId().equals(employee.getExternalId())) {
                    employee.setId(employeeFromDb.getId());
                    employee.getAddress().setId(employeeFromDb.getAddress().getId());
                    employee.setCreationDate(employeeFromDb.getCreationDate());
                }
            });
            if (employee.getCreationDate()==null){
                employee.setCreationDate(ZonedDateTime.now());
            }
            departments.add(employee.getDepartment());
            addresses.add(employee.getAddress());
            addCases(employee);
        });

        Collection<Department> distinctDepartments = new ArrayList<>(departments.stream()
                .collect(Collectors.toMap(Department::getExternalId, Function.identity(), ((department1, department2) -> department1)))
                .values());

        employees.forEach(employee -> employee.setDepartment(distinctDepartments.stream()
                .filter(department -> department.getExternalId().equals(employee.getDepartment().getExternalId()))
                .findAny().orElseThrow(() -> new NoSuchElementException("Ошибка при связывании объектов"))));

        distinctDepartments.forEach(department -> {
            departmentsFromDb.forEach(departmentFromDb -> {
                if (departmentFromDb.getExternalId().equals(department.getExternalId())) {
                    department.setId(departmentFromDb.getId());
                    department.getAddress().setId(departmentFromDb.getAddress().getId());
                    department.setCreationDate(departmentFromDb.getCreationDate());
                }
            });
            if (department.getCreationDate()==null){
                department.setCreationDate(ZonedDateTime.now());
            }
            department.setDepartment(null);
            addresses.add(department.getAddress());
        });

        addressService.saveCollection(addresses);
        departmentService.saveCollection(distinctDepartments);

        return employeeRepository.saveAll(employees);
    }


    /**
     * Конструктор падежей
     *
     * @param employee
     * @return
     */
    private static void addCases(Employee employee) {
        Petrovich.Names names = new Petrovich.Names(employee.getLastName(), employee.getFirstName(), employee.getMiddleName(), null);
        Petrovich petrovich = new Petrovich();
        String fioDative = petrovich.inflectTo(names, Case.DATIVE).lastName
                .concat(StringUtils.SPACE)
                .concat(petrovich.inflectTo(names, Case.DATIVE).firstName)
                .concat(StringUtils.SPACE)
                .concat(petrovich.inflectTo(names, Case.DATIVE).middleName);
        employee.setFioDative(fioDative);

        String fioGenitive = petrovich.inflectTo(names, Case.GENITIVE).lastName
                .concat(StringUtils.SPACE)
                .concat(petrovich.inflectTo(names, Case.GENITIVE).firstName)
                .concat(StringUtils.SPACE)
                .concat(petrovich.inflectTo(names, Case.GENITIVE).middleName);
        employee.setFioGenitive(fioGenitive);

        String fioNominative = petrovich.inflectTo(names, Case.NOMINATIVE).lastName
                .concat(StringUtils.SPACE)
                .concat(petrovich.inflectTo(names, Case.NOMINATIVE).firstName)
                .concat(StringUtils.SPACE)
                .concat(petrovich.inflectTo(names, Case.NOMINATIVE).middleName);
        employee.setFioNominative(fioNominative);

    }
}
