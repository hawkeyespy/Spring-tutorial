package com.example.springtutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * To add users in users table in database.
 * It encodes password while storing password in table.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/encodePassword")
    public void saveUserWithEncodedPassword(@RequestParam String userName, @RequestParam String password) {
        UserEntity user = new UserEntity();
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);

        userRepository.save(user);
    }
}
