package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.util.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An iterator for traversing and managing a list of questions in a content block or game session.
 * <p>
 * This class provides sequential access to questions, tracks the current position, and allows failed questions
 * to be re-added for retry. It also keeps count of failed questions for progress tracking.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Sequentially iterate through a list of questions.</li>
 *   <li>Track the current index and number of failed questions.</li>
 *   <li>Allow failed questions to be re-added for further attempts.</li>
 *   <li>Implements the {@link java.util.Iterator} interface for compatibility with Java collections.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     QuestionIterator iterator = new QuestionIterator(questionsList);
 *     while (iterator.hasNext()) {
 *         Question q = iterator.next();
 *         // ... process question ...
 *     }
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
public class QuestionIterator implements Iterator<Question> {

    /**
     * The list of questions to iterate over. A defensive copy is made to prevent external modification.
     */
    private final List<Question> questions;
    /**
     * The current index in the questions list.
     */
    private int currentIndex;
    /**
     * The number of failed questions that have been re-added.
     */
    private int failedCount;

    /**
     * Constructs a QuestionIterator with the specified list of questions.
     * A defensive copy of the list is made.
     *
     * @param questions the list of questions to iterate over
     */
    public QuestionIterator(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
        this.currentIndex = 0;
        this.failedCount = 0;
    }

    /**
     * Returns {@code true} if there are more questions to iterate over.
     *
     * @return {@code true} if there is a next question, {@code false} otherwise
     */
    @Override
    public boolean hasNext() {
        return currentIndex < questions.size();
    }

    /**
     * Returns the next question in the list, or logs an error and returns {@code null} if none remain.
     *
     * @return the next {@link Question}, or {@code null} if no more questions are available
     */
    @Override
    public Question next() {
        if (!hasNext()) {
            Logger.error("No more questions available.");
            return null;
        }
        return questions.get(currentIndex++);
    }

    /**
     * Returns the total number of questions in the iterator.
     *
     * @return the total number of questions
     */
    public int getTotalQuestions() {
        return questions.size();
    }

    /**
     * Returns the number of questions left to be answered.
     *
     * @return the number of questions left
     */
    public int getQuestionsLeft() {
        return questions.size() - currentIndex;
    }

    /**
     * Adds a failed question to the end of the list and increments the failed count.
     *
     * @param question the failed {@link Question} to re-add
     */
    public void addFailedQuestion(Question question) {
        if (question != null) {
            questions.add(question);
            failedCount++;
        }
    }

    /**
     * Returns the number of failed questions that have been re-added.
     *
     * @return the number of failed questions
     */
    public int getFailedCount() {
        return failedCount;
    }
}
