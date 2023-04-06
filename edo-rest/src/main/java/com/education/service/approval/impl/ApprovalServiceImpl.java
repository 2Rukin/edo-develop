package com.education.service.approval.impl;

import com.education.service.approval.ApprovalService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import model.dto.ApprovalDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import static model.constant.Constant.EDO_SERVICE_NAME;

@Service
@AllArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {
    private final RestTemplate restTemplate;

    @Override
    public ApprovalDto save(ApprovalDto approvalDto) {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval").toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(approvalDto, headers), ApprovalDto.class).getBody();
    }

    @Override
    public ApprovalDto findById(Long id) {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/" + id).toString();

        return restTemplate.getForObject(uri, ApprovalDto.class);
    }

    @Override
    public Collection<ApprovalDto> findAll() {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/all").toString();

        return restTemplate.getForObject(uri, List.class);
    }

    @Override
    public Collection<ApprovalDto> findAllById(Iterable<Long> ids) {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/all/" + ids).toString();

        return restTemplate.getForObject(uri, List.class);
    }

    @Override
    public void delete(Long id) {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/" + id).toString();

        restTemplate.delete(uri);
    }

    @Override
    public void moveToArchive(Long id) {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/move/" + id).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.exchange(uri, HttpMethod.PATCH, new HttpEntity<>(headers), ApprovalDto.class).getBody();
    }

    @Override
    public ApprovalDto findByIdNotArchived(Long id) {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/findByIdNotArchived/" + id).toString();

        return restTemplate.getForObject(uri, ApprovalDto.class);
    }

    @Override
    public Collection<ApprovalDto> findAllNotArchived() {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/findAllNotArchived").toString();

        return restTemplate.getForObject(uri, List.class);
    }

    @Override
    public Collection<ApprovalDto> findByIdInAndArchivedDateNull(Iterable<Long> ids) {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/archive/all/findByIdInAndArchivedDateNull/" + ids).toString();

        return restTemplate.getForObject(uri, List.class);
    }

    @Override
    public void sendForApproval(Long id) {
        String uri = URIBuilderUtil.buildURI(EDO_SERVICE_NAME, "/api/service/approval/send/" + id).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(headers), ApprovalDto.class).getBody();
    }
}
