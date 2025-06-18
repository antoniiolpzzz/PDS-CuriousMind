package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.provider.StrategyProvider;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Manages the state and logic of a game session for a registered course and content block.
 * <p>
 * The GameManager is responsible for initializing and deactivating game sessions, tracking the current course,
 * content block, question iterator, game start time, and remaining lives. It also provides methods to retrieve
 * questions, track progress, handle failed questions, and compute game duration.
 * <p>
 * This class is implemented as a singleton enum (INSTANCE) to ensure a single game manager exists in the application.
 *
 * <h2>Key Responsibilities:</h2>
 * <ul>
 *   <li>Initialize and deactivate game sessions for a user.</li>
 *   <li>Track the current registered course and content block being played.</li>
 *   <li>Manage the question iterator and lives for the session.</li>
 *   <li>Provide access to the next question, progress, and statistics.</li>
 *   <li>Handle failed questions and mark content blocks as completed.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     GameManager.INSTANCE.initializeGame(registeredCourse, registeredContentBlock);
 *     Question q = GameManager.INSTANCE.getNextQuestion();
 *     // ...
 *     GameManager.INSTANCE.deactivateGame();
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
public enum GameManager {
    INSTANCE;

    /** The currently active registered course for the game session. */
    private RegisteredCourse currentCourse;
    /** The currently active registered content block for the game session. */
    private RegisteredContentBlock currentContentBlock;
    /** Iterator for the questions in the current content block. */
    private QuestionIterator questionIterator;
    /** The start time of the current game session. */
    private LocalDateTime gameStartTime;
    /** Provides strategies for question ordering and processing. */
    private final StrategyProvider strategyProvider;
    /** The number of lives remaining in the current game session. */
    private int lives;

    /**
     * Constructs the GameManager singleton and initializes its state.
     * Sets the default number of lives to 5.
     */
    GameManager() {
        this.strategyProvider = StrategyProvider.INSTANCE;
        this.currentCourse = null;
        this.currentContentBlock = null;
        this.questionIterator = null;
        this.lives = 5;
    }

    // GETTERS AND SETTERS

    /**
     * Returns the currently active registered course.
     *
     * @return the current registered course, or null if none is active
     */
    public RegisteredCourse getCurrentCourse() {
        return currentCourse;
    }

    /**
     * Returns the currently active registered content block.
     *
     * @return the current registered content block, or null if none is active
     */
    public RegisteredContentBlock getCurrentContentBlock() {
        return currentContentBlock;
    }

    // METHODS

    /**
     * Initializes a new game session for the given course and content block.
     * Sets up the question iterator, resets lives, and records the start time.
     *
     * @param course the registered course to play
     * @param contentBlock the registered content block to play
     */
    public void initializeGame(RegisteredCourse course, RegisteredContentBlock contentBlock){
        this.currentCourse = course;
        this.currentContentBlock = contentBlock;
        Strategy currentStrategy = strategyProvider.getStrategy(course.getStrategy());
        List<Question> processedQuestions = currentStrategy.getProcessedQuestions(contentBlock);
        this.questionIterator = new QuestionIterator(processedQuestions);
        this.gameStartTime = LocalDateTime.now();
        this.lives = 5;
    }

    /**
     * Deactivates the current game session, clearing all state and resetting lives.
     */
    public void deactivateGame() {
        this.currentCourse = null;
        this.currentContentBlock = null;
        this.questionIterator = null;
        this.gameStartTime = null;
        this.lives = 5;
    }

    /**
     * Checks if there is a next question available in the current session.
     *
     * @return true if there is a next question, false otherwise
     */
    public boolean hasNextQuestion() {
        return questionIterator != null && questionIterator.hasNext();
    }

    /**
     * Returns the next question in the current session if available and lives remain.
     *
     * @return the next Question, or null if none available or out of lives
     */
    public Question getNextQuestion() {
        if (hasNextQuestion() && lives > 0) {
            return questionIterator.next();
        }
        return null;
    }

    /**
     * Adds a failed question to the iterator and decrements the number of lives.
     *
     * @param question the failed question to add
     */
    public void addFailedQuestion(Question question) {
        if (questionIterator != null && question != null) {
            questionIterator.addFailedQuestion(question);
        }
        decrementLives();
    }

    /**
     * Checks if the user has any lives remaining.
     *
     * @return true if lives are available, false otherwise
     */
    private boolean decrementLives() {
        if (lives > 0) {
            lives--;
            return true;
        }
        return false;
    }

    /**
     * Marks the current content block as completed, if one is active.
     */
    public void markBlockAsCompleted() {
        if (currentContentBlock != null) {
            currentContentBlock.markAsCompleted();
        }
    }

    /**
     * Returns the current progress as a percentage of questions answered.
     *
     * @return the progress percentage (0-100)
     */
    public int getCurrentProgress() {

        int value = (questionIterator.getTotalQuestions() - (questionIterator.getQuestionsLeft()+1)) * 100;

        return (questionIterator != null && value > 0) ?
                (value / questionIterator.getTotalQuestions())
                : 0;
    }

    /**
     * Returns the duration of the current game session in seconds.
     *
     * @return the game duration in seconds, or 0 if not started
     */
    public Long getGameDuration() {
        return gameStartTime != null ? ChronoUnit.SECONDS.between(gameStartTime, LocalDateTime.now()) : 0;
    }

    /**
     * Returns the total number of questions in the current session.
     *
     * @return the total number of questions, or 0 if not initialized
     */
    public int totalQuestions() {
        return questionIterator != null ? questionIterator.getTotalQuestions() : 0;
    }

    /**
     * Returns the number of questions left in the current session.
     *
     * @return the number of questions left, or 0 if not initialized
     */
    public int questionsLeft() {
        return questionIterator != null ? questionIterator.getQuestionsLeft() : 0;
    }

    /**
     * Returns the number of failed questions in the current session.
     *
     * @return the number of failed questions, or 0 if not initialized
     */
    public int getFailedCount() {
        return questionIterator != null ? questionIterator.getFailedCount() : 0;
    }

    /**
     * Returns the number of lives remaining in the current session.
     *
     * @return the number of lives
     */
    public int getLives(){
        return lives;
    }
}
