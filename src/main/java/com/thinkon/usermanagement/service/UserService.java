package com.thinkon.usermanagement.service;

import com.thinkon.usermanagement.model.User;
import com.thinkon.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*Creates a new user and stores it in the database.*/
    public User createUser(User user) {
        logger.info("new user", user.getUsername());
        return userRepository.save(user);
    }

    /*Retrieves all users from the database.*/
    public List<User> getAllUsers() {
        logger.info("Retrieving all users from the database.");
        return userRepository.findAll();
    }

    /*Retrieves a user by ID.*/
    public Optional<User> getUserById(Long id) {
        logger.info("Fetching user with ID: ", id);
        return userRepository.findById(id);
    }

    /* Updates an existing user*/
    public User updateUser(Long id, User userDetails) {
        logger.info("Updating user with ID: ", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));

        user.setUsername(userDetails.getUsername());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());

        User updatedUser = userRepository.save(user);
        logger.info("User with ID {} updated successfully.", id);
        return updatedUser;
    }

    /* Deletes a user by ID.?*/
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID:", id);
        userRepository.deleteById(id);
        logger.info("User with ID deleted successfully.", id);
    }
}
