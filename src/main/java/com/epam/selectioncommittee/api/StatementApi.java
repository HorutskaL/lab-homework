package com.epam.selectioncommittee.api;

import com.epam.selectioncommittee.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/v1/statements")
public interface StatementApi {

    @ApiOperation("Add faculty to statement")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/{facultyId}")
    void add(@PathVariable Long facultyId);

    @ApiOperation("Finalize statement")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/finalise/{facultyId}")
    void finalise(@PathVariable Long facultyId);

    @ApiOperation("Delete user from statement")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    void deleteUserFromStatement(@PathVariable Long userId);

    @ApiOperation("Obtain applicants from the statement")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/applicants/facultyId/{facultyId}")
    List<UserDto> getApplicantList(@PathVariable Long facultyId);

    @ApiOperation("Obtain applicants on budget places from the statement")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/budgetPlApplicants/facultyId/{facultyId}")
    List<UserDto> getBudgetPlApplicantList(@PathVariable Long facultyId);

    @ApiOperation("Obtain applicants on not budget places from the statement")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/notBudgetPlApplicants/facultyId/{facultyId}")
    List<UserDto> getNonBudgetPlApplicantList(@PathVariable Long facultyId);

}
