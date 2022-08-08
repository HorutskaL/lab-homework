package com.epam.selectioncommittee.repository;

import com.epam.selectioncommittee.model.Faculty;

import java.util.List;

public interface FacultyRepository {
    Faculty createFaculty(Faculty faculty);

    List<Faculty> getAllFaculties();

    Faculty getFacultyByName(String name);

    void deleteFaculty(String name);

    Faculty updateFaculty(String name, Faculty faculty);

}
