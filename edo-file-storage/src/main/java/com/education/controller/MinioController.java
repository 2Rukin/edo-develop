package com.education.controller;

import com.education.component.MinioComponent;

import com.education.exceptions.MinIOPutException;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;


/**
 * RestController of edo-file-storage.
 */
@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/file-storage")
public class MinioController {

    private MinioComponent minioComponent;

    /**
     * Request to upload file from bucket of MINIO-server.
     * Request consist of object`s name.
     */
    @ApiOperation("send request to upload file to buckets from source")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String uploadFileToMinIO(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("key") String key) {

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        try (var convertedFile = minioComponent.convertFileToPDF(file, extension)) {
            String contentType = minioComponent.getFileContentType(file, extension);
            log.info("Uploading file with key: {}; And type: {}", key, contentType);
            minioComponent.postObject(
                    minioComponent.getFileName(key, extension),
                    convertedFile,
                    contentType);
            return contentType;
        } catch (IOException e) {
            log.error("bed request");
            return "Something wrong.";
        }

    }

    /**
     * Request to download file from MINIO-server.
     * Request consist of object`s name.
     */
    @ApiOperation("send request to download file from server`s")
    @GetMapping(value = "/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable("id") String fileName) {
        log.info("Download file :  {}", fileName);
        InputStream is = minioComponent.getObject(fileName);
        return ResponseEntity.ok()
                .body(new InputStreamResource(is));
    }


    /**
     * Request to delete old file in the MINIO-server`s bucket
     */
    @ApiOperation("send request to upload file to buckets from source")
    @DeleteMapping("/delete/{storageFileId}")
    public ResponseEntity delete(@PathVariable("storageFileId") String storageFileId) {
        log.info("Delete outdated objects in MINIO-server");
        InputStream is = minioComponent.getObject(storageFileId);
        if(is != null){
            minioComponent.deleteObjects(storageFileId);
            log.info("Delete outdated objects in MINIO-server successful");
            return ResponseEntity.ok().body("File is deleted");
        } else {
            log.warn("Delete outdated objects in MINIO-server went wrong");
            return ResponseEntity.badRequest().body("File with name " + storageFileId + " is not found");
        }
    }

    /**
     * Request, checking the connection to MINIO-server
     */
    @ApiOperation("Checking connection to MinIo server.")
    @GetMapping("/checkConnection")
    public ResponseEntity checkConnection() {
        log.info("Checking connection");
        try {
            minioComponent.checkConnection();
        } catch (MinIOPutException e){
            log.warn("Connection is not established");
            return ResponseEntity.internalServerError().body("Connection is not established. Reason: " + e.getMessage());
        }
        log.info("Connection checked");
        return ResponseEntity.ok().body("Connection checked");
    }
}
