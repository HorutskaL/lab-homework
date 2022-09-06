package com.epam.selectioncommittee.repository;

import com.epam.selectioncommittee.model.Faculty;

import java.util.List;

public interface FacultyRepository {
    Faculty createFaculty(Faculty faculty);

    List<Faculty> getAllFaculties();

    Faculty getFacultyById(Long facultyId);

    void deleteFaculty(Long facultyId);

    Faculty updateFaculty(Long facultyId, Faculty faculty);

}
