package com.pds.curiousmind.controller;

import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.gameManager.GameManager;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.model.library.implementation.UserLibrary;

/**
 * Singleton controller responsible for managing game logic and session flow.
 * <p>
 * This controller provides methods for handling game sessions, question flow, progress tracking,
 * answer validation, and user experience updates. It acts as a facade for all game-related
 * operations in the application.
 * </p>
 *
 * <p>Usage example:</p>
 * <pre>
 *     GameController.INSTANCE.initializeGameManager(course, contentBlock);
 *     Question q = GameController.INSTANCE.getNextQuestion();
 *     boolean correct = GameController.INSTANCE.validateAnswer(q, "answer");
 * </pre>
 *
 * @author Antonio López
 * @since 1.0
 */
public enum GameController {
    INSTANCE;

    private final UserLibrary userLibrary = UserLibrary.INSTANCE;
    private final GameManager gameManager = GameManager.INSTANCE;

    /**
     * Returns the current progress in the content block.
     *
     * @return the progress as an integer value
     */
    public int getBlockProgress() {
        return gameManager.getCurrentProgress();
    }

    /**
     * Returns the number of lives left in the current game session.
     *
     * @return the number of lives left
     */
    public int getLivesLeft() {
        return gameManager.getLives();
    }

    /**
     * Initializes the game manager for a registered course and content block.
     *
     * @param course the {@link RegisteredCourse} to play
     * @param contentBlock the {@link RegisteredContentBlock} to play
     * @return the first {@link Question} of the session
     */
    public Question initializeGameManager(RegisteredCourse course, RegisteredContentBlock contentBlock) {
        gameManager.initializeGame(course, contentBlock);
        return gameManager.getNextQuestion();
    }

    /**
     * Returns the next question in the current game session.
     *
     * @return the next {@link Question}, or null if there are no more questions
     */
    public Question getNextQuestion() {
        return gameManager.getNextQuestion();
    }

    /**
     * Ends the current game session.
     */
    public void endGame() {
        gameManager.deactivateGame();
    }

    /**
     * Adds a failed question to the current game session.
     *
     * @param question the {@link Question} that was answered incorrectly
     */
    public void addFailedQuestion(Question question) {
        gameManager.addFailedQuestion(question);
    }

    /**
     * Marks the current content block as completed and updates user stats.
     *
     * @param user the {@link User} who completed the block
     * @param difficulty the {@link Difficulty} of the completed block
     */
    public void completeContentBlock(User user, Difficulty difficulty) {
        gameManager.markBlockAsCompleted();
        user.addExperiencePoints(getPointsForDifficulty(difficulty));
        user.addTimeSpent(gameManager.getGameDuration());
        userLibrary.update(user);
    }

    /**
     * Returns the number of points awarded for a given difficulty.
     *
     * @param difficulty the {@link Difficulty}
     * @return the number of points for the difficulty
     */
    public int getPointsForDifficulty(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY -> 100;
            case MEDIUM -> 200;
            case HARD -> 300;
        };
    }

    /**
     * Validates the user's answer for a given question.
     *
     * @param question the {@link Question} to validate
     * @param answer the user's answer
     * @return true if the answer is correct, false otherwise
     */
    public boolean validateAnswer(Question question, String answer) {
        return question.validateAnswer(answer);
    }
}
