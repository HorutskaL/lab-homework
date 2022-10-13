package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.exception.UserAlreadyExistsException;
import com.epam.selectioncommittee.service.exception.UserNotFoundException;
import com.epam.selectioncommittee.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMappingServiceImpl userMappingService = new UserMappingServiceImpl();

    @Test
    void getUserTest() {
        User user = TestDataUtil.createUser();
        when(userRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser(TestDataUtil.TEST_ID);

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("password", equalTo(user.getPassword()))
        ));
    }

    @Test
    void getUserUserNotFoundTest() {
        when(userRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUser(TestDataUtil.TEST_ID));
    }

    @Test
    void listUsersTest() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(User.builder().build()));
        List<UserDto> userDtoList = userService.listUsers();
        assertThat(userDtoList, hasSize(1));
    }

    @Test
    void createUserTest() {
        User testUser = TestDataUtil.createUser();
        UserDto testUserDto = TestDataUtil.createUserDto();

        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.createUser(testUserDto);
        assertThat(userDto, allOf(
                hasProperty("id", equalTo(testUser.getId())),
                hasProperty("email", equalTo(testUser.getEmail())),
                hasProperty("password", equalTo(testUser.getPassword()))
        ));
    }

    @Test
    void createUserUserAlreadyExistsTest() {
        UserDto testUserDto = TestDataUtil.createUserDto();

        when(userRepository.existsById(TestDataUtil.TEST_ID)).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(testUserDto));
    }

    @Test
    void updateUserTest() {
        User user = TestDataUtil.createUser();
        UserDto userDto = TestDataUtil.createUserDto();
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(any())).thenReturn(user);
        when(userMappingService.populateUserWithPresentUserDtoFields(user,userDto)).thenReturn(user);
        Long newId = 2L;
        userService.updateUser(newId, userDto);
        verify(userRepository, times(1)).findById(any());
        verify(userMappingService, times(1)).populateUserWithPresentUserDtoFields(any(),any());
        verify(userRepository,times(1)).save(any());
    }
    @Test
    void updateUserUserNotFoundTest() {
        UserDto testUserDto = TestDataUtil.createUserDto();
        when(userRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(testUserDto.getId(), testUserDto));
    }

    @Test
    void deleteUserTest() {
        User testUser = TestDataUtil.createUser();

        when(userRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.of(testUser));
        doNothing().when(userRepository).delete(any());
        userService.deleteUser(testUser.getId());

        verify(userRepository, times(1)).delete(testUser);
    }

  @Test
    void deleteUserNotFoundTest(){
        when(userRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, ()->userService.deleteUser(TestDataUtil.TEST_ID));
  }

}
