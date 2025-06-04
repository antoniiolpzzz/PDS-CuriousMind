package com.pds.curiousmind.model.strategy.implementation;

import java.util.List;

public class Shuffled {

    public List<Question> getQuestionsBlock(Bloque bloque){

        List<Question> questions;
        questions = bloque.getQuestions();

        java.util.Collections.shuffle(questions);
        return questions;

    }
}
