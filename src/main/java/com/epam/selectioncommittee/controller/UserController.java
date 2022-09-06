package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.api.UserApi;
import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j

public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers");
        return userService.listUsers();
    }

    @Override
    public UserDto getUser(Long userId) {
        log.info("getUser by id {}", userId);
        return userService.getUser(userId);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return userService.createUser(userDto);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }
    @Override
    public void deleteUser(Long userId) {userService.deleteUser(userId);
    }

}
