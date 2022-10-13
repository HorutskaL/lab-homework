package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.WebConfig;
import com.epam.selectioncommittee.service.UserFacultyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserFacultyController.class)
@AutoConfigureMockMvc
@Import(WebConfig.class)
public class UseFacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserFacultyService userFacultyService;

    @Test
    void registerUserOnFacultyTest() throws Exception{
        doNothing().when(userFacultyService).registerUserOnFaculty(anyLong(),anyLong(),anyInt(),anyInt(),anyInt());
        mockMvc
                .perform(post("/api/v1/userFaculty/userId/1/facultyId/1/eieUkLanguage/8/eieMath/8/eieHistory/8"))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(userFacultyService, times(1)).registerUserOnFaculty(1L, 1L,8,8,8);
    }

    @Test
    void deleteUserTest() throws Exception{
        doNothing().when(userFacultyService).deleteUser(anyLong(),anyLong());
        mockMvc.perform(delete("/api/v1/userFaculty/userId/1/facultyId/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(userFacultyService, times(1)).deleteUser(1L,1L);
    }
}
