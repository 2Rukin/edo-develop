package com.education.service.question.impl;

import com.education.entity.Question;
import com.education.repository.question.QuestionRepository;
import com.education.service.question.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author Nadezhda Pupina
 * Реализует связь контроллера и репозитория
 */
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Question save(Question question) {
        question.setCreationDate(ZonedDateTime.now());
        return questionRepository.saveAndFlush(question);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    @Override
    @Transactional(readOnly = true)
    public Question findById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> findAllById(Iterable<Long> ids) {
        return questionRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        questionRepository.moveToArchive(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Question findByIdAndArchivedDateNull(Long id) {
        return questionRepository.findByIdAndArchivedDateNull(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Question> findByAllIdNotArchived(Collection<Long> ids) {
        return questionRepository.findByIdInAndArchivedDateNull(ids);
    }

}
