package org.example.carshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.carshop.model.enums.UserRole;

/**
 * Represents a user in the Car Shop system.
 */
@Data
@AllArgsConstructor
public class User {
    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The credentials of the user.
     */
    private String fullName;

    /**
     * The role of the user.
     */
    private UserRole role = UserRole.CLIENT;

    /**
     *  Manual constructor that only requires username and password, allowing other fields to be null.
     */
    public User(String username, String password) {
        this(username, password, null, null);
    }
    /**
     * Returns a string representation of the user.
     *
     * @return A string representing the user, excluding the password.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
