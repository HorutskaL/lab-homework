package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.WebConfig;
import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.service.UserService;
import com.epam.selectioncommittee.service.mapper.UserMapper;
import com.epam.selectioncommittee.test.util.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc
@Import(WebConfig.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    UserDto userDto = TestDataUtil.createUserDto();
    User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);

    @Test
    void getAllUsersTest() throws Exception {
        when(userService.listUsers()).thenReturn(Collections.singletonList(userDto));
        mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(user.getId()));
    }

    @Test
    void getAllUsersExpressExceptionOrErrorTest() throws Exception {
        String message = "Error message";
        when(userService.listUsers()).thenThrow(new NullPointerException(message));
        mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getUserTest() throws Exception {
        when(userService.getUser(1L)).thenReturn(userDto);
        mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id")
                        .value(user.getId()));
    }

    @Test
    void createUserTest() throws Exception {
        when(userService.createUser(any())).thenReturn(userDto);
        mockMvc
                .perform(post("/api/v1/users")
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(user.getId()));
        verify(userService, times(1)).createUser(userDto);
    }

    @Test
    void updateUserTest() throws Exception {
        when(userService.updateUser(1L, userDto)).thenReturn(userDto);
        mockMvc
                .perform(patch("/api/v1/users/" + TestDataUtil.TEST_ID)
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(user.getId()));
        verify(userService, times(1)).updateUser(TestDataUtil.TEST_ID, userDto);
    }

    @Test
    void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteUser(any());
        mockMvc
                .perform(delete("/api/v1/users/" + TestDataUtil.TEST_ID))
                .andDo(print())
                .andExpect(status().isOk());
        verify(userService, times(1)).deleteUser(any());
    }


}
