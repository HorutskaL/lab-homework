package com.epam.selectioncommittee.api;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.dto.group.OnCreate;
import com.epam.selectioncommittee.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/v1/users")
public interface UserApi {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ApiOperation("Get all users")
    List<UserDto> getAllUsers();

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{userId}")
    @ApiOperation("Get user by id")
    UserDto getUser(@PathVariable Long userId);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create user")
    UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto);

    @PatchMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update user")
    UserDto updateUser(@PathVariable("userId") Long userId, @RequestBody @Validated(OnUpdate.class) UserDto userDto);

    @ApiOperation("Delete user")
    @DeleteMapping(value = "/{userId}")
    void deleteUser(@PathVariable Long userId);
}
