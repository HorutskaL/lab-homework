package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.service.FacultyMappingService;
import com.epam.selectioncommittee.service.exception.FacultyNotFoundException;
import com.epam.selectioncommittee.service.mapper.FacultyMapper;
import com.epam.selectioncommittee.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceImplTest {
    @InjectMocks
    private FacultyServiceImpl facultyService;

    @Mock
    private FacultyRepository facultyRepository;
    @Spy
    private FacultyMappingService facultyMappingService = new FacultyMappingServiceImpl();

    private final static String TEST_FACULTY_NAME = "TestFacultyName";

    @Test
    void getFacultyTest() {
        Faculty expectedFaculty = TestDataUtil.createFaculty();
        when(facultyRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(expectedFaculty));
        FacultyDto facultyDto1 = facultyService.getFaculty(TestDataUtil.TEST_ID);
        FacultyDto facultyDto2 = FacultyMapper.INSTANCE.mapFacultyToFacultyDto(expectedFaculty);
        assertEquals(facultyDto1, facultyDto2);
    }

    @Test
    void getFacultyFacultyNotFoundTest() {
        when(facultyRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.empty());
        assertThrows(FacultyNotFoundException.class, () -> facultyService.getFaculty(TestDataUtil.TEST_ID));
    }

    @Test
    void listFacultyTest() {
        List<Faculty> facultyList = Collections.singletonList(TestDataUtil.createFaculty());
        when(facultyRepository.findAll()).thenReturn(facultyList);
        List<FacultyDto> facultyDtoList = facultyService.faculties();
        assertThat(facultyDtoList, hasSize(1));
    }

    @Test
    void createFacultyTest() {
        Faculty testFaculty = Faculty.builder().id(TestDataUtil.TEST_ID).name(TEST_FACULTY_NAME).build();
        FacultyDto testFacultyDto = FacultyDto.builder().id(TestDataUtil.TEST_ID).name(TEST_FACULTY_NAME).build();

        when(facultyRepository.save(any())).thenReturn(testFaculty);

        FacultyDto facultyDto = facultyService.createFaculty(testFacultyDto);
        assertEquals(facultyDto.getName(), testFaculty.getName());
    }

    @Test
    void updateFacultyTest() {
        Faculty faculty = TestDataUtil.createFaculty();
        FacultyDto facultyDto = FacultyMapper.INSTANCE.mapFacultyToFacultyDto(faculty);
        when(facultyRepository.findById(any())).thenReturn(Optional.ofNullable(faculty));
        when(facultyRepository.save(any())).thenReturn(faculty);
        when(facultyMappingService.populateFacultyWithPresentFacultyDtoFields(faculty, facultyDto)).thenReturn(faculty);
        Long newId = 2L;
        facultyService.updateFaculty(newId, facultyDto);
        verify(facultyRepository, times(1)).save(any());
    }
    @Test
    void updateFacultyFacultyNotFoundTest() {
        FacultyDto facultyDto = TestDataUtil.createFacultyDto();
        when(facultyRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.empty());
        assertThrows(FacultyNotFoundException.class, ()->facultyService.updateFaculty(facultyDto.getId(),facultyDto));
    }

    @Test
    void deleteFacultyFacultyNotFoundTest() {
        when(facultyRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.empty());
        assertThrows(FacultyNotFoundException.class,()->facultyService.deleteFaculty(TestDataUtil.TEST_ID));
    }

    @Test
    void deleteFacultyTest() {
        Faculty faculty = TestDataUtil.createFaculty();
        when(facultyRepository.findById(TestDataUtil.TEST_ID)).thenReturn(Optional.ofNullable(faculty));
        facultyService.deleteFaculty(Objects.requireNonNull(faculty).getId());
        verify(facultyRepository, times(1)).delete(faculty);
    }

    @Test
    void listPageAndSortingFacultyByBudgetPlTest() {
        when(facultyRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        facultyService.listPageAndSortingFacultyByBudgetPl(1, 1);
        verify(facultyRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void listPageAndSortingFacultyByTotalPlTest() {
        when(facultyRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        facultyService.listPageAndSortingFacultyByTotalPl(1, 1);
        verify(facultyRepository, times(1)).findAll(any(Pageable.class));
    }

}
