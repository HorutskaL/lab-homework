package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.UserService;
import com.epam.selectioncommittee.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(Long id) {
        log.info("getUser by id {}", id);
        User user = userRepository.getUser(id);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.users()
                .stream()
                .map(UserMapper.INSTANCE::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        log.info("updateUser with id {}", userDto.getId());
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.updateUser(id, user);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("deleteUser with id {}", id);
        userRepository.deleteUser(id);
    }

}
