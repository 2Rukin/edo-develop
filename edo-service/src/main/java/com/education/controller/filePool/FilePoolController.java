package com.education.controller.filePool;

import com.education.service.filePool.FilePoolService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.dto.FilePoolDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/service/filePool")
public class FilePoolController {

    private final FilePoolService filePoolService;

    @ApiOperation(value = "Add file", notes = "file not must exist")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilePoolDto> save(@RequestBody FilePoolDto filePoolDto) {
        log.info("Send POST request to add file to databases: {}", filePoolDto);
        filePoolService.save(filePoolDto);
        log.info("file added to database");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete file", notes = "file must exist")
    @DeleteMapping("/{id}")
    public ResponseEntity<FilePoolDto> delete(@PathVariable Long id) {
        log.info("Send DELETE request to delete file with id={} to databases", id);
        filePoolService.delete(id);
        log.info("file was deleted from the database");
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Gets files by id", notes = "file must exist")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findById(@PathVariable("id") long id) {
        log.info("Send a get-request to get file with id = {} from edo-repository ", id);
        var filePoolDto = filePoolService.findById(id);
        log.info("Response from edo-repository: {}", filePoolDto);
        return new ResponseEntity<>(filePoolDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all files", notes = "file must exist")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findAll() {
        log.info("Sent GET request to get all file from the database");
        var fileDto = filePoolService.findAll();
        log.info("Response from database:{}", fileDto);
        return new ResponseEntity<>(fileDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Providing files by assigned IDs")
    @GetMapping("/all/{ids}")
    private ResponseEntity<Collection<FilePoolDto>> findAllById(@PathVariable(name = "ids") String ids) {
        log.info("Send a response with the files of the assigned IDs");
        var fileDto = filePoolService.findAllById(ids);
        log.info("Response from database:{}", fileDto);
        return new ResponseEntity<>(fileDto, HttpStatus.OK);
    }


    @ApiOperation(value = "Archiving a file with adding archive time\n")
    @PostMapping("/{id}")
    private String moveToArchive(@PathVariable(name = "id") Long id) {
        log.info("Start update a file");
        filePoolService.moveToArchive(id);
        log.info("Update a file");
        return "Delete " + id;
    }

    @ApiOperation(value = "Providing a file without archiving by id")
    @GetMapping("/NotArchived/{id}")
    private ResponseEntity<FilePoolDto> findByIdNotArchived(@PathVariable(name = "id") Long id) {
        log.info("send a response with the file not archived of the assigned ID");
        return new ResponseEntity<>(filePoolService.findByIdNotArchived(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Providing files without archiving by assigned ids")
    @GetMapping("/NotArchivedAll/{ids}")
    private ResponseEntity<Collection<FilePoolDto>> findAllByIdNotArchived(@PathVariable(name = "ids") String ids) {
        log.info("send a response with the files not archived of the assigned IDs");
        return new ResponseEntity<>(filePoolService.findAllByIdNotArchived(ids), HttpStatus.OK);
    }

}
