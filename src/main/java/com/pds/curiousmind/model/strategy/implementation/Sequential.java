package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.strategy.Strategy;

import java.util.List;

public enum Sequential implements Strategy {
    INSTANCE;

    @Override
    public List<Question> getQuestionsBlock(ContentBlock block){
        return block.getQuestions();
    }
}
