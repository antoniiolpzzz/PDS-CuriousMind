package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.provider.StrategyProvider;
import com.pds.curiousmind.model.user.User;

import java.util.List;

public enum GameManager {
    INSTANCE;

    private RegisteredCourse currentCourse;
    private RegisteredContentBlock currentContentBlock;
    private QuestionIterator questionIterator;

    private final StrategyProvider strategyProvider;

    //CONSTRUCTOR
    GameManager() {
        //TODO: This need a StrategyProvider to fetch the right strategy for processing questions
        this.strategyProvider = StrategyProvider.INSTANCE;
        this.currentCourse = null;
        this.currentContentBlock = null;
        this.questionIterator = null;
    }

    //GETTERS AND SETTERS
    public RegisteredCourse getCurrentCourse() {
        return currentCourse;
    }

    public RegisteredContentBlock getCurrentContentBlock() {
        return currentContentBlock;
    }

    //METHODS
    public void initializeGame(RegisteredCourse course, RegisteredContentBlock contentBlock){
        this.currentCourse = course;
        this.currentContentBlock = contentBlock;
        Strategy currentStrategy = strategyProvider.getStrategy(course.getStrategy());
        List<Question> processedQuestions = currentStrategy.getProcessedQuestions(contentBlock);
        this.questionIterator = new QuestionIterator(processedQuestions);
    }

    public void deactivateGame() {
        this.currentCourse = null;
        this.currentContentBlock = null;
        this.questionIterator = null;
    }

    public boolean hasNextQuestion() {
        return questionIterator != null && questionIterator.hasNext();
    }

    public Question getNextQuestion() {
        if (hasNextQuestion()) {
            return questionIterator.next();
        }
        return null;
    }

    public void addFailedQuestion(Question question) {
        if (questionIterator != null && question != null) {
            questionIterator.addFailedQuestion(question);
        }
    }

    public void markBlockAsCompleted() {
        if (currentContentBlock != null) {
            currentContentBlock.markAsCompleted();
        }



    }

    public int totalQuestions() {
        return questionIterator != null ? questionIterator.getTotalQuestions() : 0;
    }

    public int questionsLeft() {
        return questionIterator != null ? questionIterator.getQuestionsLeft() : 0;
    }

    public int getFailedCount() {
        return questionIterator != null ? questionIterator.getFailedCount() : 0;
    }




}
