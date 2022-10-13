package com.epam.selectioncommittee.service;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;

public interface FacultyMappingService {
    Faculty populateFacultyWithPresentFacultyDtoFields(Faculty faculty, FacultyDto facultyDto);
}
