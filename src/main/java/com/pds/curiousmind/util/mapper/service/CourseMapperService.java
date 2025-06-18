package com.pds.curiousmind.util.mapper.service;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.Logger;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperJSON;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperYAML;

import java.io.File;

public enum CourseMapperService {
    INSTANCE;

    private final CourseMapperJSON jsonMapper = new CourseMapperJSON();
    private final CourseMapperYAML yamlMapper = new CourseMapperYAML();

    public Course toEntity(File file) {
        String extension = extractFileExtension(file);
        return switch (MapperFormat.valueOf(extension)) {
            case MapperFormat.JSON -> jsonMapper.toEntity(file);
            case MapperFormat.YAML -> yamlMapper.toEntity(file);
            default -> {
                Logger.error("Unsupported format or extension: " + file.getName());
                yield null;
            }
        };
    }

    public File fromEntity(Course course, MapperFormat format) {
        return switch (format) {
            case JSON -> jsonMapper.fromEntity(course);
            case YAML -> yamlMapper.fromEntity(course);
            default -> {
                Logger.error("Unsupported format or extension: " + format);
                yield null;
            }
        };
    }

    public String extractFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1 || lastIndexOfDot == fileName.length() - 1) {
            Logger.error("File does not have a valid extension: " + fileName);
            return null;
        }
        return fileName.substring(lastIndexOfDot + 1).toUpperCase();
    }
}
