package com.pds.curiousmind.model.registeredCourse;

import java.util.*;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.Strategy;

public class RegisteredCourse {

    private String name;
    private String description;
    private String imageUrl;
    private List<RegisteredContentBlock> registeredContentBlocks = new ArrayList<>();
    private final Strategy strategy;

    public RegisteredCourse(Course course, Strategy strategy) {
        this.name = course.name;
        this.description = course.description;
        this.imageUrl = course.imageUrl;
        this.strategy = strategy;

        for (var contentBlock : course.contentBlocks) {
            this.registeredContentBlocks.add(new RegisteredContentBlock(contentBlock));
        }

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
        return getCompletedBlocksCount()/contentBlocks.size() * 100;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isCompleted() {
        return getCompletedBlocksCount() == contentBlocks.size();
    }


    //METHODS
    public RegisteredCourse create(Course course, Strategy strategy) {
        return new RegisteredCourse(course, strategy);
    }

    public int getCompletedBlocksCount() {
        return (int) contentBlocks.stream().filter(RegisteredContentBlock::isCompleted).count();
    }




}
