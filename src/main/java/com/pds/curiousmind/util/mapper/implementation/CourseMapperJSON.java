package com.pds.curiousmind.util.mapper.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.interfaces.ICourseMapper;
import java.io.File;
import java.io.IOException;

@JsonIgnoreProperties({"id"})
public class CourseMapperJSON implements ICourseMapper<File> {
    private final ObjectMapper objectMapper;

    public CourseMapperJSON() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.addMixIn(Object.class, IgnoreIdMixin.class);
    }

    @Override
    public Course toEntity(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("Input JSON file must not be null and must exist");
        }
        try {
            return objectMapper.readValue(file, Course.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON file to Course", e);
        }
    }

    @Override
    public File fromEntity(Course entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Course entity must not be null");
        }
        try {
            //objectMapper.addMixIn(Object.class, IgnoreIdMixin.class);
            File tempFile = File.createTempFile("course", ".json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(tempFile, entity);
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert Course to JSON file", e);
        }
    }

    @JsonIgnoreProperties({"id"})
    private static class IgnoreIdMixin {}
}

