package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.*;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.repository.StatementRepository;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.StatementService;
import com.epam.selectioncommittee.service.exception.FacultyNotFoundException;
import com.epam.selectioncommittee.service.exception.UserNotFoundException;
import com.epam.selectioncommittee.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;
    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public void addApplicantToStatement(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        Set<User> users = faculty.getUsers();
        users.stream()
                .filter(user -> !statementRepository.existsStatementByUser(user))
                .forEach(user -> statementRepository.save(Statement.builder().user(user).faculty(faculty).build()));
    }

    @Override
    public void removeApplicantFromStatement(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Statement statement = statementRepository.findByUser(user);
        statementRepository.delete(statement);
    }


    @Override
    public List<UserDto> getApplicantList(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        List<Statement> applicantList = statementRepository.findByFaculty(faculty);
        return applicantList.stream()
                .map(statement -> UserMapper.INSTANCE.mapUserToUserDto(statement.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getBudgetPlApplicantList(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        List<Statement> applicantList = statementRepository.findBudgetStPlByFaculty(faculty);
        return applicantList.stream()
                .map(statement -> UserMapper.INSTANCE.mapUserToUserDto(statement.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getNonBudgetPlApplicantList(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        List<Statement> applicantList = statementRepository.findNonBudStPlByFaculty(faculty);
        return applicantList.stream()
                .map(statement -> UserMapper.INSTANCE.mapUserToUserDto(statement.getUser()))
                .collect(Collectors.toList());
    }
}
