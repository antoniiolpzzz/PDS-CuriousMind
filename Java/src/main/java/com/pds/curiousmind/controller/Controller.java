package com.pds.curiousmind.controller;

import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.library.implementation.CourseLibrary;
import com.pds.curiousmind.model.library.implementation.UserLibrary;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.stat.Stat;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.user.User;
import java.io.File;
import java.util.List;

/**
 * Singleton main controller that orchestrates user, course, and game operations.
 * <p>
 * This controller acts as a facade, delegating all business logic to the corresponding
 * domain-specific controllers: {@link UserController}, {@link CourseController}, and {@link GameController}.
 * It provides a unified API for managing users, courses, and game sessions in the application.
 * </p>
 *
 * <p>Usage example:</p>
 * <pre>
 *     Controller.INSTANCE.logIn("user", "pass");
 *     List&lt;Course&gt; courses = Controller.INSTANCE.getAllCourses();
 * </pre>
 *
 * @author Antonio López
 * @since 1.0
 */
public enum Controller {
    INSTANCE;
    private final CourseController courseController = CourseController.INSTANCE;
    private final UserController userController = UserController.INSTANCE;
    private final GameController gameController = GameController.INSTANCE;

    // User related methods

    /**
     * Returns the currently logged-in user.
     *
     * @return the current {@link User}, or null if no user is logged in
     */
    public User getCurrentUser() {
        return userController.getCurrentUser();
    }

    /**
     * Returns the file path to the current user's profile photo.
     *
     * @return the path to the user's photo, or a default image if not available
     */
    public String getUserPhoto() {
        return userController.getUserPhoto();
    }

    /**
     * Downloads an image from a given URL and returns its local file path.
     *
     * @param urlString the URL of the image
     * @return the local file path to the downloaded image, or a default image if download fails
     */
    public String downloadImageFromUrl(String urlString) {
        return userController.downloadImageFromUrl(urlString);
    }

    /**
     * Returns the statistics of the current user.
     *
     * @return the {@link Stat} object for the current user, or null if not logged in
     */
    public Stat getUserStats() {
        return userController.getUserStats();
    }

    /**
     * Returns the current user's level.
     *
     * @return the user's level, or 0 if not logged in
     */
    public int getUserLevel() {
        return userController.getUserLevel();
    }

    /**
     * Attempts to log in a user with the given credentials.
     *
     * @param username the username
     * @param password the password
     * @return true if login is successful, false otherwise
     */
    public boolean logIn(String username, String password) {
        return userController.logIn(username, password);
    }

    /**
     * Registers a new user with the provided information.
     *
     * @param fullName the user's full name
     * @param username the desired username
     * @param email    the user's email address
     * @param password the desired password
     * @return true if registration is successful, false if username already exists
     */
    public boolean signUp(String fullName, String username, String email, String password) {
        return userController.signUp(fullName, username, email, password);
    }

    // Course related methods

    /**
     * Returns the list of registered courses for the current user.
     *
     * @return a list of {@link RegisteredCourse} objects, or an empty list if none
     */
    public List<RegisteredCourse> getRegisteredCourses() {
        return courseController.getRegisteredCourses(getCurrentUser());
    }

    /**
     * Returns all available courses that the current user can register for.
     *
     * @return a list of {@link Course} objects
     */
    public List<Course> getAllCourses() {
        return courseController.getAllCourses(getCurrentUser());
    }

    /**
     * Serializes a course to a JSON file.
     *
     * @param course the {@link Course} to serialize
     * @return the generated JSON {@link File}
     */
    public File getJsonFromCourse(Course course) {
        return courseController.getJsonFromCourse(course);
    }

    /**
     * Creates a new course from a JSON file and adds it to the course library.
     *
     * @param jsonFile the JSON {@link File} representing the course
     * @return the created {@link Course}
     */
    public Course createCourseFromJson(File jsonFile) {
        return courseController.createCourseFromJson(jsonFile);
    }

    /**
     * Registers the current user in a course with the specified strategy.
     *
     * @param course   the {@link Course} to register in
     * @param strategy the {@link StrategyType} to use
     */
    public void createRegisteredCourse(Course course, StrategyType strategy) {
        courseController.createRegisteredCourse(getCurrentUser(), course, strategy);
    }

    /**
     * Initializes sample courses on the first application run.
     */
    public void initializeSamplesOnFirstOpen() {
        courseController.initializeSamplesOnFirstOpen();
    }

    // Game related methods

    /**
     * Validates the user's answer for a given question.
     *
     * @param question the {@link Question} to validate
     * @param answer   the user's answer
     * @return true if the answer is correct, false otherwise
     */
    public boolean validateAnswer(Question question, String answer) {
        return gameController.validateAnswer(question, answer);
    }

    /**
     * Returns the current progress in the content block.
     *
     * @return the progress as an integer value
     */
    public int getBlockProgress() {
        return gameController.getBlockProgress();
    }

    /**
     * Returns the number of lives left in the current game session.
     *
     * @return the number of lives left
     */
    public int getLivesLeft() {
        return gameController.getLivesLeft();
    }

    /**
     * Initializes the game manager for a registered course and content block.
     *
     * @param course       the {@link RegisteredCourse} to play
     * @param contentBlock the {@link RegisteredContentBlock} to play
     * @return the first {@link Question} of the session
     */
    public Question initializeGameManager(RegisteredCourse course, RegisteredContentBlock contentBlock) {
        return gameController.initializeGameManager(course, contentBlock);
    }

    /**
     * Returns the next question in the current game session.
     *
     * @return the next {@link Question}, or null if there are no more questions
     */
    public Question getNextQuestion() {
        return gameController.getNextQuestion();
    }

    /**
     * Ends the current game session.
     */
    public void endGame() {
        gameController.endGame();
    }

    /**
     * Adds a failed question to the current game session.
     *
     * @param question the {@link Question} that was answered incorrectly
     */
    public void addFailedQuestion(Question question) {
        gameController.addFailedQuestion(question);
    }

    /**
     * Marks the current content block as completed and updates user stats.
     *
     * @param difficulty the {@link Difficulty} of the completed block
     */
    public void completeContentBlock(Difficulty difficulty) {
        gameController.completeContentBlock(getCurrentUser(), difficulty);
    }

    /**
     * Returns the number of points awarded for a given difficulty.
     *
     * @param difficulty the {@link Difficulty}
     * @return the number of points for the difficulty
     */
    public int getPointsForDifficulty(Difficulty difficulty) {
        return gameController.getPointsForDifficulty(difficulty);
    }
}
    // *************
    // ***** QUESTION FUNCTIONS
    // ...existing code...
