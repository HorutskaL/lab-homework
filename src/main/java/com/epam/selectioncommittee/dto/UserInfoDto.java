package com.epam.selectioncommittee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String city;
    private String region;
    private String school;
    private UserDto user;
}
