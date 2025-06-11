package com.pds.curiousmind.model.course;

import com.pds.curiousmind.model.contentBlock.ContentBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {

    private String name;
    private String description;
    private String imageURL;

    private List<String> availableStrategies;
    private List<ContentBlock> contentBlocks;

    // CONSTRUCTORS
    public Course(String name, String description, String imageURL, List<String> strategies, List<ContentBlock> contentBlocks) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.availableStrategies = new ArrayList<>(strategies);
        this.contentBlocks = new ArrayList<>(contentBlocks);
    }

    public Course(String name, String imageURL, List<String> strategies, List<ContentBlock> contentBlocks) {
        this(name, null, imageURL, strategies, contentBlocks);
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<ContentBlock> getContentBlocks() {
        return Collections.unmodifiableList(contentBlocks);
    }

    public List<String> getAvailableStrategies() {
        return Collections.unmodifiableList(availableStrategies);
    }

    // METHODS
}