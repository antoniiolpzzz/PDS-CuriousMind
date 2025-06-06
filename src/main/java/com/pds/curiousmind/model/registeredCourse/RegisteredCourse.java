package com.pds.curiousmind.model.registeredCourse;

import java.util.*;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;

public class RegisteredCourse {

    private String name;
    private String description;
    private String imageURL;
    private List<RegisteredContentBlock> registeredContentBlocks = new ArrayList<>();

    private final Strategy strategy;
    private Course course;

    //CONSTRUCTOR
    public RegisteredCourse(Course course, Strategy strategy) {
        this.name = course.name;
        this.description = course.description;
        this.imageURL = course.imageURL;
        this.strategy = strategy;

        for (var contentBlock : course.contentBlocks) {
            this.registeredContentBlocks.add(new RegisteredContentBlock(contentBlock));
        }

    }

    //GETTERS
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<RegisteredContentBlock> getContentBlocks() {
        return registeredContentBlocks;
    }

    public int getProgress() {
        return getCompletedBlocksCount()/registeredContentBlocks.size() * 100;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isCompleted() {
        return getCompletedBlocksCount() == registeredContentBlocks.size();
    }


    //METHODS
    public int getCompletedBlocksCount() {
        return (int) registeredContentBlocks.stream().filter(RegisteredContentBlock::isCompleted).count();
    }




}
