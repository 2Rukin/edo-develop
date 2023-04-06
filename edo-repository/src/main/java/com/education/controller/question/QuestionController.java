package com.education.controller.question;

import com.education.service.question.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.QuestionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.education.mapper.QuestionMapper.QUESTION_MAPPER;

/**
 * @author Nadezhda Pupina
 * Rest-контроллер в "edo-repository", необходим для отправки запросов от клиента к БД
 */
@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/repository/question")
public class QuestionController {

    private final QuestionService questionService;

    //GET ONE /api/repository/question/{id}
    @ApiOperation(value = "Возвращает вопрос по id", notes = "Вопрос должен существовать")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<QuestionDto> findById(@PathVariable(name = "id") Long id) {
        log.info("Send a get-request to get Question with id = {} from database", id);
        var questionDto = QUESTION_MAPPER.toDto(questionService.findById(id));
        log.info("The operation was successful, we got the Question by id ={}", id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    //GET ALL /api/repository/question/all/{ids}
    @ApiOperation(value = "Возвращает все вопросы", notes = "Вопросы должны существовать")
    @GetMapping("/all/{ids}")
    private ResponseEntity<List<QuestionDto>> findAll(@PathVariable(name = "ids") List<Long> ids) {
        log.info("Send a get-request to get all Question from database");
        var questionDtos = (List<QuestionDto>) QUESTION_MAPPER.toDto(questionService.findAll());
        log.info("The operation was successful, they got the non-archived Question by id ={}", ids);
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    //POST /api/repository/question
    @ApiOperation(value = "Создает вопрос в БД", notes = "Вопрос должен существовать")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionDto> save(@RequestBody QuestionDto questionDto) {
        log.info("Send a post-request to post new Question to database");
        var saved = questionService.save(QUESTION_MAPPER.toEntity(questionDto));
        log.info("Response: {} was added to database", questionDto);
        return new ResponseEntity<>(QUESTION_MAPPER.toDto(saved), HttpStatus.CREATED);
    }

    //DELETE /api/repository/question/{id}
    @ApiOperation(value = "Удаляет вопрос из БД", notes = "Вопрос должен существовать")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") long id) {
        log.info("Send a delete-request to delete Question with id = {} from database", id);
        questionService.delete(questionService.findById(id));
        log.info("Response: Question with id = {} was deleted from database", id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //POST /api/repository/question/move/{id}
    @ApiOperation(value = "Добавляет в вопрос дату архивации", notes = "Вопрос должен существовать")
    @PostMapping("/move/{id}")
    private ResponseEntity<QuestionDto> moveToArchive(@PathVariable(name = "id") Long id) {
        log.info("Starting the archiving operation");
        questionService.moveToArchive(id);
        log.info("Added archiving date");
        QuestionDto questionDto = QUESTION_MAPPER.toDto(questionService.findById(id));
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    //GET ONE WITHOUT ARCHIVED DATE /api/repository/question/notArchived/{id}
    @ApiOperation(value = "Возвращает вопрос без архивации по id", notes = "Вопрос должен существовать")
    @GetMapping("/notArchived/{id}")
    private ResponseEntity<QuestionDto> findByIdAndArchivedDateNull(@PathVariable(name = "id") Long id) {
        log.info("send a response with the Question not archived of the assigned ID");
        QuestionDto questionDto = QUESTION_MAPPER.toDto(questionService.findByIdAndArchivedDateNull(id));
        log.info("The operation was successful, they got the non-archived Question by id ={}", id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    //GET ALL WITHOUT ARCHIVED DATE /api/repository/question/notArchivedAll/{ids}
    @ApiOperation(value = "Возвращает вопросы без архивации по id", notes = "Вопросы должны существовать")
    @GetMapping("/notArchivedAll/{ids}")
    private ResponseEntity<List<QuestionDto>> findByAllIdNotArchived(@PathVariable(name = "ids") List<Long> ids) {
        log.info("send a response with the Question not archived of the assigned IDs");
        List<QuestionDto> questionDto = (List<QuestionDto>) QUESTION_MAPPER.toDto(questionService.findByAllIdNotArchived(ids));
        log.info("The operation was successful, they got the non-archived Question by id ={}", ids);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

}
