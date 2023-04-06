package com.education.service.author;

import com.education.entity.Author;

import java.util.Collection;

public interface AuthorService {
    Author save(Author author);

    void delete(Long id);

    Author findById(Long id);

    Collection<Author> findAllById(Iterable<Long> ids);

    Collection<Author> findAll();
}
