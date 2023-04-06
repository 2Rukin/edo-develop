package com.education.service.question;

import model.dto.QuestionDto;

import java.util.Collection;

/**
 * @author Nadezhda Pupina
 * Service в "edo-service", создает связь контроллера и RestTemplate
 */
public interface QuestionService {

    /**
     * Метод сохранения нового вопроса в БД
     */
    QuestionDto save(QuestionDto questionDto);

    /**
     * Метод удаления вопроса из БД
     */
    void delete(long id);

    /**
     * Метод возвращает вопрос по Id
     */
    String findById(long id);

    /**
     * Метод возвращает все вопросы
     */
    Collection<QuestionDto> findByAllId(String ids);

    /**
     * Метод заполняет поле значением и установленной датой
     */
    void moveToArchived(Long id);

    /**
     * Метод ищет объект Question, у которого поле archiveDate имеет значение null
     */
    QuestionDto findByIdNotArchived(Long id);

    /**
     * Метод ищет сущности Question, у которых поля archiveDate имеют значение null
     */
    Collection<QuestionDto> findByAllIdNotArchived(String ids);

}
