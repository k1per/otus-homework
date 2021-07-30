package ru.korenev.openapi.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import ru.korenev.openapi.model.User;

import java.util.Optional;

/**
 * A delegate to be called by the {@link UserApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-20T23:49:05.869983+03:00[Europe/Moscow]")
public interface UserApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /user : Create user
     * This can only be done by the logged in user.
     *
     * @param user Created user object (required)
     * @return successful operation (status code 200)
     * @see UserApi#createUser
     */
    default ResponseEntity<Void> createUser(User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /user/{userId}
     * deletes a single user based on the ID supplied
     *
     * @param userId ID of user (required)
     * @return user deleted (status code 204)
     *         or unexpected error (status code 200)
     * @see UserApi#deleteUser
     */
    default ResponseEntity<Void> deleteUser(Long userId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /user/{userId}
     * Returns a user based on a single ID, if the user does not have access to the user
     *
     * @param userId ID of user (required)
     * @return user response (status code 200)
     *         or unexpected error (status code 200)
     * @see UserApi#findUserById
     */
    default ResponseEntity<User> findUserById(Long userId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"phone\" : \"phone\", \"id\" : 0, \"email\" : \"email\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /user/{userId}
     * Update user with User ID supplied
     *
     * @param userId ID of user (required)
     * @param user  (optional)
     * @return user updated (status code 200)
     *         or unexpected error (status code 200)
     * @see UserApi#updateUser
     */
    default ResponseEntity<Void> updateUser(Long userId,
        User user) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
