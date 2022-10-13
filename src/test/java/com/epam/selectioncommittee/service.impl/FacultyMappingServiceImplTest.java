package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.service.mapper.FacultyMapper;
import com.epam.selectioncommittee.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FacultyMappingServiceImplTest {
    @InjectMocks
    FacultyMappingServiceImpl facultyMappingService;

    @Test
    void populateUserWithPresentUserDtoFieldsTest(){
        Faculty expectedFaculty = TestDataUtil.createFaculty();
        FacultyDto facultyDto = FacultyMapper.INSTANCE.mapFacultyToFacultyDto(expectedFaculty);
        Faculty actualFaculty = TestDataUtil.createFaculty();

        facultyMappingService.populateFacultyWithPresentFacultyDtoFields(actualFaculty,facultyDto);

        assertEquals(expectedFaculty.getName(),actualFaculty.getName());
    }
}
