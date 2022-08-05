package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.FacultyDto;

import java.util.List;

public interface FacultyService {
    FacultyDto getFaculty(String name);

    List<FacultyDto> faculties();

    FacultyDto createFaculty(FacultyDto facultyDto);

    FacultyDto updateFaculty(String name, FacultyDto facultyDto);

    void deleteFaculty(String name);
}
