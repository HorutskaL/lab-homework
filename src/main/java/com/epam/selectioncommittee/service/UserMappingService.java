package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.User;

public interface UserMappingService {
    User populateUserWithPresentUserDtoFields(User user, UserDto userDto);
}
