package com.pds.curiousmind.util.mapper.service;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.mapper.service.CourseFormat;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperJSON;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperYAML;

import java.io.File;

public enum CourseMapperService {
    INSTANCE;

    private final CourseMapperJSON jsonMapper = new CourseMapperJSON();
    private final CourseMapperYAML yamlMapper = new CourseMapperYAML();

    public Course toEntity(File file) {
        String extension = extractFileExtension(file);
        return switch (CourseFormat.valueOf(extension)) {
            case CourseFormat.JSON -> jsonMapper.toEntity(file);
            case CourseFormat.YAML -> yamlMapper.toEntity(file);
            default -> throw new IllegalArgumentException("Unsupported format: " + file.getName());
        };
    }

    public File fromEntity(Course course, CourseFormat format) {
        return switch (format) {
            case JSON -> jsonMapper.fromEntity(course);
            case YAML -> yamlMapper.fromEntity(course);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }

    public String extractFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1 || lastIndexOfDot == fileName.length() - 1) {
            throw new IllegalArgumentException("File does not have a valid extension: " + fileName);
        }
        return fileName.substring(lastIndexOfDot + 1).toUpperCase();
    }
}

