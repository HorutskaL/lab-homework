package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.dto.group.OnCreate;
import com.epam.selectioncommittee.dto.group.OnUpdate;
import com.epam.selectioncommittee.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Long id;
    @Email
    @NotBlank(message = "'email' shouldn't be empty", groups = OnCreate.class)
    private String email;

    @NotBlank(message = "'password' shouldn't be empty", groups = OnCreate.class)
    @Null(message = "'password' should be absent in request", groups = OnUpdate.class)
    private String password;

    @NotBlank(message = "'repeatPassword' shouldn't be empty", groups = OnCreate.class)
    @Null(message = "'repeatPassword' should be absent in request", groups = OnUpdate.class)
    private String repeatPassword;

    private Role role;
    private UserInfoDto userInfo;
    private SubjectDto subjects;

}
