package com.education.service.theme.imp;

import com.education.entity.Theme;
import com.education.repository.theme.ThemeRepository;
import com.education.service.theme.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author AlexeySpiridonov
 */
@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Theme theme) {
        themeRepository.save(theme);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        themeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Theme findById(Long id) {
        return themeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Theme> findAllById(Iterable<Long> ids) {
        return themeRepository.findAllById(ids);
    }

    @Override
    @Cacheable(cacheNames = {"themeCache"})
    @Transactional(readOnly = true)
    public Collection<Theme> findAll() {
        return themeRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        themeRepository.moveToArchive(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Theme findByIdAndArchivedDateNull(Long id) {
        return themeRepository.findByIdAndArchivedDateNull(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Theme> findByIdInAndArchivedDateNull(Iterable<Long> ids) {
        return themeRepository.findByIdInAndArchivedDateNull(ids);
    }
}
