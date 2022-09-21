package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.UserDto;

import java.util.List;

public interface StatementService {
    void addApplicantToStatement(Long userId);

    void removeApplicantFromStatement(Long userId);

    List<UserDto> getApplicantList(Long facultyId);

    List<UserDto> getBudgetPlApplicantList(Long facultyId);

    List<UserDto> getNonBudgetPlApplicantList(Long facultyId);
}
