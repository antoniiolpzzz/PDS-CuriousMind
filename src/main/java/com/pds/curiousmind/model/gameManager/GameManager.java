package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.provider.StrategyProvider;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public enum GameManager {
    INSTANCE;

    private RegisteredCourse currentCourse;
    private RegisteredContentBlock currentContentBlock;
    private QuestionIterator questionIterator;
    private LocalDateTime gameStartTime;

    private final StrategyProvider strategyProvider;
    private int lifes;

    //CONSTRUCTOR
    GameManager() {
        this.strategyProvider = StrategyProvider.INSTANCE;
        this.currentCourse = null;
        this.currentContentBlock = null;
        this.questionIterator = null;
        this.lifes = 5;
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
        this.gameStartTime = LocalDateTime.now();
        this.lifes = 5;
    }

    public void deactivateGame() {
        this.currentCourse = null;
        this.currentContentBlock = null;
        this.questionIterator = null;
        this.gameStartTime = null;
        this.lifes = 5;
    }

    public boolean hasNextQuestion() {
        return questionIterator != null && questionIterator.hasNext();
    }

    public Question getNextQuestion() {
        if (hasNextQuestion() && lifes > 0) {
            return questionIterator.next();
        }
        return null;
    }

    public void addFailedQuestion(Question question) {
        if (questionIterator != null && question != null) {
            questionIterator.addFailedQuestion(question);
        }
        lifes--;
    }

    public void markBlockAsCompleted() {
        if (currentContentBlock != null) {
            currentContentBlock.markAsCompleted();
        }
    }

    public int getCurrentProgress() {
        return questionIterator != null ?
                ((questionIterator.getTotalQuestions() - (questionIterator.getQuestionsLeft()+1)) * 100) / questionIterator.getTotalQuestions()
                : 0;
    }

    public Long getGameDuration() {
        return gameStartTime != null ? ChronoUnit.SECONDS.between(gameStartTime, LocalDateTime.now()) : 0;
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

    public int getLifes(){
        return lifes;
    }


}
