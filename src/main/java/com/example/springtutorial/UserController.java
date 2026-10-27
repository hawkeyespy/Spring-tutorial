package com.example.springtutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("/encodePassword")
    public void saveUserWithEncodedPassword(@RequestParam String userName,
                                            @RequestParam String password,
                                            @RequestParam String role) {
        UserEntity user = new UserEntity();
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setRole(role);

        userRepository.save(user);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(),
                        authRequest.password()));
        if (authenticate.isAuthenticated()) {
            String role = authenticate
                    .getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority()
                    .replace("ROLE_", "");
            return jwtService.generateJwtToken(authRequest.username(), role);
        }

        return null;
    }
}
