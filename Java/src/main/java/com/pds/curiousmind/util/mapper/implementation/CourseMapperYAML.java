package com.pds.curiousmind.util.mapper.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.Logger;
import com.pds.curiousmind.util.mapper.interfaces.ICourseMapper;
import java.io.File;
import java.io.IOException;

/**
 * YAML mapper implementation for converting between {@link Course} entities and YAML files.
 * <p>
 * This class implements the {@link ICourseMapper} interface, providing methods to serialize a {@link Course}
 * entity to a YAML file and deserialize a YAML file to a {@link Course} entity. It uses Jackson's
 * {@link ObjectMapper} with a {@link YAMLFactory} for YAML processing.
 * </p>
 *
 * <p>
 * The mapper ignores the {@code id} property during serialization and deserialization to avoid
 * persisting or reading the entity's identifier from YAML files. If the input file or entity is invalid,
 * errors are logged and {@code null} is returned.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     CourseMapperYAML mapper = new CourseMapperYAML();
 *     Course course = mapper.toEntity(new File("course.yaml"));
 *     File yamlFile = mapper.fromEntity(course);
 * </pre>
 * </p>
 *
 * @author Antonio López Toboso
 * @see com.pds.curiousmind.model.course.Course
 * @see com.pds.curiousmind.util.mapper.interfaces.ICourseMapper
 */
@JsonIgnoreProperties({"id"})
public class CourseMapperYAML implements ICourseMapper<File> {
    /**
     * Jackson ObjectMapper configured for YAML processing.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new CourseMapperYAML with a configured YAML ObjectMapper.
     * Disables the document start marker and registers all modules.
     */
    public CourseMapperYAML() {
        YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.disable(com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        this.objectMapper = new ObjectMapper(yamlFactory);
        this.objectMapper.findAndRegisterModules();
    }

    /**
     * Deserializes a YAML file to a {@link Course} entity.
     *
     * @param file the YAML file to read
     * @return the deserialized {@link Course} entity, or {@code null} if the file is invalid or parsing fails
     */
    @Override
    public Course toEntity(File file) {
        if (file == null || !file.exists()) {
            Logger.error("Input YAML file must not be null and must exist");
            return null;
        }
        try {
            return objectMapper.readValue(file, Course.class);
        } catch (IOException e) {
            Logger.error("Failed to parse YAML file to Course: " + e.getMessage());
            return null;
        }
    }

    /**
     * Serializes a {@link Course} entity to a temporary YAML file.
     *
     * @param entity the Course entity to serialize
     * @return the YAML file containing the serialized course, or {@code null} if the entity is invalid or writing fails
     */
    @Override
    public File fromEntity(Course entity) {
        if (entity == null) {
            Logger.error("Course entity must not be null");
            return null;
        }
        try {
            objectMapper.addMixIn(Object.class, IgnoreIdMixin.class);
            File tempFile = File.createTempFile("course", ".yaml");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(tempFile, entity);
            return tempFile;
        } catch (IOException e) {
            Logger.error("Failed to convert Course to YAML file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Mixin class to ignore the {@code id} property during YAML serialization.
     */
    @JsonIgnoreProperties({"id"})
    private static class IgnoreIdMixin {}
}
