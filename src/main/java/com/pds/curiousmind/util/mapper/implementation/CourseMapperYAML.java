package com.pds.curiousmind.util.mapper.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.Logger;
import com.pds.curiousmind.util.mapper.interfaces.ICourseMapper;
import java.io.File;
import java.io.IOException;

@JsonIgnoreProperties({"id"})
public class CourseMapperYAML implements ICourseMapper<File> {
    private final ObjectMapper objectMapper;

    public CourseMapperYAML() {
        YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.disable(com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.WRITE_DOC_START_MARKER);
        this.objectMapper = new ObjectMapper(yamlFactory);
        this.objectMapper.findAndRegisterModules();
    }

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

    @JsonIgnoreProperties({"id"})
    private static class IgnoreIdMixin {}
}
