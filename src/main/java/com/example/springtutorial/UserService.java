package com.example.springtutorial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* "UserDetailsService" is a spring security interface which handles the service call on each api call.
   if not used we have to manually call the UserService.getUserByUsername method.

 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsernameAndIsActive(username, true).
                                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = getUserByUsername(username);
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority(user.getRole()))
                .build();
    }
}
