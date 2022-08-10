package com.epam.selectioncommittee.repository.impl;

import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.service.exception.FacultyNotFoundException;
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
    public Faculty getFacultyByName(String name) {
        log.info("Get faculty with {}", name);
        return list.stream()
                .filter(faculty -> faculty.getName().equals(name))
                .findFirst()
                .orElseThrow(FacultyNotFoundException::new);
    }

    @Override
    public Faculty updateFaculty(String name, Faculty faculty) {

        boolean isDeleted = list.removeIf(u -> u.getName().equals(name));
        if (isDeleted) {
            list.add(faculty);
        } else {
            throw new FacultyNotFoundException();
        }
        log.info("Faculty with name {} was updated", name);
        return faculty;
    }

    @Override
    public void deleteFaculty(String name) {
        list.removeIf(faculty -> faculty.getName().equals(name));
        log.info("User with email {} was deleted", name);
    }
}
