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
    public void registerUserOnFaculty(Long userId, Long facultyId, int eieMath,
                                      int eieUkLanguage, int eieHistory) {
        log.info("Applicant userId {} registered on facultyId {}", userId, facultyId);
        userFacultyService.registerUserOnFaculty(userId, facultyId, eieUkLanguage, eieMath, eieHistory);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId, Long facultyId) {
        userFacultyService.deleteUser(userId, facultyId);
        return ResponseEntity.noContent().build();
    }
}
