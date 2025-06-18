package com.pds.curiousmind.util;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Utility class for image-related operations.
 * Provides methods to download images from URLs and save them as temporary files.
 */
public final class ImageUtils {
    private ImageUtils() { /* Utility class: prevent instantiation */ }
    /**
     * Downloads an image from the given URL and saves it as a temporary file with the given extension.
     * @param urlString The URL of the image.
     * @param extension The file extension (e.g., ".png", ".jpg").
     * @return The absolute path to the downloaded image file, or null if failed.
     */
    public static String downloadImage(String urlString, String extension) {
        try (InputStream in = URI.create(urlString).toURL().openStream()) {
            File tempFile = File.createTempFile("downloaded_image", extension);
            Files.copy(in, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return tempFile.getAbsolutePath();
        } catch (Exception e) {
            Logger.error("Failed to download image from URL: " + urlString + " - " + e.getMessage());
            return null;
        }
    }
}
