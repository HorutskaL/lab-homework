package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.service.StatementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class StatementController {
    public final StatementService statementService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/statement/{facultyId}")
    public void add(@PathVariable String facultyId) {
        statementService.addApplicantToStatement(facultyId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/statement")
    public void finalise() {
        statementService.finaliseStatement();
    }

    @DeleteMapping(value = "/user/{userEmail}")
    public void deleteUser(@PathVariable String userEmail) {
        statementService.removeApplicantFromStatement(userEmail);
    }
}
