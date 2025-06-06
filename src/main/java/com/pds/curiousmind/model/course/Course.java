package com.pds.curiousmind.model.course;

import com.pds.curiousmind.model.contentBlock.ContentBlock;

import java.util.List;

public class Course {

    private String name;
    private String description;
    private String imageURL;

    private List<ContentBlock> contentBlocks;


    public Course(String name, String description, String imageURL, List<ContentBlock> contentBlocks) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.contentBlocks = contentBlocks;
    }

    public Course(String name, String imageURL, List<ContentBlock> contentBlocks) {
        this(name, null, imageURL, contentBlocks);
    }

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
        return contentBlocks;
    }

    // METHODS


}
