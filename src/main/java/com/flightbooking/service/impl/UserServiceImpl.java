package com.flightbooking.service.impl;

import com.flightbooking.model.User;
import com.flightbooking.repository.UserRepository;
import com.flightbooking.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User createUser(User user) {
        // simple example: prevent duplicate emails
        repo.findByEmail(user.getEmail()).ifPresent(existing -> {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        });
        return repo.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}