package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.service.UserFacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserFacultyServiceImpl implements UserFacultyService {
    @Override
    public void registerUserOnFaculty(String userEmail, int facultyId, int eieMath, int eieUkLanguage, int eieHistory) {

    }

    @Override
    public void deleteUser(String userEmail, int facultyId) {

    }
}