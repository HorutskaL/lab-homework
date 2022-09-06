package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.StatementDto;
import com.epam.selectioncommittee.dto.UserDto;

import java.util.List;

public interface StatementService {
    void addApplicantToStatement(Long userId);

    void removeApplicantFromStatement(Long userId);

    void finaliseStatement(Long facultyId);

    List<UserDto> getApplicantList(Long facultyId);

    List<UserDto> getBudgetPlApplicantList(Long facultyId);

    List<UserDto> getNonBudgetPlApplicantList(Long facultyId);
}
