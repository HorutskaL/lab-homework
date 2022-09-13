package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.service.StatementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/statements")
public class StatementController {
    public final StatementService statementService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{userId}")
    public void add(@PathVariable Long userId) {
        statementService.addApplicantToStatement(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    public void finalise(long facultyId) {
        statementService.finaliseStatement(facultyId);
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        statementService.removeApplicantFromStatement(userId);
    }
}
