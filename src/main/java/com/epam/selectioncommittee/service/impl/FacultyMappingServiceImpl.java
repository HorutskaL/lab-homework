package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.service.FacultyMappingService;
import org.springframework.stereotype.Service;

@Service
public class FacultyMappingServiceImpl implements FacultyMappingService {
    @Override
    public Faculty populateFacultyWithPresentFacultyDtoFields(Faculty faculty, FacultyDto facultyDto) {
        faculty.setAmountBudgetPlaces(facultyDto.getAmountBudgetPlaces());
        faculty.setAmountTotalPlaces(facultyDto.getAmountTotalPlaces());
        faculty.setIsEieHistory(faculty.getIsEieHistory());
        faculty.setIsEieMath(faculty.getIsEieMath());
        faculty.setIsEieUkLanguage(facultyDto.getIsEieUkLanguage());
        return faculty;
    }
}
