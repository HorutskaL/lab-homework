package com.epam.selectioncommittee.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"user"})
@Builder
public class UserInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String city;
    private String region;
    private String school;
    private User user;
}
