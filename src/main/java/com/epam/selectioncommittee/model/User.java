package com.epam.selectioncommittee.model;

import lombok.*;

import java.util.Set;

@Data
@ToString(exclude = {"userInfo","subjects"})
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private Role role = Role.ADMIN;
    private UserInfo userInfo;
    private Subject subjects;
    Set<Faculty> faculties;
}
