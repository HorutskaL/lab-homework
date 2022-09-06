package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.UserFacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacultyServiceImpl implements UserFacultyService {
    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public void registerUserOnFaculty(Long userId, Long facultyId, int eieMath, int eieUkLanguage, int eieHistory) {
        User user = userRepository.findUserById(userId);
        user.getSubjects().setEieHistory(eieHistory);
        user.getSubjects().setEieUkLanguage(eieUkLanguage);
        user.getSubjects().setEieMath(eieMath);
        Faculty faculty = facultyRepository.getFacultyById(facultyId);
        Set<Faculty> faculties = user.getFaculties();
        faculties.add(faculty);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId, Long facultyId) {
        User user = userRepository.findUserById(userId);
        Faculty faculty = facultyRepository.getFacultyById(facultyId);
        Set<Faculty> faculties = user.getFaculties();
        faculties.remove(faculty);
        user.setFaculties(faculties);
        userRepository.save(user);
    }
}
