package com.example.vetclinic.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String matchingPassword;
    private String email;
    private List<String> roles;

    public User() {
    }
}
