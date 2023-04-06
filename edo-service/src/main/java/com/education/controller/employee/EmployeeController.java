package com.education.controller.employee;

import com.education.service.emloyee.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * @author Kiladze George
 * Rest-контроллер отправляет запрос от клиента в бд
 */
@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/service/employee")
@ApiOperation("EmployeeController in module edo - service ")
public class EmployeeController {

    private final EmployeeService employeeService;

    @ApiOperation(value = "Создает сотрудника")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        log.info("Starting the save operation");
        employeeService.save(employeeDto);
        log.info("Saving the employee");
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Архивация сотрудника с занесением времени архивации")
    @PostMapping(value = "/{id}")
    public ResponseEntity<Void> moveToArchived(@PathVariable Long id) {
        log.info("Starting the archiving operation");
        employeeService.moveToArchived(id);
        log.info("Archiving the employee");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Предоставление сотрудника по индентификатору")
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        log.info("Send a response with the employee of the assigned id");
        var employeeDto = employeeService.findById(id);
        log.info("The operation was successful, we got the employee by id ={}", id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Предоставление всех сотрудников")
    @GetMapping(value = "/all")
    public ResponseEntity<Collection<EmployeeDto>> findAll() {
        log.info("Send a response with the employees");
        var employeeDto = employeeService.findAll();
        log.info("The operation was successful, we got the all employees");
        return new ResponseEntity<Collection<EmployeeDto>>(employeeDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Предоставление сотрудников по назначеным идентификаторам")
    @GetMapping(value = "/all/{ids}")
    public ResponseEntity<Collection<EmployeeDto>> findAllById(@PathVariable String ids) {
        log.info("Send a response with the employee of the assigned IDs");
        var employeeDto = employeeService.findAllById(ids);
        log.info("The operation was successful, we got the employee by id = {} ", ids);
        return new ResponseEntity<Collection<EmployeeDto>>(employeeDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Предоставление сотрудника без архивирования по идентификатору")
    @GetMapping(value = "/NotArchived/{id}")
    public ResponseEntity<EmployeeDto> findByIdNotArchived(@PathVariable Long id) {
        log.info("Send a response with the employee not archived of the assigned ID");
        var employeeDto = employeeService.findByIdAndArchivedDateNull(id);
        log.info("The operation was successful, they got the non-archived employee by id ={}", id);
        return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Предоставление сотрудников без архивирования по идентификатору")
    @GetMapping(value = "/NotArchivedAll/{ids}")
    public ResponseEntity<Collection<EmployeeDto>> findByAllIdNotArchived(@PathVariable String ids) {
        log.info("Send a response with the employee not archived of the assigned IDs");
        var employeeDto = employeeService.findByIdInAndArchivedDateNull(ids);
        log.info("The operation was successful, they got the non-archived employee by id ={}", ids);
        return new ResponseEntity<Collection<EmployeeDto>>(employeeDto, HttpStatus.OK);
    }

    /**
     * Сохраняет коллекцию сотрудников
     *
     * @param employeeDto - Коллекция сотрудников
     */
    @ApiOperation(value = "Сохраняет коллекцию сотрудников")
    @PostMapping(value = "/collection")
    public ResponseEntity<Collection<EmployeeDto>> saveCollection(@RequestBody Collection<EmployeeDto> employeeDto) {
        log.info("Send a response with the collection employee");
        var collection = employeeService.saveCollection(employeeDto);
        log.info("The operation was successful, they saved the collection");
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

}
