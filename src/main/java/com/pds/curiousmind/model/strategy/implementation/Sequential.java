package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.strategy.Strategy;

import java.util.List;

public class Sequential implements Strategy {


    public List<Question> getQuestionsBlock(Bloque bloque){

        return bloque.getQuestions();
    }
}
