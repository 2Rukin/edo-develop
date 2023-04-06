package com.education.controller.minio;

import com.education.service.minio.MinioService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.FilePoolDto;
import model.file.ByteArrayFileContainer;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * RestController of edo-rest for redirect
 * request to rest-controller of edo-file-storage.
 */
@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/api/rest/minio")
public class MinIOController {

    private MinioService service;

    /**
     * Redirect request to upload file from bucket of MINIO-server.
     * Request consist of object`s name.
     */
    @ApiOperation("send request to upload file to buckets from source")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public FilePoolDto uploadOneFile(@RequestParam(value = "file", required = false) MultipartFile file) {
        log.info("Upload file named: {}", file.getOriginalFilename());
        return service.uploadOneFile(file);
    }

    /**
     * Redirect request to download file from MINIO-server.
     * Request consist of object`s name.
     */
    @ApiOperation("send request to download file from server`s buckets to target folder")
    @GetMapping(value = "/download/{name}")
    public ResponseEntity<InputStreamResource> downloadOneFile(@PathVariable("name") String name) {
        log.info("Download file named: {}", name);
        var uuid = UUID.fromString(name);
        var filePoolDto = service.getFilePool(uuid);
        try (var is = service.downloadOneFile(name).getInputStream();
             var file = new ByteArrayFileContainer(is.readAllBytes(), filePoolDto.getName())) {
            final var octetStreamHeaders = createOctetStreamHeaders(filePoolDto.getName());
            final var fileResource = new InputStreamResource(file.getInputStream());
            return new ResponseEntity<>(fileResource, octetStreamHeaders, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception while downloading: ", e);
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    /**
     * Request to delete old file in the MINIO-server`s bucket
     */
    @ApiOperation("send request to upload file to bucjets from source")
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity delete(@PathVariable("fileName") String fileName) {
        log.info("delete file: {}", fileName);
        service.delete(fileName);
        log.info("delete file: {}", fileName);
        return ResponseEntity.ok().body("File is deleted");
    }

    public MultiValueMap<String, String> createOctetStreamHeaders(final String name) {
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(CONTENT_DISPOSITION,
                "attachment; filename*=utf-8''" + UriUtils.encodeScheme(name, "UTF-8")
        );
        headers.add(SET_COOKIE, "fileDownload=true; path=/");
        return headers;
    }
}
