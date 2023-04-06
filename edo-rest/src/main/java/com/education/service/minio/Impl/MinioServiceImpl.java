package com.education.service.minio.Impl;

import com.education.service.minio.MinioService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import model.dto.FilePoolDto;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static model.constant.Constant.EDO_SERVICE_NAME;


@Service
@AllArgsConstructor
public class MinioServiceImpl implements MinioService {

    private RestTemplate restTemplate;

    @Override
    public FilePoolDto uploadOneFile(MultipartFile currentFile){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", currentFile.getResource());
        body.add("name", currentFile.getOriginalFilename());

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(getUri("/api/service/minio/upload"),
                requestEntity,
                FilePoolDto.class);
    }

    private static String getUri(String path) {
        return URIBuilderUtil.buildURI(EDO_SERVICE_NAME, path)
                .toString();
    }

    @Override
    public Resource downloadOneFile(String objectName) {
        String uri = getUri("/api/service/minio/download/") + objectName;
        return restTemplate.getForObject(uri, Resource.class);
    }

    @Override
    public FilePoolDto getFilePool(UUID uuid){
        String uri = getUri("/api/service/minio/info/") + uuid;
        return restTemplate.getForObject(uri, FilePoolDto.class);
    }

    @Override
    public void delete(String name) {
        restTemplate.delete(getUri("/api/service/minio/delete/" + name), Void.class);
    }


}
