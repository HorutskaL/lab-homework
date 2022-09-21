package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.UserMappingService;
import com.epam.selectioncommittee.service.UserService;
import com.epam.selectioncommittee.service.exception.UserAlreadyExistsException;
import com.epam.selectioncommittee.service.exception.UserNotFoundException;
import com.epam.selectioncommittee.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMappingService userMappingService;

    @Override
    public UserDto getUser(Long id) {
        log.info("Finding user by id {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        log.info("User with id {} is found", id);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("Creating user with id {}", userDto.getId());
        if (userRepository.existsById(userDto.getId())) {
            throw new UserAlreadyExistsException();
        }
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.save(user);
        log.info("User with id {} created", user.getId());
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        log.info("Updating user with id {}", userDto.getId());
        User persistedUser = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        persistedUser = userMappingService.populateUserWithPresentUserDtoFields(persistedUser, userDto);
        User storedUser = userRepository.save(persistedUser);
        log.info("User with id {} successfully updated", storedUser.getId());
        return UserMapper.INSTANCE.mapUserToUserDto(persistedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("deleteUser with id {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        log.info("User with id {} was successfully deleted", id);
    }

}
