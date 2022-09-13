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
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<FacultyDto> getAllFaculties() {
        log.info("getAllFaculties");
        return facultyService.faculties();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FacultyDto createFaculty(@RequestBody FacultyDto facultyDto) {
        log.info("create faculty");
        return facultyService.createFaculty(facultyDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{facultyId}")
    public FacultyDto updateFaculty(@PathVariable("facultyId") Long facultyId, @RequestBody FacultyDto facultyDto) {
        log.info("update faculty with id {}", facultyId);
        return facultyService.updateFaculty(facultyId, facultyDto);
    }

    @DeleteMapping(value = "/{facultyId}")
    public void deleteFaculty(@PathVariable("facultyId") Long facultyId) {
        facultyService.deleteFaculty(facultyId);
    }
}
