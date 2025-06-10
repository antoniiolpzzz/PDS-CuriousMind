package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.question.Question;

public enum GameManager {
    INSTANCE;

    private RegisteredCourse currentCourse;
    private RegisteredContentBlock currentContentBlock;
    private QuestionIterator questionIterator;

    //CONSTRUCTOR
    GameManager() {
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
        this.questionIterator = new QuestionIterator(contentBlock.getQuestions());
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
