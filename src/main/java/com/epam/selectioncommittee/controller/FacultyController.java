package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.service.FacultyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class FacultyController {
    private final FacultyService facultyService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/faculty")
    public List<FacultyDto> getAllFaculties() {
        log.info("getAllFaculties");
        return facultyService.faculties();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/faculty")
    public FacultyDto createFaculty(@RequestBody FacultyDto facultyDto) {
        log.info("create faculty");
        return facultyService.createFaculty(facultyDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/faculty/{name}")
    public FacultyDto updateFaculty(@PathVariable("name") String name, @RequestBody FacultyDto facultyDto) {
        log.info("update faculty with name {}", name);
        return facultyService.updateFaculty(name, facultyDto);
    }

    @DeleteMapping(value = "/faculty/{name}")
    public void deleteFaculty(@PathVariable String name) {
        facultyService.deleteFaculty(name);
    }
}
