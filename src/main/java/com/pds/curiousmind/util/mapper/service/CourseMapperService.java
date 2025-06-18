package com.pds.curiousmind.util.mapper.service;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.util.Logger;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperJSON;
import com.pds.curiousmind.util.mapper.implementation.CourseMapperYAML;

import java.io.File;

/**
 * Service for mapping {@link Course} entities to and from file representations in different formats.
 * <p>
 * This enum singleton provides methods to convert between {@link Course} objects and their file representations
 * (such as JSON and YAML) using the appropriate mapper implementation. It supports both serialization
 * (from entity to file) and deserialization (from file to entity) based on the file extension or specified format.
 * </p>
 *
 * <p>
 * Supported formats are defined by the {@link MapperFormat} enum. If an unsupported format or file extension is
 * encountered, an error is logged and {@code null} is returned.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     Course course = CourseMapperService.INSTANCE.toEntity(new File("course.json"));
 *     File file = CourseMapperService.INSTANCE.fromEntity(course, MapperFormat.JSON);
 * </pre>
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.model.course.Course
 * @see com.pds.curiousmind.util.mapper.implementation.CourseMapperJSON
 * @see com.pds.curiousmind.util.mapper.implementation.CourseMapperYAML
 * @see MapperFormat
 */
public enum CourseMapperService {
    /**
     * Singleton instance of the course mapper service.
     */
    INSTANCE;

    /**
     * Mapper for JSON format.
     */
    private final CourseMapperJSON jsonMapper = new CourseMapperJSON();
    /**
     * Mapper for YAML format.
     */
    private final CourseMapperYAML yamlMapper = new CourseMapperYAML();

    /**
     * Converts a file to a {@link Course} entity based on its extension (JSON or YAML).
     *
     * @param file the file to convert
     * @return the deserialized {@link Course} entity, or {@code null} if the format is unsupported
     */
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

    /**
     * Converts a {@link Course} entity to a file in the specified format.
     *
     * @param course the course entity to serialize
     * @param format the target file format
     * @return the file containing the serialized course, or {@code null} if the format is unsupported
     */
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

    /**
     * Extracts the file extension from a file and returns it in uppercase.
     *
     * @param file the file whose extension is to be extracted
     * @return the file extension in uppercase, or {@code null} if not found or invalid
     */
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
