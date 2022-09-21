package com.epam.selectioncommittee.service.impl;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.model.Faculty;
import com.epam.selectioncommittee.repository.FacultyRepository;
import com.epam.selectioncommittee.service.FacultyMappingService;
import com.epam.selectioncommittee.service.FacultyService;
import com.epam.selectioncommittee.service.exception.FacultyNotFoundException;
import com.epam.selectioncommittee.service.mapper.FacultyMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyMappingService facultyMappingService;

    @Override
    public FacultyDto createFaculty(FacultyDto facultyDto) {
        log.info("Create faculty with name {}", facultyDto.getName());
        Faculty faculty = FacultyMapper.INSTANCE.mapFacultyDtoToFaculty(facultyDto);
        faculty = facultyRepository.save(faculty);
        return FacultyMapper.INSTANCE.mapFacultyToFacultyDto(faculty);
    }

    @Override
    public FacultyDto getFaculty(Long facultyId) {
        log.info("getFaculty by id {}", facultyId);
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        return FacultyMapper.INSTANCE.mapFacultyToFacultyDto(faculty);
    }

    @Override
    public List<FacultyDto> faculties() {
        log.info("get all faculties");
        return facultyRepository.findAll()
                .stream()
                .map(FacultyMapper.INSTANCE::mapFacultyToFacultyDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDto updateFaculty(Long facultyId, FacultyDto facultyDto) {
        log.info("updateFaculty with id {}", facultyDto.getId());
        Faculty persistedFaculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        persistedFaculty = facultyMappingService.populateFacultyWithPresentFacultyDtoFields(persistedFaculty, facultyDto);
        Faculty storedFaculty = facultyRepository.save(persistedFaculty);
        return FacultyMapper.INSTANCE.mapFacultyToFacultyDto(storedFaculty);
    }

    @Override
    public void deleteFaculty(Long facultyId) {
        log.info("deleteFaculty with id {}", facultyId);
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        facultyRepository.delete(faculty);
        log.info("Faculty with id {} successfully deleted", facultyId);
    }

    @Override
    public Page<Faculty> listPageAndSortingFacultyByBudgetPl(int page, int size) {
        Pageable sorted = PageRequest.of(page, size, Sort.by("amountBudgetPlaces"));
        return facultyRepository.findAll(sorted);
    }

    @Override
    public Page<Faculty> listPageAndSortingFacultyByTotalPl(int page, int size) {
        Pageable sorted = PageRequest.of(page, size, Sort.by("amountTotalPlaces"));
        return facultyRepository.findAll(sorted);
    }

    @Override
    public List<Faculty> listPageAndSortingFacultyByFacultyId(int page, int size) {
        Pageable sorted = PageRequest.of(page, size, Sort.by("facultyId"));
        return facultyRepository.findAll(sorted).stream().collect(Collectors.toList());
    }
}
