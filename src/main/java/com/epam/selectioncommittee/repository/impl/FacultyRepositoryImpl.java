package com.epam.selectioncommittee.repository.impl;

import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.repository.FacultyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class FacultyRepositoryImpl implements FacultyRepository {
    private final List<Faculty> list = new ArrayList<>();

    @Override
    public Faculty createFaculty(Faculty faculty) {
        list.add(faculty);
        log.info("Created Faculty ", faculty);
        return faculty;
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return new ArrayList<>(list);
    }

    @Override
    public Faculty getFacultyById(Long facultyId) {
        log.info("Get faculty with id: {}", facultyId);
        return list.stream()
                .filter(faculty -> faculty.getId().equals(facultyId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Faculty is not found!"));
    }

    @Override
    public Faculty updateFaculty(Long facultyId, Faculty faculty) {

        boolean isDeleted = list.removeIf(u -> u.getId().equals(facultyId));
        if (isDeleted) {
            list.add(faculty);
        } else {
            throw new RuntimeException("Faculty is not found!");
        }
        log.info("Faculty with id {} was updated", facultyId);
        return faculty;
    }

    @Override
    public void deleteFaculty(Long facultyId) {
        list.removeIf(faculty -> faculty.getId().equals(facultyId));
        log.info("Faculty with id {} was deleted", facultyId);
    }
}
