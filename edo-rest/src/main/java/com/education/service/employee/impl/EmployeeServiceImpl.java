package com.education.service.employee.impl;

import com.education.service.employee.EmployeeService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static model.constant.Constant.EDO_SERVICE_NAME;
import static model.constant.Constant.EMPLOYEE_FIO_SEARCH_PARAMETER;


/**
 * @author Kryukov Andrey
 * Сервис направляет запрос в edo-service
 */
@Service
@Log4j2
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final RestTemplate restTemplate;

    @Override
    public Collection<EmployeeDto> findAllByFullName(String fullName) {
        log.info("Build uri to service");
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/employee/search")
                .addParameter(EMPLOYEE_FIO_SEARCH_PARAMETER, fullName).toString();
        log.info("Sent a request to receive the employee collection with requested full name");
        return restTemplate.getForObject(uri, Collection.class);
    }
}
