package com.epam.selectioncommittee.service;

public interface UserFacultyService {
    void registerUserOnFaculty(Long userId, Long facultyId, int eieMath, int eieUkLanguage, int eieHistory);

    void deleteUser(Long userId, Long facultyId);
}
