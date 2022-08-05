package com.epam.selectioncommittee.model;

import lombok.*;

@Data
@ToString(exclude = {"userInfo"})
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private Role role = Role.ADMIN;
    private UserInfo userInfo;
    private Subject subjects;
}
