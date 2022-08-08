package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.service.StatementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
public class StatementController {
    public final StatementService statementService;

    @ApiOperation("Add faculty to statement")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/statement/{facultyId}")
    public void add(@PathVariable String facultyId) {
        statementService.addApplicantToStatement(facultyId);
    }

    @ApiOperation("Finalize statement")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/statement")
    public void finalise() {
        statementService.finaliseStatement();
    }

    @ApiOperation("Delete user from statement")
    @DeleteMapping(value = "/statement/{userEmail}")
    public void deleteUserFromStatement(@PathVariable String userEmail) {
        statementService.removeApplicantFromStatement(userEmail);
    }
}
