package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FacultyService {
    FacultyDto getFaculty(Long facultyId);

    List<FacultyDto> faculties();

    FacultyDto createFaculty(FacultyDto facultyDto);

    FacultyDto updateFaculty(Long facultyId, FacultyDto facultyDto);

    void deleteFaculty(Long facultyId);

    Page<Faculty> listPageAndSortingFacultyByBudgetPl(int page, int size);

    Page<Faculty> listPageAndSortingFacultyByTotalPl(int page, int size);

    List<Faculty> listPageAndSortingFacultyByFacultyId(int page, int size);
}
