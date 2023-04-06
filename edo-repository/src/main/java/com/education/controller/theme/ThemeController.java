package com.education.controller.theme;

import com.education.entity.Theme;
import com.education.service.theme.ThemeService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.ThemeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static com.education.mapper.ThemeMapper.THEME_MAPPER;

/**
 * @author AlexeySpiridonov
 * Rest-контроллер отправляет запрос от клиента в бд
 */
@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/repository/theme")
public class ThemeController {

    private final ThemeService themeService;

    @ApiOperation(value = "Создает тему обращения", notes = "Тема обращения должна существовать")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Theme>  save(@RequestBody ThemeDto themeDto) {
        log.info("Starting the save operation");
        themeService.save(THEME_MAPPER.toEntity(themeDto));
        log.info("POST request successful");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Удаляет тему обращения", notes = "Тема обращения должна существовать")
    @DeleteMapping("/{id}")
    public ResponseEntity<ThemeDto> delete(@PathVariable Long id) {
        log.info("DELETE: /api/repository/filePool/" + id);
        themeService.delete(id);
        log.info("DELETE request successful");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @ApiOperation(value = "Gets authors by id", notes = "Author must exist")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThemeDto> findById(@PathVariable Long id) {
        log.info("Sent GET request to get author with id={} from the database", id);
        var themeDto = THEME_MAPPER.toDto(themeService.findById(id));
        log.info("Response from database:{}", themeDto);
        return new ResponseEntity<>(themeDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Выводит id всех файлов", notes = "Файлы должны существовать")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ThemeDto>> findAll() {
        log.info("Sent GET request to get all authors from the database");
        var themeDtoCollection = THEME_MAPPER.toDto(themeService.findAll());
        log.info("Response from database:{}");
        return new ResponseEntity<>(themeDtoCollection, HttpStatus.OK);
    }

    @ApiOperation(value = "Добавляет в тему дату архивации", notes = "Тема должна существовать")
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> moveToArchive(@PathVariable(name = "id") Long id) {
        log.info("Starting the archiving theme");
        themeService.moveToArchive(id);
        log.info("Archiving a theme");
        return new ResponseEntity<>("The theme is archived", HttpStatus.OK);
    }


        @ApiOperation(value = "Предоставление темы без архивации")
    @GetMapping("/noArchived/{id}")
    private ResponseEntity<ThemeDto> getThemeNotArchived(@PathVariable Long id) {
        return new ResponseEntity<>(THEME_MAPPER.toDto(themeService.findByIdAndArchivedDateNull(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Предоставление тем без архивации")
    @GetMapping("/noArchived/{ids}")
    private  ResponseEntity<Collection<ThemeDto>> getThemesNotArchived(@PathVariable List<Long> ids) {
        Collection<ThemeDto> themeDto = THEME_MAPPER.toDto(themeService.findByIdInAndArchivedDateNull(ids));
        return  new ResponseEntity<>(themeDto,HttpStatus.OK);
    }

}
