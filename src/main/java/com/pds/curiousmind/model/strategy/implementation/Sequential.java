package com.pds.curiousmind.model.strategy.implementation;

import java.util.List;

public class Sequential {


    public List<Question> getQuestionsBlock(Bloque bloque){

        return bloque.getQuestions();
    }
}
