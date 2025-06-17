package com.pds.curiousmind.model.course;

import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.strategy.StrategyType;
import jakarta.persistence.*;

import java.util.*;

@NamedEntityGraph(
    name = "Course.fullGraph",
    attributeNodes = {
        @NamedAttributeNode(value = "contentBlocks", subgraph = "contentBlockGraph")
    },
    subgraphs = {
        @NamedSubgraph(
            name = "contentBlockGraph",
            attributeNodes = {
                @NamedAttributeNode(value = "questions", subgraph = "questionGraph")
            }
        ),
        @NamedSubgraph(
            name = "questionGraph",
            attributeNodes = {
                @NamedAttributeNode("options")
            }
        )
    }
)
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image_url")
    private String imageURL;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "available_strategy", nullable = false)
    @CollectionTable(name = "course_strategies", joinColumns = @JoinColumn(name = "course_id"))
    private Set<StrategyType> availableStrategies;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "course_id", nullable = false)
    private List<ContentBlock> contentBlocks;

    // CONSTRUCTORS
    public Course() {
        this.availableStrategies = new HashSet<>();
        this.contentBlocks = new ArrayList<>();
    }

    public Course(String name, String description, String imageURL, List<StrategyType> strategies, List<ContentBlock> contentBlocks) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.availableStrategies = new HashSet<>(strategies);
        this.contentBlocks = new ArrayList<>(contentBlocks);
    }

    public Course(String name, String imageURL, List<StrategyType> strategies, List<ContentBlock> contentBlocks) {
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

    public Set<StrategyType> getAvailableStrategies() {
        return Collections.unmodifiableSet(availableStrategies);
    }


    // SETTERS
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

    public void setAvailableStrategies(Set<StrategyType> availableStrategies) {
        this.availableStrategies = availableStrategies;
    }
}