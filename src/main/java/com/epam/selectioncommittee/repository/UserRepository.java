package com.epam.selectioncommittee.repository;

import com.epam.selectioncommittee.model.User;

import java.util.List;


public interface UserRepository {
    User getUser(Long id);

    List<User> users();

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User findUserById(Long userId);

    void save(User user);
}
