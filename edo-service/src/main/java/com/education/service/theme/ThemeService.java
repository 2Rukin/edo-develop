package com.education.service.theme;

import model.dto.ThemeDto;

import java.util.Collection;

/**
 * @author AlexeySpiridonov
 */

public interface ThemeService {
    void save(ThemeDto themeDto);

    void moveToArchived(Long id);

    ThemeDto findById(Long id);

    Collection<ThemeDto> findByAllId(String ids);

    ThemeDto findByIdNotArchived(Long id);

    Collection<ThemeDto> findByAllIdNotArchived(String ids);
}
