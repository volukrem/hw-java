package org.example.carshop.service;

import lombok.AllArgsConstructor;

import org.example.carshop.model.User;
import org.example.carshop.model.enums.UserRole;
import org.example.carshop.repository.UserRepository;

import java.util.Collection;

/**
 * Service class for managing User operations.
 */
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Registers a new user.
     *
     * @param user the user to register.
     */
    public void register(User user) {
        userRepository.registerUser(user);
    }

    /**
     * Logs in a user by checking if the credentials match.
     *
     * @param userParam the user attempting to log in.
     * @return true if the credentials match, otherwise false.
     */
    public boolean login(User userParam) {
        return userRepository.findUserByUsername(userParam.getUsername()).getPassword()
                .equals(userParam.getPassword());
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for.
     * @return the user with the specified username, or null if not found.
     */
    public User findUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Filters users based on given criteria.
     *
     * @param name Filter by name.
     * @param userRoles Filter by role
     * @return filtered collection of users.
     */
    public Collection<User> searchUsers(String name, Collection<UserRole> userRoles) {
        return userRepository.filterUsers(name, userRoles);
    }
    /**
     * Updates an existing user in the repository.
     *
     * @param user the request to add.
     */
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
