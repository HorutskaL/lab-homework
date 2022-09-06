package com.epam.selectioncommittee.dto;

import com.epam.selectioncommittee.dto.group.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserInfoDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "validation.user.firstName", groups = OnCreate.class)
    private String firstName;

    @NotBlank(message = "validation.user.lastName", groups = OnCreate.class)
    private String lastName;

    @NotBlank(message = "validation.user.patronymic", groups = OnCreate.class)
    private String patronymic;

    @NotBlank(message = "validation.user.city", groups = OnCreate.class)
    private String city;

    @NotBlank(message = "validation.user.region", groups = OnCreate.class)
    private String region;

    @NotBlank(message = "validation.user.school", groups = OnCreate.class)
    private String school;

    private UserDto user;
}
