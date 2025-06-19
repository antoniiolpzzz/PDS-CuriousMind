package com.pds.curiousmind.controller;

import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.library.implementation.CourseLibrary;
import com.pds.curiousmind.model.library.implementation.UserLibrary;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.util.mapper.service.MapperFormat;
import com.pds.curiousmind.util.mapper.service.CourseMapperService;
import com.pds.curiousmind.util.AppConfig;
import com.pds.curiousmind.util.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Singleton controller responsible for managing course-related operations.
 * <p>
 * This controller provides methods for retrieving, registering, serializing, and importing courses.
 * It acts as a facade for all course management logic, including user course registration and
 * initialization of sample data on first application run.
 * </p>
 *
 * <p>Usage example:</p>
 * <pre>
 *     List&lt;Course&gt; courses = CourseController.INSTANCE.getAllCourses(user);
 *     CourseController.INSTANCE.createRegisteredCourse(user, course, StrategyType.SEQUENTIAL);
 * </pre>
 *
 * @author Antonio López
 * @since 1.0
 */
public enum CourseController {
    INSTANCE;
    private final CourseLibrary courseLibrary = CourseLibrary.INSTANCE;
    private final UserLibrary userLibrary = UserLibrary.INSTANCE;
    private final CourseMapperService courseMapperService = CourseMapperService.INSTANCE;
    private final MapperFormat mapperFormat = MapperFormat.JSON; // Or get from config

    /**
     * Returns the list of registered courses for a given user.
     *
     * @param user the {@link User} whose registered courses are to be retrieved
     * @return a list of {@link RegisteredCourse} objects, or an empty list if none
     */
    public List<RegisteredCourse> getRegisteredCourses(User user) {
        return user.getRegisteredCourses();
    }

    /**
     * Returns all available courses that the user can register for.
     *
     * @param user the {@link User} for whom to retrieve available courses
     * @return a list of {@link Course} objects
     */
    public List<Course> getAllCourses(User user) {
        List<Course> allCourses = new ArrayList<>(courseLibrary.getAll());
        getRegisteredCourses(user).forEach(registeredCourse -> {
            allCourses.removeIf(
                course -> course.getId().equals(registeredCourse.getCourse().getId())
                        && !registeredCourse.isCompleted());
        });
        return allCourses;
    }

    /**
     * Serializes a course to a JSON file.
     *
     * @param course the {@link Course} to serialize
     * @return the generated JSON {@link File}
     */
    public File getJsonFromCourse(Course course) {
        return courseMapperService.fromEntity(course, mapperFormat);
    }

    /**
     * Creates a new course from a JSON file and adds it to the course library.
     *
     * @param jsonFile the JSON {@link File} representing the course
     * @return the created {@link Course}
     */
    public Course createCourseFromJson(File jsonFile) {
        Course mappedCourse = courseMapperService.toEntity(jsonFile);
        return courseLibrary.add(mappedCourse);
    }

    /**
     * Registers a user in a course with the specified strategy.
     *
     * @param user the {@link User} to register
     * @param course the {@link Course} to register in
     * @param strategy the {@link StrategyType} to use
     */
    public void createRegisteredCourse(User user, Course course, StrategyType strategy) {
        courseLibrary.update(course);
        user.registerInCourse(course, strategy);
        userLibrary.update(user);
    }

    /**
     * Initializes sample courses on the first application run by importing them from resources.
     *
     * <p>This method checks if the course library is empty and, if so, loads sample courses
     * from the configured resource directory. It logs errors if the directory or files are not found.</p>
     */
    public void initializeSamplesOnFirstOpen() {
        try {
            String sampleCoursesPath = AppConfig.get("sample.courses.path");
            String sampleExtension = mapperFormat.name().toLowerCase();

            if (courseLibrary.getAll().isEmpty()) {
                var classLoader = getClass().getClassLoader();
                var resourceUrl = classLoader.getResource(sampleCoursesPath);
                if (resourceUrl == null) {
                    Logger.error("Sample courses directory not found in resources.");
                    return;
                }
                if ("file".equals(resourceUrl.getProtocol())) {
                    // Running from filesystem (e.g., IDE)
                    Path sampleCoursesDir = Paths.get(resourceUrl.toURI());
                    try (Stream<Path> paths = Files.list(sampleCoursesDir)) {
                        for (Path path : paths.filter(p -> p.toString().endsWith("." + sampleExtension)).toList()) {
                            courseLibrary.add(courseMapperService.toEntity(path.toFile()));
                        }
                    }
                } else if ("jar".equals(resourceUrl.getProtocol())) {
                    // Running from JAR
                    String jarPath = resourceUrl.getPath().substring(5, resourceUrl.getPath().indexOf("!"));
                    try (java.util.jar.JarFile jar = new java.util.jar.JarFile(jarPath)) {
                        java.util.Enumeration<java.util.jar.JarEntry> entries = jar.entries();
                        while (entries.hasMoreElements()) {
                            java.util.jar.JarEntry entry = entries.nextElement();
                            String entryName = entry.getName();
                            if (entryName.startsWith(sampleCoursesPath + "/") && entryName.endsWith("." + sampleExtension)) {
                                try (var is = classLoader.getResourceAsStream(entryName)) {
                                    if (is != null) {
                                        // Create a temp file to use the existing toEntity(File) method
                                        File tempFile = File.createTempFile("samplecourse", "." + sampleExtension);
                                        tempFile.deleteOnExit();
                                        try (var os = new java.io.FileOutputStream(tempFile)) {
                                            is.transferTo(os);
                                        }
                                        courseLibrary.add(courseMapperService.toEntity(tempFile));
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Logger.error("Unsupported resource protocol: " + resourceUrl.getProtocol());
                }
            }
        } catch (Exception e) {
            Logger.error("Failed to import courses from resources: " + e.getMessage());
        }
    }
}
