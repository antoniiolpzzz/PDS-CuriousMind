package com.pds.curiousmind.util.mapper.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.interfaces.ICourseMapper;
import java.io.File;
import java.io.IOException;

public class CourseMapperYAML implements ICourseMapper<File> {
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public Course toEntity(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("Input YAML file must not be null and must exist");
        }
        try {
            return objectMapper.readValue(file, Course.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse YAML file to Course", e);
        }
    }

    @Override
    public File fromEntity(Course entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Course entity must not be null");
        }
        try {
            File tempFile = File.createTempFile("course", ".yaml");
            objectMapper.writeValue(tempFile, entity);
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert Course to YAML file", e);
        }
    }
}
