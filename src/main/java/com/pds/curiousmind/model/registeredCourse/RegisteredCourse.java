package com.pds.curiousmind.model.registeredCourse;

import java.util.*;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;

public class RegisteredCourse {

    private String name;
    private String description;
    private String imageUrl;
    private List<RegisteredContentBlock> contentBlocks;
    private final Strategy strategy;
    private int progress;
    private boolean isCompleted;

    public RegisteredCourse(Course course, Strategy strategy) {
        this.name = course.name;
        this.description = course.description;
        this.imageUrl = course.imageUrl;
        this.contentBlocks = course.contentBlocks;
        this.strategy = strategy;
        this.progress = 0; // Default progress
        this.isCompleted = false; // Default completion status
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<RegisteredContentBlock> getContentBlocks() {
        return contentBlocks;
    }

    public int getProgress() {
        return progress;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isCompleted() {
        return isCompleted;
    }


    //METHODS
    public RegisteredCourse create(Course course, Strategy strategy) {
        return new RegisteredCourse(course, strategy);
    }

    public int getCompletedBlocksCount() {
        return (int) contentBlocks.stream().filter(RegisteredContentBlock::isCompleted).count();
    }

    public void updateProgress() {
        this.progress = getCompletedBlocksCount()/contentBlocks.size() * 100;
    }

    public void markAsCompleted() {
        if (getCompletedBlocksCount() == contentBlocks.size()) {
            this.isCompleted = true;
            updateProgress();
        }
    }

}
