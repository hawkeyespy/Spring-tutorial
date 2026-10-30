package com.example.springtutorial;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Jpa repository for users table. It performs query on users table.
 *
 * @author Ravi Chandra
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndIsActive(String username, boolean isActive);
}
