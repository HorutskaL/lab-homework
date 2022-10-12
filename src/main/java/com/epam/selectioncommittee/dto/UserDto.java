package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.dto.group.OnCreate;
import com.epam.selectioncommittee.dto.validation.ValidPassword;
import com.epam.selectioncommittee.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    @Email
    @NotBlank(message = "validation.user.email", groups = OnCreate.class)
    private String email;

    @NotBlank(message = "validation.user.password.null", groups = OnCreate.class)
    @ValidPassword(message = "validation.user.password", groups = OnCreate.class)
    private String password;

    @NotBlank(message = "validation.user.repeatPassword", groups = OnCreate.class)
    private String repeatPassword;

    private Role role;
    private int isBlocked;

    private UserInfoDto userInfo;

    private SubjectDto subject;

}
