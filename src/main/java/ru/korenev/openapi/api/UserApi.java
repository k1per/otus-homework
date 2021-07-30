/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.1.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ru.korenev.openapi.api;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.korenev.openapi.model.Error;
import ru.korenev.openapi.model.User;

import javax.validation.Valid;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-20T23:49:05.869983+03:00[Europe/Moscow]")
@Validated
@Api(value = "user", description = "the user API")
public interface UserApi {

    default UserApiDelegate getDelegate() {
        return new UserApiDelegate() {};
    }

    /**
     * POST /user : Create user
     * This can only be done by the logged in user.
     *
     * @param user Created user object (required)
     * @return successful operation (status code 200)
     */
    @ApiOperation(value = "Create user", nickname = "createUser", notes = "This can only be done by the logged in user.", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @PostMapping(
        value = "/user",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User user) {
        return getDelegate().createUser(user);
    }


    /**
     * DELETE /user/{userId}
     * deletes a single user based on the ID supplied
     *
     * @param userId ID of user (required)
     * @return user deleted (status code 204)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "", nickname = "deleteUser", notes = "deletes a single user based on the ID supplied", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "user deleted"),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
    @DeleteMapping(
        value = "/user/{userId}",
        produces = { "application/json" }
    )
    default ResponseEntity<Void> deleteUser(@ApiParam(value = "ID of user",required=true) @PathVariable("userId") Long userId) {
        return getDelegate().deleteUser(userId);
    }


    /**
     * GET /user/{userId}
     * Returns a user based on a single ID, if the user does not have access to the user
     *
     * @param userId ID of user (required)
     * @return user response (status code 200)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "", nickname = "findUserById", notes = "Returns a user based on a single ID, if the user does not have access to the user", response = User.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "user response", response = User.class),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
    @GetMapping(
        value = "/user/{userId}",
        produces = { "application/json" }
    )
    default ResponseEntity<User> findUserById(@ApiParam(value = "ID of user",required=true) @PathVariable("userId") Long userId) {
        return getDelegate().findUserById(userId);
    }


    /**
     * PUT /user/{userId}
     * Update user with User ID supplied
     *
     * @param userId ID of user (required)
     * @param user  (optional)
     * @return user updated (status code 200)
     *         or unexpected error (status code 200)
     */
    @ApiOperation(value = "", nickname = "updateUser", notes = "Update user with User ID supplied", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "user updated"),
        @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
    @PutMapping(
        value = "/user/{userId}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> updateUser(@ApiParam(value = "ID of user",required=true) @PathVariable("userId") Long userId,@ApiParam(value = ""  )  @Valid @RequestBody(required = false) User user) {
        return getDelegate().updateUser(userId, user);
    }

}