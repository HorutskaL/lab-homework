package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.WebConfig;
import com.epam.selectioncommittee.api.FacultyApi;
import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.service.FacultyService;
import com.epam.selectioncommittee.service.mapper.FacultyMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = {FacultyController.class, FacultyApi.class})
@AutoConfigureMockMvc
@Import(WebConfig.class)
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    FacultyService facultyService;

    FacultyDto facultyDto = TestDataUtil.createFacultyDto();

    Faculty faculty = FacultyMapper.INSTANCE.mapFacultyDtoToFaculty(facultyDto);

    @Test
    void createFacultyTest() throws Exception {
        when(facultyService.createFaculty(any())).thenReturn(facultyDto);
        mockMvc
                .perform(post("/api/v1/faculties")
                        .content(objectMapper.writeValueAsString(facultyDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(facultyDto.getId()));
    }

    @Test
    void getAllFacultiesTest() throws Exception {
        when(facultyService.faculties()).thenReturn(Collections.singletonList(facultyDto));
        mockMvc
                .perform(get("/api/v1/faculties"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(faculty.getId()));
        verify(facultyService, times(1)).faculties();
    }

    @Test
    void updateFacultyTest() throws Exception {
        when(facultyService.updateFaculty(1L,facultyDto)).thenReturn(facultyDto);
        mockMvc
                .perform(patch("/api/v1/faculties/1")
                        .content(objectMapper.writeValueAsString(facultyDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(faculty.getId()));
        verify(facultyService, times(1)).updateFaculty(1L,facultyDto);
    }

    @Test
    void deleteFacultyTest() throws Exception {
        doNothing().when(facultyService).deleteFaculty(any());
        mockMvc
                .perform(delete("/api/v1/faculties/1"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(facultyService, times(1)).deleteFaculty(any());
    }

}
