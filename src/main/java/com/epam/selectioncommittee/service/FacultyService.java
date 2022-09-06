package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.FacultyDto;

import java.util.List;

public interface FacultyService {
    FacultyDto getFaculty(Long facultyId);

    List<FacultyDto> faculties();

    FacultyDto createFaculty(FacultyDto facultyDto);

    FacultyDto updateFaculty(Long facultyId, FacultyDto facultyDto);

    void deleteFaculty(Long facultyId);
}
