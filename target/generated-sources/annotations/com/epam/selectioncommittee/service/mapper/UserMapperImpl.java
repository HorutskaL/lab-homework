package com.epam.selectioncommittee.service.mapper;

import com.epam.selectioncommittee.dto.SubjectDto;
import com.epam.selectioncommittee.dto.SubjectDto.SubjectDtoBuilder;
import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.dto.UserDto.UserDtoBuilder;
import com.epam.selectioncommittee.dto.UserInfoDto;
import com.epam.selectioncommittee.dto.UserInfoDto.UserInfoDtoBuilder;
import com.epam.selectioncommittee.model.Subject;
import com.epam.selectioncommittee.model.Subject.SubjectBuilder;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.model.User.UserBuilder;
import com.epam.selectioncommittee.model.UserInfo;
import com.epam.selectioncommittee.model.UserInfo.UserInfoBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-21T21:59:02+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto mapUserToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.role( user.getRole() );
        userDto.isBlocked( user.getIsBlocked() );
        userDto.userInfo( userInfoToUserInfoDto( user.getUserInfo() ) );
        userDto.subject( subjectToSubjectDto( user.getSubject() ) );

        return userDto.build();
    }

    @Override
    public User mapUserDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.email( userDto.getEmail() );
        user.password( userDto.getPassword() );
        user.isBlocked( userDto.getIsBlocked() );
        user.role( userDto.getRole() );
        user.userInfo( userInfoDtoToUserInfo( userDto.getUserInfo() ) );
        user.subject( subjectDtoToSubject( userDto.getSubject() ) );

        return user.build();
    }

    protected UserInfoDto userInfoToUserInfoDto(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        UserInfoDtoBuilder userInfoDto = UserInfoDto.builder();

        userInfoDto.id( userInfo.getId() );
        userInfoDto.firstName( userInfo.getFirstName() );
        userInfoDto.lastName( userInfo.getLastName() );
        userInfoDto.patronymic( userInfo.getPatronymic() );
        userInfoDto.city( userInfo.getCity() );
        userInfoDto.region( userInfo.getRegion() );
        userInfoDto.school( userInfo.getSchool() );
        userInfoDto.user( mapUserToUserDto( userInfo.getUser() ) );

        return userInfoDto.build();
    }

    protected SubjectDto subjectToSubjectDto(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        SubjectDtoBuilder subjectDto = SubjectDto.builder();

        subjectDto.id( subject.getId() );
        subjectDto.algebra( subject.getAlgebra() );
        subjectDto.science( subject.getScience() );
        subjectDto.geometry( subject.getGeometry() );
        subjectDto.geography( subject.getGeography() );
        subjectDto.ukLiterature( subject.getUkLiterature() );
        subjectDto.ukHistory( subject.getUkHistory() );
        subjectDto.foreignLanguage( subject.getForeignLanguage() );
        subjectDto.ukLanguage( subject.getUkLanguage() );
        subjectDto.informatics( subject.getInformatics() );
        subjectDto.phTraining( subject.getPhTraining() );
        subjectDto.eieMath( subject.getEieMath() );
        subjectDto.eieUkLanguage( subject.getEieUkLanguage() );
        subjectDto.eieHistory( subject.getEieHistory() );
        subjectDto.user( mapUserToUserDto( subject.getUser() ) );

        return subjectDto.build();
    }

    protected UserInfo userInfoDtoToUserInfo(UserInfoDto userInfoDto) {
        if ( userInfoDto == null ) {
            return null;
        }

        UserInfoBuilder userInfo = UserInfo.builder();

        userInfo.id( userInfoDto.getId() );
        userInfo.firstName( userInfoDto.getFirstName() );
        userInfo.lastName( userInfoDto.getLastName() );
        userInfo.patronymic( userInfoDto.getPatronymic() );
        userInfo.city( userInfoDto.getCity() );
        userInfo.region( userInfoDto.getRegion() );
        userInfo.school( userInfoDto.getSchool() );
        userInfo.user( mapUserDtoToUser( userInfoDto.getUser() ) );

        return userInfo.build();
    }

    protected Subject subjectDtoToSubject(SubjectDto subjectDto) {
        if ( subjectDto == null ) {
            return null;
        }

        SubjectBuilder subject = Subject.builder();

        subject.id( subjectDto.getId() );
        subject.algebra( subjectDto.getAlgebra() );
        subject.science( subjectDto.getScience() );
        subject.geometry( subjectDto.getGeometry() );
        subject.geography( subjectDto.getGeography() );
        subject.ukLiterature( subjectDto.getUkLiterature() );
        subject.ukHistory( subjectDto.getUkHistory() );
        subject.foreignLanguage( subjectDto.getForeignLanguage() );
        subject.ukLanguage( subjectDto.getUkLanguage() );
        subject.informatics( subjectDto.getInformatics() );
        subject.phTraining( subjectDto.getPhTraining() );
        subject.eieMath( subjectDto.getEieMath() );
        subject.eieUkLanguage( subjectDto.getEieUkLanguage() );
        subject.eieHistory( subjectDto.getEieHistory() );
        subject.user( mapUserDtoToUser( subjectDto.getUser() ) );

        return subject.build();
    }
}
