package com.pds.curiousmind.controller;

import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.library.implementation.UserLibrary;
import com.pds.curiousmind.util.AppConfig;
import com.pds.curiousmind.util.ImageUtils;

/**
 * Singleton controller responsible for managing user authentication, profile, and statistics.
 * <p>
 * This controller provides methods for user login, registration, profile image retrieval,
 * and access to user statistics and level. It acts as a facade for all user-related
 * operations in the application.
 * </p>
 *
 * <p>Usage example:</p>
 * <pre>
 *     boolean loggedIn = UserController.INSTANCE.logIn("username", "password");
 *     Stat stats = UserController.INSTANCE.getUserStats();
 * </pre>
 *
 * @author Antonio López
 * @since 1.0
 */
public enum UserController {
    INSTANCE;
    private static final String USER_IMAGE_API = AppConfig.get("user.image.api");
    private final UserLibrary userLibrary = UserLibrary.INSTANCE;
    private User currentUser;

    /**
     * Returns the currently logged-in user.
     *
     * @return the current {@link User}, or null if no user is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the file path to the current user's profile photo.
     *
     * @return the path to the user's photo, or a default image if not available
     */
    public String getUserPhoto() {
        String urlString = USER_IMAGE_API + (currentUser != null ? currentUser.getUsername() : "user");
        String imagePath = ImageUtils.downloadImage(urlString, ".png");
        return imagePath != null ? imagePath : "icons/button/user.png";
    }

    /**
     * Downloads an image from a given URL and returns its local file path.
     *
     * @param urlString the URL of the image
     * @return the local file path to the downloaded image, or a default image if download fails
     */
    public String downloadImageFromUrl(String urlString) {
        String imagePath = ImageUtils.downloadImage(urlString, ".png");
        return imagePath != null ? imagePath : "icons/stat/time.jpg";
    }

    /**
     * Returns the statistics of the current user.
     *
     * @return the {@link Stat} object for the current user, or null if not logged in
     */
    public Stat getUserStats() {
        return currentUser != null ? currentUser.getStats() : null;
    }

    /**
     * Returns the current user's level.
     *
     * @return the user's level, or 0 if not logged in
     */
    public int getUserLevel() {
        Stat stats = getUserStats();
        return stats != null ? stats.getLevel() : 0;
    }

    /**
     * Attempts to log in a user with the given credentials.
     *
     * @param username the username
     * @param password the password
     * @return true if login is successful, false otherwise
     */
    public boolean logIn(String username, String password) {
        User user = userLibrary.getByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            currentUser = user;
            currentUser.logEntry();
            userLibrary.update(currentUser);
            return true;
        }
        return false;
    }

    /**
     * Registers a new user with the given information.
     *
     * @param fullName the full name of the user
     * @param username the desired username
     * @param email the email address
     * @param password the password
     * @return true if registration is successful, false if the username is already taken
     */
    public boolean signUp(String fullName, String username, String email, String password) {
        if (userLibrary.getByUsername(username) == null) {
            User user = new User(fullName, email, password, username);
            userLibrary.add(user);
            return true;
        } else {
            return false;
        }
    }
}
