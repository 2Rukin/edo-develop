package com.education.controller.author;

import com.education.entity.Author;
import com.education.service.author.AuthorService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.AuthorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.education.mapper.AuthorMapper.AUTHOR_MAPPER;

/**
 * Рест-контроллер для Author
 */

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/repository/author")
public class AuthorRestController {

    private final AuthorService authorService;

    @ApiOperation(value = "Gets all authors", notes = "Author must exist")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<AuthorDto>> getAllAuthors() {
        log.info("Sent GET request to get all authors from the database");
        var authorDtoCollection = AUTHOR_MAPPER.toDto(authorService.findAll());
        log.info("Response from database:{}", authorDtoCollection);
        return new ResponseEntity<>(authorDtoCollection, HttpStatus.OK);
    }

    @ApiOperation(value = "Add author", notes = "Author not must exist")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> addAuthorAction(@RequestBody AuthorDto authorDto) {
        log.info("Send POST request to add author to databases: {}", authorDto);
        Author save = authorService.save(AUTHOR_MAPPER.toEntity(authorDto));
        log.info("Author added to database");
        return new ResponseEntity<>(AUTHOR_MAPPER.toDto(save),HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete author", notes = "Author must exist")
    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable Long id) {
        log.info("Send DELETE request to delete author with id={} to databases", id);
        authorService.delete(id);
        log.info("Author was deleted from the database");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Gets authors by id", notes = "Author must exist")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        log.info("Sent GET request to get author with id={} from the database", id);
        var authorDto = AUTHOR_MAPPER.toDto(authorService.findById(id));
        log.info("Response from database:{}", authorDto);
        return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }
}
