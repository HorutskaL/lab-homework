package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.api.UserFacultyApi;
import com.epam.selectioncommittee.service.UserFacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserFacultyController implements UserFacultyApi {
    private final UserFacultyService userFacultyService;

    @Override
    public ResponseEntity<Void> registerUserOnFaculty(String userEmail, int facultyId, int eieMath,
                                                      int eieUkLanguage, int eieHistory) {
        log.info("Applicant userEmail {} registered on facultyId {}", userEmail, facultyId);
        userFacultyService.registerUserOnFaculty(userEmail, facultyId, eieUkLanguage, eieMath, eieHistory);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userEmail, int facultyId) {
        userFacultyService.deleteUser(userEmail, facultyId);
        return ResponseEntity.noContent().build();
    }
}
