package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Shuffled implements Strategy {
    INSTANCE;

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.SHUFFLED;
    }

    @Override
    public List<Question> getProcessedQuestions(RegisteredContentBlock block){
        List<Question> questions = new ArrayList<Question>(block.getQuestions());
        Collections.shuffle(questions);
        return questions;
    }
}
