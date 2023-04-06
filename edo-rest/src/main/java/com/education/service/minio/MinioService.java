package com.education.service.minio;

import model.dto.FilePoolDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


/**
 * Interface of MinioService for manipulating the request to another RestController from edo-file-Storage
 */
public interface MinioService {

    /**
     * Method upload the file to server MinIo.
     * Variable "objectName" means name of object, uploadibg to the bucket of minio server.
     */
    FilePoolDto uploadOneFile(MultipartFile objectName);

    /**
     * Method download the file from server MinIo.
     * Variable "objectName" means name of object,
     * downloading from the bucket of minio server
     * to target folder.
     */
    Resource downloadOneFile(String objectName);

    /**
     * Method of getting FilePool
     */
    FilePoolDto getFilePool(UUID uuid);

    /**
     * Method delete objects
     */
    void delete(String name);


}
