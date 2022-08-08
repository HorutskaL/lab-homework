package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.dto.group.OnCreate;
import com.epam.selectioncommittee.dto.group.OnUpdate;
import com.epam.selectioncommittee.service.FacultyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class FacultyController {
    private final FacultyService facultyService;

    @ApiOperation("Get faculties")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/faculty")
    public List<FacultyDto> getAllFaculties() {
        log.info("getAllFaculties");
        return facultyService.faculties();
    }

    @ApiOperation("Create faculty")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/faculty")
    public FacultyDto createFaculty(@RequestBody @Validated(OnCreate.class) FacultyDto facultyDto) {
        log.info("create faculty");
        return facultyService.createFaculty(facultyDto);
    }

    @ApiOperation("Update faculty")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/faculty/{name}")
    public FacultyDto updateFaculty(@PathVariable("name") String name, @RequestBody @Validated(OnUpdate.class) FacultyDto facultyDto) {
        log.info("update faculty with name {}", name);
        return facultyService.updateFaculty(name, facultyDto);
    }

    @ApiOperation("Delete faculty")
    @DeleteMapping(value = "/faculty/{name}")
    public void deleteFaculty(@PathVariable String name) {
        facultyService.deleteFaculty(name);
    }


}
