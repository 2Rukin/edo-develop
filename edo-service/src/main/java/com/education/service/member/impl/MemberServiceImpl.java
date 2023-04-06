package com.education.service.member.impl;

import com.education.service.member.MemberService;
import com.education.util.URIBuilderUtil;
import lombok.AllArgsConstructor;
import model.dto.MemberDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

import static model.constant.Constant.EDO_REPOSITORY_NAME;

/**
 * Сервис слой для взаимодействия с MemberDto
 */
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final RestTemplate restTemplate;

    /**
     * Отправляет post-запрос в edo-repository для сохранения участника
     */
    @Override
    public MemberDto save(MemberDto memberDto) {

        // Установка даты создания для нового участника
        if (memberDto.getCreationDate() == null) {
            memberDto.setCreationDate(ZonedDateTime.now());
        }

        String uri = URIBuilderUtil.buildURI(EDO_REPOSITORY_NAME, "/api/repository/member").toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(memberDto, headers), MemberDto.class).getBody();
    }

    /**
     * Отправляет post-запрос в edo-repository для сохранения участника со ссылкой на блок согласования
     */
    @Override
    public MemberDto save(MemberDto memberDto, Long approvalBlockId) {

        // Установка даты создания для нового участника
        if (memberDto.getCreationDate() == null) {
            memberDto.setCreationDate(ZonedDateTime.now());
        }

        // Установка даты создания для участника
        memberDto.setCreationDate(ZonedDateTime.now());

        String uri = URIBuilderUtil.buildURI(EDO_REPOSITORY_NAME, "/api/repository/member/saveWithLinkToApprovalBlock/" + approvalBlockId).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(memberDto, headers), MemberDto.class).getBody();
    }

    /**
     * Отправляет get-запрос в edo-repository для получения участника по индексу
     */
    @Override
    public MemberDto findById(Long id) {
        String uri = URIBuilderUtil.buildURI(EDO_REPOSITORY_NAME, "/api/repository/member/" + id).toString();

        return restTemplate.getForObject(uri, MemberDto.class);
    }

    /**
     * Отправляет get-запрос в edo-repository для получения всех участников
     */
    @Override
    public Collection<MemberDto> findAll() {
        String uri = URIBuilderUtil.buildURI(EDO_REPOSITORY_NAME, "/api/repository/member/all").toString();

        return restTemplate.getForObject(uri, List.class);
    }

    /**
     * Отправляет get-запрос в edo-repository для получения всех участников с полученными индексами
     */
    @Override
    public Collection<MemberDto> findAllById(Iterable<Long> ids) {
        String uri = URIBuilderUtil.buildURI(EDO_REPOSITORY_NAME, "/api/repository/member/all/" + ids).toString();

        return restTemplate.getForObject(uri, List.class);
    }

    /**
     * Отправляет delete-запрос в edo-repository для удаления участника по индексу
     */
    @Override
    public void delete(Long id) {
        String uri = URIBuilderUtil.buildURI(EDO_REPOSITORY_NAME, "/api/repository/member/" + id).toString();

        restTemplate.delete(uri);
    }
}
