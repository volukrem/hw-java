package org.example.carshop.repository;

import lombok.AllArgsConstructor;
import org.example.carshop.model.User;
import org.example.carshop.model.enums.UserRole;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository class for managing users.
 */
@AllArgsConstructor
public class UserRepository {
    private final Map<String, User> userMap;

    /**
     * Registers a new user in the repository.
     *
     * @param user The user object to register.
     */
    public void registerUser(User user) {
        userMap.put(user.getUsername(), user);
    }

    /**
     * Updates a user in the repository.
     *
     * @param user The user object to update.
     */
    public void updateUser(User user) {
        userMap.put(user.getUsername(), user);
    }

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The user object with the specified username, or null if not found.
     */
    public User findUserByUsername(String username) {
        return userMap.get(username);
    }

    /**
     * Filters users based on given criteria.
     *
     * @param name Filter by name.
     * @param userRoles Filter by roles.
     * @return filtered collection of users.
     */

    public Collection<User> filterUsers(String name, Collection<UserRole> userRoles) {
        return userMap.values().stream()
                .filter(user -> (name == null || user.getUsername().contains(name))
                        && (userRoles == null || userRoles.contains(user.getRole())))
                .collect(Collectors.toList());
    }
}
