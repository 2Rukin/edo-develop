package com.education.service.minio;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface MinioService {
    /**
     * Method upload the file to server MinIo.
     * Variable "objectName" means name of object, uploadibg to the bucket of minio server.
     */
    ResponseEntity<String> uploadOneFile(MultipartFile objectName, UUID UUIDKey,
                                         String fileName, String contentType) throws IOException;

    /**
     * Method download the file from server MinIo.
     * Variable "objectName" means name of object,
     * downloading from the bucket of minio server
     * to target folder.
     */
    Resource downloadOneFile(String objectName);

    /**
     * Method delete objects
     */
    void delete(String name);
}
