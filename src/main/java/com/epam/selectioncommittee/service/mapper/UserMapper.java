package com.epam.selectioncommittee.service.mapper;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);
}
