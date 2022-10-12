package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.api.StatementApi;
import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.service.StatementMappingService;
import com.epam.selectioncommittee.service.StatementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class StatementController implements StatementApi {
    private final StatementService statementService;
    private final StatementMappingService statementMappingService;

    @Override
    public void add(Long facultyId) {
        statementService.addApplicantToStatement(facultyId);
    }

    @Override
    public void finalise(Long facultyId) {
        statementMappingService.finaliseStatement(facultyId);
    }

    @Override
    public void deleteUserFromStatement(Long userId) {
        statementService.removeApplicantFromStatement(userId);
    }

    @Override
    public List<UserDto> getApplicantList(Long facultyId) {
        return statementService.getApplicantList(facultyId);
    }

    @Override
    public List<UserDto> getBudgetPlApplicantList(Long facultyId) {
        return statementService.getBudgetPlApplicantList(facultyId);
    }

    @Override
    public List<UserDto> getNonBudgetPlApplicantList(Long facultyId) {
        return statementService.getNonBudgetPlApplicantList(facultyId);
    }

}
