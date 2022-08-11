package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.api.FacultyApi;
import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.service.FacultyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j

public class FacultyController implements FacultyApi {
    private final FacultyService facultyService;

    @Override
    public List<FacultyDto> getAllFaculties() {
        log.info("getAllFaculties");
        return facultyService.faculties();
    }

    @Override
    public FacultyDto createFaculty(FacultyDto facultyDto) {
        log.info("create faculty");
        return facultyService.createFaculty(facultyDto);
    }

    @Override
    public FacultyDto updateFaculty(String name, FacultyDto facultyDto) {
        log.info("update faculty with name {}", name);
        return facultyService.updateFaculty(name, facultyDto);
    }

    @Override
    public void deleteFaculty(String name) {
        facultyService.deleteFaculty(name);
    }

}
