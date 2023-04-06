package com.education.service.appeal.impl;

import com.education.service.appeal.AppealService;
import com.education.service.author.AuthorService;
import com.education.service.filePool.FilePoolService;
import com.education.service.question.QuestionService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import model.constant.Constant;
import model.dto.AppealDto;
import model.dto.AuthorDto;
import model.dto.FilePoolDto;
import model.dto.QuestionDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.education.util.URIBuilderUtil.buildURI;

import static model.constant.Constant.APPEAL_URL;
import static model.constant.Constant.EDO_REPOSITORY_NAME;
import static model.enum_.Status.NEW_STATUS;

/**
 * Сервис-слой для Appeal
 */
@Service
@AllArgsConstructor
public class AppealServiceImpl implements AppealService {

    private final RestTemplate restTemplate;

    private final AuthorService authorService;
    private final QuestionService questionService;
    private final FilePoolService filePoolService;


    /**
     * Нахождение обращения по id
     */
    @Override
    public AppealDto findById(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, APPEAL_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), AppealDto.class);
    }

    /**
     * Нахождение всех обращений
     */
    @Override
    public Collection<AppealDto> findAll() {
        var builder = buildURI(EDO_REPOSITORY_NAME, APPEAL_URL);
        return restTemplate.getForObject(builder.toString(), Collection.class);
    }

    /**
     * Изменение обращения, добавление status,маппинг  Authors,Questions
     */
    @Override
    public AppealDto save(AppealDto appealDto) {
        // Назначения статуса и времени создания
        if(appealDto.getAppealsStatus()==null) {
            appealDto.setAppealsStatus(NEW_STATUS);
            appealDto.setCreationDate(ZonedDateTime.now());
        }
        // Списки, которые хранят, новые сущности
        List<AuthorDto> savedAuthors = new ArrayList<>();
        List<QuestionDto> savedQuestions = new ArrayList<>();
        List<FilePoolDto> savedFiles = new ArrayList<>();

        try {
            // Сохранение новых авторов
            appealDto.setAuthors(appealDto.getAuthors().stream()
                    .map(authorDto -> {
                        if (authorDto.getId() == null) {
                            authorDto = authorService.save(authorDto);
                            savedAuthors.add(authorDto);
                        }
                        return authorDto;
                    })
                    .collect(Collectors.toList()));

            // Сохранение новых вопросов
            appealDto.setQuestions(appealDto.getQuestions().stream()
                    .map(questionDto -> {
                        if (questionDto.getId() == null) {
                            questionDto = questionService.save(questionDto);
                            savedQuestions.add(questionDto);
                        }
                        return questionDto;
                    })
                    .collect(Collectors.toList()));

            // Сохранение новых файлов
            appealDto.setFile(appealDto.getFile().stream()
                    .map(filePoolDto -> {
                        if (filePoolDto.getId() == null) {
                            filePoolDto = filePoolService.save(filePoolDto);
                            savedFiles.add(filePoolDto);
                        }
                        return filePoolDto;
                    })
                    .collect(Collectors.toList()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String uri = URIBuilderUtil.buildURI(Constant.EDO_REPOSITORY_NAME, "api/repository/appeal").toString();
            return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(appealDto, headers), AppealDto.class).getBody();

        } catch (Exception e) {

            // Удаление сохранённых вложенных сущностей
            savedAuthors.forEach(authorDto -> {
                authorService.delete(authorDto.getId());
            });
            savedFiles.forEach(filePoolDto -> {
                filePoolService.delete(filePoolDto.getId());
            });
            savedQuestions.forEach(questionDto -> {
                questionService.delete(questionDto.getId());
            });

            throw e;
        }
    }

    /**
     * Удаления обращения по Id
     */
    @Override
    public void delete(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, APPEAL_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        restTemplate.delete(builder.toString());
    }

    /**
     * Перенос обращения в архив по id
     */
    @Override
    public void moveToArchive(Long id) {
        AppealDto appeal = findById(id);
        appeal.setArchivedDate(ZonedDateTime.now());
        var builder = buildURI(EDO_REPOSITORY_NAME, APPEAL_URL)
                .setPath("/move/")
                .setPath(String.valueOf(id));
        restTemplate.put(builder.toString(), appeal);
    }

    /**
     * Нахождение обращения по id не из архива
     */
    @Override
    public AppealDto findByIdNotArchived(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, APPEAL_URL)
                .setPath("/findByIdNotArchived/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), AppealDto.class);
    }

    /**
     * Нахождение всех обращений не из архива
     */
    @Override
    public Collection<AppealDto> findAllNotArchived() {
        var builder = buildURI(EDO_REPOSITORY_NAME, APPEAL_URL)
                .setPath("/findByIdNotArchived");
        return restTemplate.getForObject(builder.toString(), Collection.class);
    }

    /**
     * Метод достает Appeal по Questions id
     */
    @Override
    public AppealDto findAppealByQuestionsId(Long id){
        String URL = URIBuilderUtil.buildURI(Constant.EDO_REPOSITORY_NAME, "api/repository/appeal/findAppealByQuestionsId/"+ id).toString();
        return restTemplate.getForObject(URL, AppealDto.class);

    }

}
