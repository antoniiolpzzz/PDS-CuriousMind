package com.pds.curiousmind.model.gameManager;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.util.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionIterator implements Iterator<Question> {

    private final List<Question> questions;
    private int currentIndex;
    private int failedCount;


    //CONSTRUCTOR
    public QuestionIterator(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
        this.currentIndex = 0;
        this.failedCount = 0;
    }

    // METHODS
    @Override
    public boolean hasNext() {
        return currentIndex < questions.size();
    }

    @Override
    public Question next() {
        if (!hasNext()) {
            Logger.error("No more questions available.");
            return null;
        }
        return questions.get(currentIndex++);
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getQuestionsLeft() {
        return questions.size() - currentIndex;
    }

    public void addFailedQuestion(Question question) {
        if (question != null) {
            questions.add(question);
            failedCount++;
        }
    }

    public int getFailedCount() {
        return failedCount;
    }

}
