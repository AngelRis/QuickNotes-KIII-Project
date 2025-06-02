package com.example.quicknotes.services.impl;

import com.example.quicknotes.model.User;
import com.example.quicknotes.repository.UserRepository;
import com.example.quicknotes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        Optional<User> userOptional=userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if(user.getPassword().isEmpty() || user.getUsername().isEmpty()) {
            throw new RuntimeException("Please enter your username and password");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User authenticate(User userInput) {
        User user = userRepository.findByUsername(userInput.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(userInput.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
