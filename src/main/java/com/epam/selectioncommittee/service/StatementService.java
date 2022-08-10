package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.StatementDto;
import com.epam.selectioncommittee.dto.UserDto;

import java.util.List;

public interface StatementService {
    void addApplicantToStatement(Long facultyId);

    void removeApplicantFromStatement(String userEmail);

    void finaliseStatement(Long facultyId);

    List<UserDto> getBudgetPlApplicantList(String facultyName);

    List<UserDto> getNotBudgetPlApplicantList(String facultyName);

    StatementDto createStatement(StatementDto statementDto);
}
