package com.pds.curiousmind.model.course;

public class Course {

    private String name;
    private String description;
    private String imageUrl;
    private List<Block> contentBlocks;

    public Course(String name, String description, String imageUrl, List<Block> contentBlocks) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.contentBlocks = contentBlocks;
    }

    public Course(String name, String imageUrl, List<Block> contentBlocks) {
        this(name, null, imageUrl, contentBlocks);
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

    public List<Block> getContentBlocks() {
        return contentBlocks;
    }

    //METHODS

    public Course create(String name, String description, String imageUrl, List<Block> contentBlocks) {
        return new Course(name, description, imageUrl, contentBlocks);
    }
}
