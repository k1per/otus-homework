package ru.korenev.openapi.api;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.korenev.openapi.UserRepository;
import ru.korenev.openapi.UserService;
import ru.korenev.openapi.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Timed(percentiles = {0.5, 0.95, 0.99}, value = "createUser")
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
         userRepository.deleteById(userId);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, User user) {
        Optional<User> savedUserOpt = userRepository.findById(userId);
        if(savedUserOpt.isPresent()){
            User savedUser = savedUserOpt.get();
            savedUser.setEmail(user.getEmail());
            savedUser.setFirstName(user.getFirstName());
            savedUser.setLastName(user.getLastName());
            savedUser.setPhone(user.getPhone());
            savedUser.username(user.getUsername());
        } else {
            throw new RuntimeException("User does not exist");
        }
    }
}
