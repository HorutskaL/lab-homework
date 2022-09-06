package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.dto.group.OnCreate;
import com.epam.selectioncommittee.dto.group.OnUpdate;
import com.epam.selectioncommittee.dto.validation.ValidPassword;
import com.epam.selectioncommittee.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Email
    @NotBlank(message = "validation.user.email", groups = OnCreate.class)
    private String email;

    @NotBlank(message = "validation.user.password.null", groups = OnCreate.class)
    @Null(message = "validation.update.password.null", groups = OnUpdate.class)
    @ValidPassword(message = "validation.user.password", groups = OnCreate.class)
    private String password;

    @NotBlank(message = "validation.user.repeatPassword", groups = OnCreate.class)
    @Null(message = "validation.update.repeatPassword.null", groups = OnUpdate.class)
    private String repeatPassword;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotBlank(message = "{validation.user.role.notNull}", groups = OnCreate.class)
    @Null(message = "{validation.user.role.null}", groups = OnUpdate.class)
    private Role role;

    private UserInfoDto userInfo;

    private SubjectDto subjects;

}
