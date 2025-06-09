package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.strategy.Strategy;
import java.util.Collections;
import java.util.List;

public class Shuffled implements Strategy {

    public List<Question> getQuestionsBlock(ContentBlock block){

        List<Question> questions = new List<Question>(block.getQuestions());
        Collections.shuffle(questions);
        return questions;
    }
}
