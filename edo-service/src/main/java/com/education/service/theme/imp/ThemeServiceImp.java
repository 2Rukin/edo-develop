package com.education.service.theme.imp;

import com.education.service.theme.ThemeService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AppealDto;
import model.dto.ThemeDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

import static com.education.util.URIBuilderUtil.buildURI;
import static model.constant.Constant.*;

/**
 * @author AlexeySpiridonov
 */

@Service
@Log4j2
@AllArgsConstructor
public class ThemeServiceImp implements ThemeService {

    private final RestTemplate restTemplate;


    @Override
    public void save(ThemeDto themeDto) {
        log.info("sent a request to save the theme in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, THEME_URL);

        restTemplate.postForObject(builder.toString(), themeDto, ThemeDto.class);
        log.info("sent a request to save the theme in edo - repository");
    }

    @Override
    public void moveToArchived(Long id) {
        var builder = buildURI(EDO_REPOSITORY_NAME, THEME_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        restTemplate.postForObject(builder.toString(), null, ThemeDto.class);
        log.info("sent a request to archive the theme in edo - repository");
    }

    @Override
    public ThemeDto findById(Long id) {
        log.info("sent a request to receive the theme in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, THEME_URL)
                .setPath("/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), ThemeDto.class);
    }

    @Override
    public Collection<ThemeDto> findByAllId(String ids) {
        log.info("sent a request to receive the theme in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, THEME_URL)
                .setPath("/all/")
                .setPath(String.valueOf(ids));
        return restTemplate.getForObject(builder.toString(), List.class);
    }

    @Override
    public ThemeDto findByIdNotArchived(Long id) {
        log.info("sent a request to receive the theme not archived in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, THEME_URL)
                .setPath("/NotArchived/")
                .setPath(String.valueOf(id));
        return restTemplate.getForObject(builder.toString(), ThemeDto.class);
    }

    @Override
    public Collection<ThemeDto> findByAllIdNotArchived(String ids) {
        log.info("sent a request to receive the theme not archived in edo - repository");
        var builder = buildURI(EDO_REPOSITORY_NAME, THEME_URL)
                .setPath("/NotArchivedAll/")
                .setPath(String.valueOf(ids));
        return restTemplate.getForObject(builder.toString(), List.class);
    }
}
