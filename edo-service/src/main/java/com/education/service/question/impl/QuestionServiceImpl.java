package com.education.service.question.impl;

import com.education.service.question.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.QuestionDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static com.education.util.URIBuilderUtil.buildURI;
import static model.constant.Constant.*;

/**
 * @author Nadezhda Pupina
 * Service в "edo-service", создает связь контроллера и RestTemplate
 */
@Service
@AllArgsConstructor
@Log4j2
public class QuestionServiceImpl implements QuestionService {

    private final RestTemplate restTemplate;

    @Override
    public QuestionDto save(QuestionDto questionDto) {
        var builder = buildURI(EDO_REPOSITORY_NAME, QUESTION_URL);
        return restTemplate.postForObject(builder.toString(), questionDto, QuestionDto.class);
    }

    @Override
    public void delete(long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, QUESTION_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        restTemplate.delete(builder.toString());
    }

    @Override
    public String findById(long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, QUESTION_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), String.class);
    }

    @Override
    public Collection<QuestionDto> findByAllId(String ids) {
        var builder = buildURI(EDO_REPOSITORY_NAME, QUESTION_URL)
                .setPath("/all/")
                .setPath(String.valueOf(ids));
        return restTemplate.getForObject(builder.toString(), Collection.class);
    }

    @Override
    public void moveToArchived(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, QUESTION_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        restTemplate.postForObject(builder.toString(), null, String.class);
    }

    @Override
    public QuestionDto findByIdNotArchived(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, QUESTION_URL)
                .setPath("/notArchived/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), QuestionDto.class);
    }

    @Override
    public Collection<QuestionDto> findByAllIdNotArchived(String ids) {
        var builder = buildURI(EDO_REPOSITORY_NAME, QUESTION_URL)
                .setPath("/notArchivedAll/")
                .setPath(String.valueOf(ids));
        return restTemplate.getForObject(builder.toString(), Collection.class);
    }
}
