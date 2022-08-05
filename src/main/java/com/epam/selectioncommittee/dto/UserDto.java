package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private Role role;
    private UserInfoDto userInfo;
    private SubjectDto subjects;
}
