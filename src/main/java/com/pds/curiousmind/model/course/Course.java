package com.pds.curiousmind.model.course;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageURL;

    @ElementCollection
    private List<String> availableStrategies;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentBlock> contentBlocks;

    // CONSTRUCTORS
    public Course() {
        this.availableStrategies = new ArrayList<>();
        this.contentBlocks = new ArrayList<>();
    }

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
    public Long getId() {
        return id;
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
        return Collections.unmodifiableList(contentBlocks);
    }

    public List<String> getAvailableStrategies() {
        return Collections.unmodifiableList(availableStrategies);
    }

    // SETTERS (for JPA and updates)
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setContentBlocks(List<ContentBlock> contentBlocks) {
        this.contentBlocks = contentBlocks;
    }

    public void setAvailableStrategies(List<String> availableStrategies) {
        this.availableStrategies = availableStrategies;
    }
}