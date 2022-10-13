package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.model.Statement;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.repository.StatementRepository;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
public class StatementServiceImplTest {
    @InjectMocks
    private StatementServiceImpl statementService;
    @Mock
    private StatementRepository statementRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void addApplicantToStatementTest() {
        Set<User> users = new HashSet<>();
        users.add(new User());
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Faculty.builder().users(users).build()));
        when(statementRepository.existsStatementByUser(any())).thenReturn(false);
        when(statementRepository.save(any())).thenReturn(Statement.builder().build());
        statementService.addApplicantToStatement(1L);
        verify(statementRepository, times(1)).save(any());
    }

    @Test
    void removeApplicantFromStatementTest() {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(User.builder().build()));
        when(statementRepository.findByUser(any())).thenReturn(Statement.builder().build());
        doNothing().when(statementRepository).delete(any());
        statementService.removeApplicantFromStatement(1L);
        verify(statementRepository, times(1)).delete(any());
    }

    @Test
    void getApplicantListTest() {
        List<Statement> statements = new ArrayList<>();
        statements.add(new Statement());
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Faculty.builder().build()));
        when(statementRepository.findByFaculty(any())).thenReturn(statements);
        List<UserDto> userDtoList = statementService.getApplicantList(1L);
        assertEquals(UserMapper.INSTANCE.mapUserToUserDto(statements.get(0).getUser()), userDtoList.get(0));

    }

    @Test
    void getBudgetPlApplicantListTest() {
        List<Statement> statements = new ArrayList<>();
        statements.add(new Statement());
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Faculty.builder().build()));
        when(statementRepository.findBudgetStPlByFaculty(any())).thenReturn(statements);
        List<UserDto> userDtoList = statementService.getBudgetPlApplicantList(1L);
        assertEquals(UserMapper.INSTANCE.mapUserToUserDto(statements.get(0).getUser()), userDtoList.get(0));
    }

    @Test
    void getNonBudgetPlApplicantListTest() {
        List<Statement> statementList = new ArrayList<>();
        statementList.add(new Statement());
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Faculty.builder().build()));
        when(statementRepository.findNonBudStPlByFaculty(any())).thenReturn(statementList);
        List<UserDto> userDtoList = statementService.getNonBudgetPlApplicantList(1L);
        assertEquals(UserMapper.INSTANCE.mapUserToUserDto(statementList.get(0).getUser()), userDtoList.get(0));
    }
}
