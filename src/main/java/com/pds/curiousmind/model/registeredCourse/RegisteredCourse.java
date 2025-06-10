package com.pds.curiousmind.model.registeredCourse;

import java.util.*;
import java.util.stream.Collectors;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;

public class RegisteredCourse {

    private Course course;
    private final Strategy strategy;

    private final List<RegisteredContentBlock> registeredContentBlocks;


    //CONSTRUCTOR
    public RegisteredCourse(Course course, Strategy strategy) {
        this.course = course;
        this.strategy = strategy;

        this.registeredContentBlocks = course.contentBlocks.stream()
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

    public Strategy getStrategy() {
        return this.strategy;
    }

    public boolean isCompleted() {
        return this.getCompletedBlocksCount() == this.registeredContentBlocks.size();
    }


    //METHODS
    public int getCompletedBlocksCount() {
        return (int) this.registeredContentBlocks.stream().filter(RegisteredContentBlock::isCompleted).count();
    }




}
