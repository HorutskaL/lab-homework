package com.epam.selectioncommittee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/v1")
public interface UserFacultyApi {

    @ApiOperation("Register applicant")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/userFaculty/userEmail/{userEmail}/facultyId/{facultyId}/eieUkLanguage/{eieUkLanguage}/eieMath/{eieMath}/eieHistory/{eieHistory}")
    ResponseEntity<Void> registerUserOnFaculty(@PathVariable("userEmail") String userEmail, @PathVariable("facultyId") int facultyId,
                                               @PathVariable("eieMath") int eieMath, @PathVariable("eieUkLanguage") int eieUkLanguage,
                                               @PathVariable("eieHistory") int eieHistory);

    @ApiOperation("Delete user from faculty")
    @DeleteMapping(value = "/userFaculty/userEmail/{userEmail}/facultyId/{facultyId}")
    ResponseEntity<Void> deleteUser(@PathVariable("userEmail") String userEmail, @PathVariable("facultyId") int facultyId);
}
