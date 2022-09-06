package com.epam.selectioncommittee.repository.impl;

import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.repository.UserRepository;
import com.epam.selectioncommittee.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final List<User> list = new ArrayList<>();

    @Override
    public User getUser(Long id) {
        log.info("Got user by id {}", id);
        return list.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public User findUserById(Long id) {
        log.info("Found user by id {}", id);
        return list.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public List<User> users() {
        return new ArrayList<>(list);
    }

    @Override
    public User createUser(User user) {
        list.add(user);
        log.info("Created user {}", user);
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        boolean isDeleted = list.removeIf(u -> u.getId().equals(id));
        if (isDeleted) {
            list.add(user);
        } else {
            throw new UserNotFoundException();
        }
        log.info("User with id {} was updated", id);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        list.removeIf(user -> user.getId().equals(id));
        log.info("User with id {} was deleted", id);
    }


    @Override
    public void save(User user) {
    }
}
