package com.epam.selectioncommittee.controller;


import com.epam.selectioncommittee.WebConfig;
import com.epam.selectioncommittee.dto.UserDto;
import com.epam.selectioncommittee.model.User;
import com.epam.selectioncommittee.service.StatementMappingService;
import com.epam.selectioncommittee.service.StatementService;
import com.epam.selectioncommittee.service.mapper.UserMapper;
import com.epam.selectioncommittee.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = StatementController.class)
@AutoConfigureMockMvc
@Import(WebConfig.class)
public class StatementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StatementService statementService;
    @MockBean
    StatementMappingService statementMappingService;

    UserDto userDto = TestDataUtil.createUserDto();
    User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);

    @Test
    void addApplicantToStatementTest() throws Exception {
        doNothing().when(statementService).addApplicantToStatement(anyLong());
        mockMvc
                .perform(post("/api/v1/statements/1"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(statementService, times(1)).addApplicantToStatement(1L);
    }


    @Test
    void removeApplicantFromStatementTest() throws Exception {
        doNothing().when(statementService).removeApplicantFromStatement(anyLong());
        mockMvc
                .perform(delete("/api/v1/statements/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(statementService, times(1)).removeApplicantFromStatement(1L);
    }

    @Test
    void finaliseTest() throws Exception {
        doNothing().when(statementMappingService).finaliseStatement(anyLong());
        mockMvc
                .perform(post("/api/v1/statements/finalise/1"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(statementMappingService, times(1)).finaliseStatement(1L);
    }

    @Test
    void getApplicantListTest() throws Exception {
        when(statementService.getApplicantList(anyLong())).thenReturn(Collections.singletonList(userDto));
        mockMvc
                .perform(get("/api/v1/statements/applicants/facultyId/"+TestDataUtil.TEST_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(user.getId()))
                .andExpect(jsonPath("$[0].password").value(user.getPassword()));
    }

    @Test
    void getBudgetPlApplicantListTest() throws Exception {
        when(statementService.getBudgetPlApplicantList(any())).thenReturn(Collections.singletonList(userDto));
        mockMvc
                .perform(get("/api/v1/statements/budgetPlApplicants/facultyId/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(user.getId()))
                .andExpect(jsonPath("$[0].password").value(user.getPassword()));
    }

    @Test
    void getNonBudgetPlApplicantListTest() throws Exception {
        when(statementService.getNonBudgetPlApplicantList(anyLong())).thenReturn(Collections.singletonList(userDto));
        mockMvc
                .perform(get("/api/v1/statements/notBudgetPlApplicants/facultyId/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(user.getId()))
                .andExpect(jsonPath("$[0].password").value(user.getPassword()));
    }

}
