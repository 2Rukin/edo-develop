package com.education.service;


import com.education.job.JobScheduler;
import com.education.mapper.ConvertEmployee;

import com.education.util.URIBuilderUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.EmployeeDto;
import model.dto.ExternalEmployeeDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import static model.constant.Constant.EDO_SERVICE_NAME;


/**
 * @author Usolkin Dmitry & Kostenko Aleksandr & Kryukov Andrey
 * Класс планировщик с методом, который будет нарпавлять обьект EmployeeDto в контроллер Dto каждый час.
 */
@Component
@AllArgsConstructor
@Log4j2
public class ServiceExternalEmployeeImp implements ServiceExternalEmployee {

    private final ObjectMapper objectMapper;
    private final JobScheduler jobScheduler;
    private final RestTemplate restTemplate;

    /**
     * Каждый час синхронизирует внешних пользователей
     */


    @Override
    @Scheduled(fixedDelayString = "${interval}")
    public void dataSyncEveryHour() {
        log.info("The data synchronization method has started, it starts every hour");
        Collection<ExternalEmployeeDto> externalEmployeesDto;
        try {
            externalEmployeesDto = objectMapper.readValue(new URL(jobScheduler.getEmployeeUrl()), new TypeReference<>() {});
            log.info("Get all users from JSON and save to collection");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String uriEmployeeSavePath = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/employee/collection").toString();
        log.info("Got a URL to save Employee to the database");
        var employeeDtos = ConvertEmployee.toDto(externalEmployeesDto);
        log.info("Finished the conversion process externalEmployeeDto in EmployeeDto");
        restTemplate.postForObject(uriEmployeeSavePath, employeeDtos, EmployeeDto.class);
        log.info("Sent a save request to the EmployeeDto database");
    }
}




