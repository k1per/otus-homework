package ru.korenev.openapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korenev.openapi.UserService;
import ru.korenev.openapi.model.User;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-07-20T23:49:05.869983+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.userService.base-path:/api/v1}")
public class UserApiController implements UserApi {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> createUser(User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<User> findUserById(Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @Override
    public ResponseEntity<Void> updateUser(Long userId, User user) {
        userService.updateUser(userId, user);
        return ResponseEntity.ok().build();
    }
}
