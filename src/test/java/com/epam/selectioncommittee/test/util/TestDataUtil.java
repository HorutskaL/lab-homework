package com.epam.selectioncommittee.test.util;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.dto.SubjectDto;
import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.dto.UserInfoDto;
import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.model.Subject;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.model.UserInfo;
import com.epam.selectioncommittee.model.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {

    public final static String TEST_EMAIL = "email@email.com";
    public final static String PASSWORD = "PasswordD1";
    public final static Long TEST_ID = 1L;
    public final static Role ROLE = Role.ADMIN;

    public static User createUser() {
        return User.builder()
                .id(TEST_ID)
                .email(TEST_EMAIL)
                .password(PASSWORD)
                .role(ROLE)
                .userInfo(TestDataUtil.createUserInfo())
                .subject(TestDataUtil.createSubject())
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .id(TEST_ID)
                .email(TEST_EMAIL)
                .password(PASSWORD)
                .repeatPassword(PASSWORD)
                .role(ROLE)
                .userInfo(TestDataUtil.createUserInfoDto())
                .subject(TestDataUtil.createSubjectDto())
                .build();
    }

    public static UserInfo createUserInfo() {
        return UserInfo.builder()
                .firstName("FirstName")
                .lastName("LastName")
                .patronymic("Patronymic")
                .city("City")
                .region("Region")
                .school("School")
                .build();
    }

    public static UserInfoDto createUserInfoDto() {
        return UserInfoDto.builder()
                .firstName("FirstName")
                .lastName("LastName")
                .patronymic("Patronymic")
                .city("City")
                .region("Region")
                .school("School")
                .build();
    }

    public static Subject createSubject() {
        return Subject.builder()
                .algebra(7)
                .science(9)
                .geometry(5)
                .geography(8)
                .ukLiterature(10)
                .ukHistory(6)
                .foreignLanguage(7)
                .ukLanguage(10)
                .informatics(9)
                .phTraining(7)
                .eieMath(8)
                .eieUkLanguage(8)
                .eieHistory(8)
                .build();
    }

    public static SubjectDto createSubjectDto() {
        return SubjectDto.builder()
                .algebra(7)
                .science(9)
                .geometry(5)
                .geography(8)
                .ukLiterature(10)
                .ukHistory(6)
                .foreignLanguage(7)
                .ukLanguage(10)
                .informatics(9)
                .phTraining(7)
                .eieMath(8)
                .eieUkLanguage(8)
                .eieHistory(8)
                .build();
    }

    public static Faculty createFaculty() {
        return Faculty.builder()
                .id(TEST_ID)
                .name("Test name")
                .amountBudgetPlaces(4)
                .amountTotalPlaces(2)
                .isEieMath(1)
                .isEieHistory(1)
                .isEieUkLanguage(1)
                .build();
    }

    public static FacultyDto createFacultyDto() {
        return FacultyDto.builder()
                .id(TEST_ID)
                .name("Test name")
                .amountBudgetPlaces(4)
                .amountTotalPlaces(2)
                .isEieMath(1)
                .isEieHistory(1)
                .isEieUkLanguage(1)
                .build();
    }
}
