package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.SubjectDto;
import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.dto.UserInfoDto;
import com.epam.selectioncommittee.model.Subject;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.model.UserInfo;
import com.epam.selectioncommittee.service.UserMappingService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserMappingServiceImpl implements UserMappingService {
    @Override
    public User populateUserWithPresentUserDtoFields(User user, UserDto userDto) {
        Long id = userDto.getId();
        if(Objects.nonNull(id)) {
            user.setId(id);
        }
        String email = userDto.getEmail();
        if (Objects.nonNull(email)) {
            user.setEmail(email);
        }
        String password = userDto.getPassword();
        if (Objects.nonNull(password)) {
            user.setPassword(password);
        }
        user.setRole(userDto.getRole());
        user.setIsBlocked(userDto.getIsBlocked());

        UserInfoDto userInfoDto = userDto.getUserInfo();
        if (Objects.nonNull(userInfoDto)) {
            mapUserInfo(user, userInfoDto);
        }
        SubjectDto subjectDto = userDto.getSubject();
        if (Objects.nonNull(subjectDto)) {
            mapSubject(user, subjectDto);
        }
        return user;
    }

    private void mapUserInfo(User user, UserInfoDto userInfoDto) {
        UserInfo userInfo = user.getUserInfo();
        if (!Objects.nonNull(userInfo)) {
            userInfo = new UserInfo();
            user.setUserInfo(userInfo);
        }
        userInfo.setFirstName(userInfo.getFirstName());
        userInfo.setLastName(userInfoDto.getLastName());
        userInfo.setPatronymic(userInfoDto.getPatronymic());
        userInfo.setCity(userInfoDto.getCity());
        userInfo.setRegion(userInfoDto.getRegion());
        userInfo.setSchool(userInfoDto.getSchool());
    }

    private void mapSubject(User user, SubjectDto subjectDto) {
        Subject subject = user.getSubject();
        if (!Objects.nonNull(subject)) {
            subject = new Subject();
            user.setSubject(subject);
        }
        subject.setAlgebra(subjectDto.getAlgebra());
        subject.setGeometry(subjectDto.getGeometry());
        subject.setGeography(subjectDto.getGeography());
        subject.setForeignLanguage(subjectDto.getForeignLanguage());
        subject.setInformatics(subjectDto.getInformatics());
        subject.setUkHistory(subjectDto.getUkHistory());
        subject.setScience(subjectDto.getScience());
        subject.setUkLanguage(subjectDto.getUkLanguage());
        subject.setUkLiterature(subjectDto.getUkLiterature());
        subject.setPhTraining(subjectDto.getPhTraining());
        subject.setEieMath(subjectDto.getEieMath());
        subject.setEieHistory(subjectDto.getEieHistory());
        subject.setEieUkLanguage(subjectDto.getEieUkLanguage());
    }
}
