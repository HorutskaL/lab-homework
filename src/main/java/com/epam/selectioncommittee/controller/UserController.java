package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.dto.group.OnCreate;
import com.epam.selectioncommittee.dto.group.OnUpdate;
import com.epam.selectioncommittee.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user")
    @ApiOperation("Get all users")
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers");
        return userService.listUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "user/{email}")
    @ApiOperation("Get user by email")
    public UserDto getUser(@PathVariable String email) {
        log.info("getUser by email {}", email);
        return userService.getUser(email);
    }

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create user")
    public UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PatchMapping(value = "/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Update user")
    public UserDto updateUser(@PathVariable("email") String email, @RequestBody @Validated(OnUpdate.class) UserDto userDto) {
        return userService.updateUser(email, userDto);
    }

    @ApiOperation("Delete user")
    @DeleteMapping(value = "/user/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }

}
