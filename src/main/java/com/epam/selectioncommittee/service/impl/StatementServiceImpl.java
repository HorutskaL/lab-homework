package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.StatementDto;
import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.repository.StatementRepository;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.StatementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StatementServiceImpl implements StatementService {

    @Override
    public void addApplicantToStatement(Long facultyId) {
    }

    @Override
    public void removeApplicantFromStatement(String userEmail) {

    }

    @Override
    public void finaliseStatement(Long statementId) {

    }

    @Override
    public List<UserDto> getBudgetPlApplicantList(String facultyName) {
        return null;
    }

    @Override
    public List<UserDto> getNotBudgetPlApplicantList(String facultyName) {
        return null;
    }

    @Override
    public StatementDto createStatement(StatementDto statementDto) {
        return null;
    }
}
