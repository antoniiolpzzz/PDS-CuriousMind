package com.pds.curiousmind.util.mapper.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.Logger;
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

    @JsonIgnoreProperties({"id"})
    private static class IgnoreIdMixin {}
}
