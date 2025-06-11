package com.pds.curiousmind.model.strategy;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.question.Question;

import java.util.List;

public interface Strategy {

    List<Question> getQuestionsBlock(ContentBlock block);

}
