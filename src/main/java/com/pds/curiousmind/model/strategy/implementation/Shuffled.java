package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.strategy.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Shuffled implements Strategy {
    INSTANCE;

    @Override
    public List<Question> getQuestionsBlock(ContentBlock block){
        List<Question> questions = new ArrayList<Question>(block.getQuestions());
        Collections.shuffle(questions);
        return questions;
    }
}
