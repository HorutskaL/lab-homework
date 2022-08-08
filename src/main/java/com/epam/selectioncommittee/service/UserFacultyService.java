package com.epam.selectioncommittee.service;

public interface UserFacultyService {
    void registerUserOnFaculty(String userEmail, int facultyId, int eieMath, int eieUkLanguage, int eieHistory);

    void deleteUser(String userEmail, int facultyId);
}
