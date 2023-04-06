package com.education.controller.employee;


import com.education.service.employee.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author Andrey Kryukov
 * Rest-контроллер отправляет запрос от клиента в сервисы
*/
@Log4j2
@ApiOperation("Контроллер сотрудника")
@RestController
@RequestMapping("/api/rest/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @ApiOperation(value = "Предоставление сотрудников по ФИО")
    @GetMapping(value = "/search")
    public ResponseEntity<Collection<EmployeeDto>> findAllByFullName(@RequestParam("fullName") String fullName) {
        log.info("Send a response with the requested full name");
        Collection<EmployeeDto> employeeDto = employeeService.findAllByFullName(fullName);
        log.info("The operation was successful, they got employee with full name = {}", fullName);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
