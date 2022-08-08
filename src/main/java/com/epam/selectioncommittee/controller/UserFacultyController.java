package com.epam.selectioncommittee.controller;

import com.epam.selectioncommittee.service.UserFacultyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class UserFacultyController {
    private final UserFacultyService userFacultyService;

    @ApiOperation("Register applicant")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/userFaculty/userEmail/{userEmail}/facultyId/{facultyId}/eieUkLanguage/{eieUkLanguage}/eieMath/{eieMath}/eieHistory/{eieHistory}")
    public ResponseEntity<Void> registerUserOnFaculty(@PathVariable("userEmail") String userEmail, @PathVariable("facultyId") int facultyId,
                                                      @PathVariable("eieMath") int eieMath, @PathVariable("eieUkLanguage") int eieUkLanguage,
                                                      @PathVariable("eieHistory") int eieHistory) {
        log.info("Applicant userEmail {} registered on facultyId {}", userEmail, facultyId);
        userFacultyService.registerUserOnFaculty(userEmail, facultyId, eieUkLanguage, eieMath, eieHistory);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Delete user from faculty")
    @DeleteMapping(value = "/userFaculty/userEmail/{userEmail}/facultyId/{facultyId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userEmail") String userEmail, @PathVariable("facultyId") int facultyId) {
        userFacultyService.deleteUser(userEmail, facultyId);
        return ResponseEntity.noContent().build();
    }
}
