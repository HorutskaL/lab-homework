package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.model.Subject;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.model.UserInfo;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.exception.UserNotFoundException;
import com.epam.selectioncommittee.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserFacultyServiceImplTest {
    @InjectMocks
    private UserFacultyServiceImpl userFacultyService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FacultyRepository facultyRepository;

    @Test
    void registerUserOnFacultyTest(){
        User user = TestDataUtil.createUser();
        UserInfo userInfo = TestDataUtil.createUserInfo();
        Subject subject = TestDataUtil.createSubject();
        user.setUserInfo(userInfo);
        user.setSubject(subject);
        user.setFaculties(new HashSet<>());
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(facultyRepository.findById(any())).thenReturn(Optional.ofNullable(Faculty.builder().build()));
        when(userRepository.save(any())).thenReturn(user);
        userFacultyService.registerUserOnFaculty(1L,1L,9,8,10);
        verify(userRepository,times(1)).save(any());
    }

    @Test
    void deleteUserTest(){
        User user = User.builder().faculties(new HashSet<>()).build();
        Faculty faculty = Faculty.builder().build();
        Set<Faculty> faculties = user.getFaculties();
        faculties.add(faculty);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(facultyRepository.findById(any())).thenReturn(Optional.ofNullable(faculty));
        userFacultyService.deleteUser(1L,1L);
        verify(userRepository,times(1)).save(any());
    }
    @Test
    void deleteUserUserNotFoundTest(){
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,()->userFacultyService.deleteUser(1L,1L));
    }

}
