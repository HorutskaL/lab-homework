package com.epam.selectioncommittee.repository;

import com.epam.selectioncommittee.model.User;

import java.util.List;


public interface UserRepository {
    User getUser(String email);

    List<User> users();

    User createUser(User user);

    User updateUser(String email, User user);

    void deleteUser(String email);
}
