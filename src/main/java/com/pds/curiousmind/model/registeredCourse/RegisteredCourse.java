package com.pds.curiousmind.model.registeredCourse;

import java.util.*;
import java.util.stream.Collectors;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;

public class RegisteredCourse {

    private final Course course;
    private final String strategyID;

    private final List<RegisteredContentBlock> registeredContentBlocks;


    //CONSTRUCTOR
    public RegisteredCourse(Course course, String strategyID) {
        this.course = course;
        this.strategyID = strategyID;
        this.registeredContentBlocks = course.getContentBlocks.stream()
            .map(RegisteredContentBlock::new)
            .collect(Collectors.toCollection(ArrayList::new));

    }

    //GETTERS
    public String getName() {
        return this.course.getName();
    }

    public String getDescription() {
        return this.course.getDescription();
    }

    public String getImageURL() {
        return this.course.getImageURL();
    }

    public List<RegisteredContentBlock> getRegisteredContentBlocks() {
        return Collections.unmodifiableList(registeredContentBlocks);
    }

    public double getProgress() {
        return this.registeredContentBlocks.isEmpty() ? 0.0 :
                ((double) getCompletedBlocksCount() * 100.0 / registeredContentBlocks.size());
    }

    public String getStrategyID() {
        return this.strategyID;
    }

    public boolean isCompleted() {
        return this.getCompletedBlocksCount() == this.registeredContentBlocks.size();
    }


    //METHODS
    public int getCompletedBlocksCount() {
        return (int) this.registeredContentBlocks.stream().filter(RegisteredContentBlock::isCompleted).count();
    }




}
