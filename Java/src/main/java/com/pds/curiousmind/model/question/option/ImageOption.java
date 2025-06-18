package com.pds.curiousmind.model.question.option;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Represents an option for a question that includes an associated image, such as for image-based multiple-choice questions.
 * <p>
 * This class extends {@link Option} by adding an image URL, allowing the option to display an image alongside its label.
 * It is mapped as an entity in the database and can be used in any question type that supports image-based options.
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     ImageOption option = new ImageOption("Apple", "https://example.com/apple.png");
 *     String label = option.getLabel();
 *     String imageUrl = option.getImageURL();
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
@Entity
public class ImageOption extends Option {
    /**
     * The URL of the image associated with this option.
     */
    @Column(name = "image_url")
    private String imageURL;

    /**
     * Constructs an ImageOption with the specified label and image URL.
     *
     * @param label the label or text for the option
     * @param imageURL the URL of the image associated with the option
     */
    public ImageOption(String label, String imageURL) {
        super(label);
        this.imageURL = imageURL;
    }

    /**
     * Default constructor for JPA and deserialization.
     */
    public ImageOption() {
        super();
    }

    /**
     * Returns the URL of the image associated with this option.
     *
     * @return the image URL
     */
    public String getImageURL() {
        return imageURL;
    }
}
