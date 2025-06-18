package com.pds.curiousmind.application;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;
import com.pds.curiousmind.util.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.util.List;


/**
 * Main entry point for the CuriousMind application.
 * <p>
 * This class is responsible for initializing core services and launching the Swing UI.
 * It follows best practices for maintainability and clarity, keeping the entry point clean and professional.
 * </p>
 *
 * <p>
 * Application startup and shutdown are logged for traceability.
 * </p>
 *
 * @author antoniolopeztoboso
 */
public class CuriousMindApp {
    /**
     * Main method: application entry point.
     * Initializes core services and launches the Swing UI.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("CuriousMind Application starting...");
        try {
            initializeCoreServices();
            configureLookAndFeel();
            launchUI();
            System.out.println("CuriousMind Application started successfully.");
        } catch (Exception e) {
            System.err.println("Application failed to start: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initializes core singleton services/libraries required by the application.
     * Extend this method as the application grows.
     *
     * Note: Initialization of business logic should be handled by controllers or service classes.
     */
    private static void initializeCoreServices() {
        Controller controller = Controller.INSTANCE;
    }

    /**
     * Configures the Swing look and feel for the application.
     * Sets FlatMacLightLaf as the default theme.
     */
    private static void configureLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());

        } catch (Exception e) {
            Logger.error("Failed to initialize FlatMacLightLaf: " + e.getMessage());
        }
    }

    /**
     * Launches the main Swing UI on the Event Dispatch Thread.
     * Replace MainController::showMainWindow with your actual UI entry point.
     */
    private static void launchUI() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Launching main UI...");

        });
    }

    public static void printAllUserDetails(List<User> userlist) {
        for (User userObtained : userlist) {
            System.out.println("User Details:");
            System.out.println("User Name: " + userObtained.getFullName());
            System.out.println("User Email: " + userObtained.getEmail());
            System.out.println("User Username: " + userObtained.getUsername());
            for (RegisteredCourse rc : userObtained.getRegisteredCourses()) {
                System.out.println("-- Registered Course --");
                System.out.println("Course Name: " + rc.getName());
                System.out.println("Strategy ID: " + rc.getStrategy());
                System.out.println("Content Blocks:");
                for (RegisteredContentBlock block : rc.getRegisteredContentBlocks()) {
                    System.out.println("Content Block Name: " + block.getName());
                    System.out.println("Difficulty Level: " + block.getDifficulty());
                    for (Question q : block.getQuestions()) {
                        System.out.println("-- Questions --");
                        System.out.println("Question Type: " + q.getClass().getName());
                        System.out.println("Question Statement: " + q.getStatement());
                        System.out.println("Correct Answer: " + q.getCorrectAnswer());
                        System.out.println("-- Options --");
                        for (Option opt : q.getOptions()) {
                            System.out.println("Option: " + opt.getLabel());
                        }
                    }
                }
            }
            System.out.println("-----------------------------------------------");
        }
        System.out.println("\n");
    }
}