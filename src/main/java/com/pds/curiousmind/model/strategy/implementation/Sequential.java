package com.pds.curiousmind.model.strategy.implementation;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;

import java.util.List;

public enum Sequential implements Strategy {
    INSTANCE;

    @Override
    public StrategyType getStrategyType() {
        return StrategyType.SEQUENTIAL;
    }

    @Override
    public List<Question> getProcessedQuestions(RegisteredContentBlock block){
        return block.getQuestions();
    }
}
