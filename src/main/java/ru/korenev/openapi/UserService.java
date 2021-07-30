package ru.korenev.openapi;

import ru.korenev.openapi.model.User;

public interface UserService {
    void createUser(User user);

    void deleteUser(Long userId);

    User findUserById(Long userId);

    void updateUser(Long userId, User user);
}
