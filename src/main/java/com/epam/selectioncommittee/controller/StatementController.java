package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.api.StatementApi;
import com.epam.selectioncommittee.service.StatementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class StatementController implements StatementApi {
    public final StatementService statementService;

    @Override
    public void add(Long facultyId) {
        statementService.addApplicantToStatement(facultyId);
    }

    @Override
    public void finalise(Long facultyId) {
        statementService.finaliseStatement(facultyId);
    }

    @Override
    public void deleteUserFromStatement(@PathVariable String userEmail) {
        statementService.removeApplicantFromStatement(userEmail);
    }
}
