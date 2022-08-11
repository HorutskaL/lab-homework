package com.epam.selectioncommittee.api;

import com.epam.selectioncommittee.dto.FacultyDto;
import com.epam.selectioncommittee.dto.group.OnCreate;
import com.epam.selectioncommittee.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/v1")
public interface FacultyApi {
    @ApiOperation("Get faculties")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/faculty")
    List<FacultyDto> getAllFaculties();

    @ApiOperation("Create faculty")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/faculty")
    FacultyDto createFaculty(@RequestBody @Validated(OnCreate.class) FacultyDto facultyDto);

    @ApiOperation("Update faculty")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/faculty/{name}")
    FacultyDto updateFaculty(@PathVariable("name") String name, @RequestBody @Validated(OnUpdate.class) FacultyDto facultyDto);

    @ApiOperation("Delete faculty")
    @DeleteMapping(value = "/faculty/{name}")
    void deleteFaculty(@PathVariable String name);
}
