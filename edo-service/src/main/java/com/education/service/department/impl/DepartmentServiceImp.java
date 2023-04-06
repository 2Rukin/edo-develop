package com.education.service.department.impl;


import com.education.service.department.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.DepartmentDto;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.education.util.URIBuilderUtil.buildURI;
import static model.constant.Constant.*;


/**
 * @author Usolkin Dmitry
 * Сервис, который соединяет реализацию двух модулей через RestTemplate
 * Имеет все те же операции, что и service в edo-repository
 */
@Service
@Log4j2
@AllArgsConstructor
public class DepartmentServiceImp implements DepartmentService {

    private final RestTemplate restTemplate;


    @Override
    public void save(DepartmentDto department) {
        var builder = buildURI(EDO_REPOSITORY_NAME, DEPARTMENT_URL)
                .setPath("/");
        restTemplate.postForObject(builder.toString(), department, DepartmentDto.class);
        log.info("sent a request to save the department in edo - repository");
    }

    @Override
    public void removeToArchived(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, DEPARTMENT_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        restTemplate.delete(builder.toString());
        log.info("sent a request to archive the department in edo - repository");
    }

    @Override
    public DepartmentDto findById(Long id) {
        log.info("sent a request to receive the department in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, DEPARTMENT_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> findByAllId(String ids) {
        log.info("sent a request to receive the departments in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, DEPARTMENT_URL)
                .setPath("/all/")
                .setPath(String.valueOf(ids));
        return restTemplate.getForObject(builder.toString(), List.class);
    }

    @Override
    public DepartmentDto findByIdNotArchived(Long id) {
        log.info("sent a request to receive the department not archived in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, DEPARTMENT_URL)
                .setPath("/notArchived/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), DepartmentDto.class);

    }

    @Override
    public List<DepartmentDto> findByAllIdNotArchived(String ids) {
        log.info("sent a request to receive the departments not archived in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, DEPARTMENT_URL)
                .setPath("/notArchivedAll/")
                .setPath(String.valueOf(ids));
        return restTemplate.getForObject(builder.toString(), List.class);
    }


}
