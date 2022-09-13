package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.service.FacultyService;
import com.epam.selectioncommittee.service.mapper.FacultyMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public FacultyDto createFaculty(FacultyDto facultyDto) {
        log.info("Create faculty with name {}", facultyDto.getName());
        Faculty faculty = FacultyMapper.INSTANCE.mapFacultyDtoToFaculty(facultyDto);
        faculty = facultyRepository.createFaculty(faculty);
        return FacultyMapper.INSTANCE.mapFacultyToFacultyDto(faculty);
    }

    @Override
    public FacultyDto getFaculty(Long facultyId) {
        log.info("getFaculty by id {}", facultyId);
        Faculty faculty = facultyRepository.getFacultyById(facultyId);
        return FacultyMapper.INSTANCE.mapFacultyToFacultyDto(faculty);
    }

    @Override
    public List<FacultyDto> faculties() {
        log.info("get all faculties");
        return facultyRepository.getAllFaculties()
                .stream()
                .map(FacultyMapper.INSTANCE::mapFacultyToFacultyDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDto updateFaculty(Long facultyId, FacultyDto facultyDto) {
        log.info("updateFaculty with id {}", facultyDto.getId());
        Faculty faculty = FacultyMapper.INSTANCE.mapFacultyDtoToFaculty(facultyDto);
        faculty = facultyRepository.updateFaculty(facultyId, faculty);
        return FacultyMapper.INSTANCE.mapFacultyToFacultyDto(faculty);
    }

    @Override
    public void deleteFaculty(Long facultyId) {
        log.info("deleteFaculty with id {}", facultyId);
        facultyRepository.deleteFaculty(facultyId);
    }
}
