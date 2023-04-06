package com.education.service.filePool.impl;

import com.education.entity.FilePool;
import com.education.repository.filePool.FilePoolRepository;
import com.education.service.filePool.FilePoolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

/**
 * @author Nadezhda Pupina
 * Сервис реализует методы jpa repository и обычные методы
 */
@Service
@AllArgsConstructor
public class FilePoolServiceImpl implements FilePoolService {
    private FilePoolRepository filePoolRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FilePool save(FilePool filePool) {
        filePool.setUploadDate(ZonedDateTime.now());
        return filePoolRepository.saveAndFlush(filePool);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        filePoolRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public FilePool findById(Long id) {
        return filePoolRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public FilePool findByUuid(UUID uuid) {
        return filePoolRepository.getFilePoolByStorageFileId(uuid);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<FilePool> findAllById(Iterable<Long> ids) {
        return filePoolRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<FilePool> findAll() {
        return filePoolRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveToArchive(Long id) {
        filePoolRepository.moveToArchived(id);
    }

    @Override
    @Transactional(readOnly = true)
    public FilePool findByIdAndArchivedDateNull(Long id) {
        return filePoolRepository.findByIdAndArchivedDateNull(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<FilePool> findByIdInAndArchivedDateNull(Iterable<Long> ids) {
        return filePoolRepository.findByIdInAndArchivedDateNull(ids);
    }

}
