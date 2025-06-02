package com.example.quicknotes.services;

import com.example.quicknotes.model.User;

public interface UserService {
    public User register(User user);
    public User findByUsername(String username);
    public User authenticate(User userInput);
}
