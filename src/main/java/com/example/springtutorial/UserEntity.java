package com.example.springtutorial;

import jakarta.persistence.*;

/**
 * This class represents users table in database.
 *
 * @author Ravi Chandra
 */
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    public UserEntity(Long id) {
        this.id = id;
    }

    public UserEntity(Long id, String username, String password, Boolean isActive, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
    }

    public UserEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setRole(Role role) { this.role = role; }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Role getRole() { return role; }


}
