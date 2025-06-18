package com.pds.curiousmind.util.mapper.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.Logger;
import com.pds.curiousmind.util.mapper.interfaces.ICourseMapper;
import java.io.File;
import java.io.IOException;

/**
 * JSON mapper implementation for converting between {@link Course} entities and JSON files.
 * <p>
 * This class implements the {@link ICourseMapper} interface, providing methods to serialize a {@link Course}
 * entity to a JSON file and deserialize a JSON file to a {@link Course} entity. It uses Jackson's
 * {@link ObjectMapper} for JSON processing and ignores the {@code id} property during serialization
 * and deserialization to avoid persisting or reading the entity's identifier from JSON files.
 * </p>
 *
 * <p>
 * If the input file or entity is invalid, errors are logged and {@code null} is returned.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     CourseMapperJSON mapper = new CourseMapperJSON();
 *     Course course = mapper.toEntity(new File("course.json"));
 *     File jsonFile = mapper.fromEntity(course);
 * </pre>
 * </p>
 *
 * @author Antonio López Toboso
 * @see com.pds.curiousmind.model.course.Course
 * @see com.pds.curiousmind.util.mapper.interfaces.ICourseMapper
 */
@JsonIgnoreProperties({"id"})
public class CourseMapperJSON implements ICourseMapper<File> {
    /**
     * Jackson ObjectMapper configured for JSON processing and ignoring the id property.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new CourseMapperJSON with a configured ObjectMapper.
     * Adds a mixin to ignore the id property during serialization.
     */
    public CourseMapperJSON() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.addMixIn(Object.class, IgnoreIdMixin.class);
    }

    /**
     * Deserializes a JSON file to a {@link Course} entity.
     *
     * @param file the JSON file to read
     * @return the deserialized {@link Course} entity, or {@code null} if the file is invalid or parsing fails
     */
    @Override
    public Course toEntity(File file) {
        if (file == null || !file.exists()) {
            Logger.error("Input JSON file must not be null and must exist");
            return null;
        }
        try {
            return objectMapper.readValue(file, Course.class);
        } catch (IOException e) {
            Logger.error("Failed to parse JSON file to Course: " + e.getMessage());
            return null;
        }
    }

    /**
     * Serializes a {@link Course} entity to a temporary JSON file.
     *
     * @param entity the Course entity to serialize
     * @return the JSON file containing the serialized course, or {@code null} if the entity is invalid or writing fails
     */
    @Override
    public File fromEntity(Course entity) {
        if (entity == null) {
            Logger.error("Course entity must not be null");
            return null;
        }
        try {
            File tempFile = File.createTempFile("course", ".json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(tempFile, entity);
            return tempFile;
        } catch (IOException e) {
            Logger.error("Failed to convert Course to JSON file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Mixin class to ignore the {@code id} property during JSON serialization.
     */
    @JsonIgnoreProperties({"id"})
    private static class IgnoreIdMixin {}
}
