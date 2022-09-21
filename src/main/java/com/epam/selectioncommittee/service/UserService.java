package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUser(Long id);

    List<UserDto> listUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
