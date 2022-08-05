package com.epam.selectioncommittee.service;

public interface UserFacultyService {
    void registerUserOnFaculty(String userEmail, int isEieMath, int isEieUkLanguage, int isEieHistory);

    void deleteUser(String userEmail, int facultyId);
}
