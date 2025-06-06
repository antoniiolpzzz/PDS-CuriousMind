package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.strategy.Strategy;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Shuffled implements Strategy {

    public List<Question> getQuestionsBlock(Bloque bloque){

        List<Question> questions = bloque.getQuestions();
        Collections.shuffle(questions);
        return questions;

    }
}
