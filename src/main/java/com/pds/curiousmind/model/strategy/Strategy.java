package com.pds.curiousmind.model.strategy;

import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;

import java.util.List;

public interface Strategy {

    StrategyType getStrategyType();
    List<Question> getProcessedQuestions(RegisteredContentBlock block);

}
