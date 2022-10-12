package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.service.mapper.UserMapper;
import com.epam.selectioncommittee.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserMappingServiceImplTest {
    @InjectMocks
    UserMappingServiceImpl userMappingService;

    @Test
    void populateUserWithPresentUserDtoFieldsTest() {
        User expectedUser = TestDataUtil.createUser();
        UserDto userDto = UserMapper.INSTANCE.mapUserToUserDto(expectedUser);
        User actualUser = TestDataUtil.createUser();

        userMappingService.populateUserWithPresentUserDtoFields(actualUser, userDto);
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getUserInfo().getFirstName(), actualUser.getUserInfo().getFirstName());
        assertEquals(expectedUser.getSubject().getEieHistory(), actualUser.getSubject().getEieHistory());
        assertEquals(expectedUser.getUserInfo().getLastName(), actualUser.getUserInfo().getLastName());
    }
}
