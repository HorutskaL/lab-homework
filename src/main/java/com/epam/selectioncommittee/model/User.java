package com.epam.selectioncommittee.model;

import com.epam.selectioncommittee.model.enums.Role;
import lombok.*;

@Data
@ToString(exclude = {"userInfo"})
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private UserInfo userInfo;
    private Subject subjects;
}
