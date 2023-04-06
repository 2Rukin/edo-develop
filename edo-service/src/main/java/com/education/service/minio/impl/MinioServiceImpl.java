package com.education.service.minio.impl;

import com.education.service.minio.MinioService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static model.constant.Constant.EDO_FILE_STORAGE_NAME;

/**
 * @author Anna Artemyeva
 */
@Service
@Log4j2
@AllArgsConstructor
public class MinioServiceImpl implements MinioService {
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> uploadOneFile(MultipartFile currentFile,
                                                UUID UUIDKey, String fileName, String contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        var body = new LinkedMultiValueMap<>();
        body.add("file", currentFile.getResource());
        body.add("key", UUIDKey.toString());
        body.add("fileName", fileName);
        var requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(getUri("/api/file-storage/upload"),
                requestEntity,
                String.class);
    }

    private static String getUri(String path) {
        return URIBuilderUtil.buildURI(EDO_FILE_STORAGE_NAME, path)
                .toString();
    }

    @Override
    public Resource downloadOneFile(String objectName) {
        String uri = getUri("/api/file-storage/download/") + objectName;
        return restTemplate.getForObject(uri, Resource.class);
    }

    @Override
    public void delete(String name) {
        restTemplate.delete(getUri("/api/file-storage/delete/" + name), Void.class);
    }
}
