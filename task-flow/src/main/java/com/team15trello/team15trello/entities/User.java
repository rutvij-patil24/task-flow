package com.team15trello.team15trello.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Override
    public String toString() {
        return "User{" +
                "Id=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "first_name",nullable = false,length = 20)
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 20)
    private String lastName;
    @Column(name = "username",nullable = false,length = 50, unique = true)
    private String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
