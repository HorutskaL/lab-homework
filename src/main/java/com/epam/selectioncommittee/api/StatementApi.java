package com.epam.selectioncommittee.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "API description for SWAGGER documentation")
@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@RequestMapping("/api/v1/statements")
public interface StatementApi {

    @ApiOperation("Add faculty to statement")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{facultyId}")
    void add(@PathVariable Long facultyId);

    @ApiOperation("Finalize statement")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    void finalise(Long facultyId);

    @ApiOperation("Delete user from statement")
    @DeleteMapping(value = "/{userId}")
    void deleteUserFromStatement(@PathVariable Long userId);

}
