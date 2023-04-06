package com.education.service.author;
import model.dto.AuthorDto;

import java.util.Collection;

public interface AuthorService {
    AuthorDto save(AuthorDto author);

    void delete(Long id);

    AuthorDto findById(Long id);

    Collection<AuthorDto> findAll();
}
