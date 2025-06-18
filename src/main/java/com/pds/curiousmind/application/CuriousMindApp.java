package com.pds.curiousmind.application;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.pds.curiousmind.controller.Controller;
import com.pds.curiousmind.util.Logger;
import com.pds.curiousmind.view.authentication.login.LoginWindow;

import javax.swing.UIManager;


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
        Logger.info("CuriousMind Application starting...");
        try {
            initializeCoreServices();
            configureLookAndFeel();
            launchUI();
            Logger.info("CuriousMind Application started successfully.");
        } catch (Exception e) {
            Logger.error("Application failed to start: " + e.getMessage());

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
        controller.initializeSamplesOnFirstOpen();
    }

    /**
     * Configures the Swing look and feel for the application.
     * Sets FlatMacLightLaf as the default theme.
     */
    private static void configureLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());

        } catch (Exception e) {
            Logger.error("Failed to initialize FlatMacLightLaf: " + e.getMessage());
        }
    }

    /**
     * Launches the main Swing UI on the Event Dispatch Thread.
     * Replace MainController::showMainWindow with your actual UI entry point.
     */
    private static void launchUI() {
        LoginWindow.showLogin();
        Logger.info("Launching main UI...");
    }

}