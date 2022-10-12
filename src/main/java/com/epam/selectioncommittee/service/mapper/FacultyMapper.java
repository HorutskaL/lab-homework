package com.epam.selectioncommittee.service.mapper;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FacultyMapper {
    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    FacultyDto mapFacultyToFacultyDto(Faculty faculty);

    Faculty mapFacultyDtoToFaculty(FacultyDto facultyDto);

}
