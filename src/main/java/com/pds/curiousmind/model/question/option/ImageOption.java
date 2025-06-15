package com.pds.curiousmind.model.question.option;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class ImageOption extends Option {
    @Column(name = "image_url")
    private String imageURL;

    public ImageOption(String label, String imageURL) {
        super(label);
        this.imageURL = imageURL;
    }

    public ImageOption() {
        super();
    }

    //GETTERS
    public String getImageURL() {
        return imageURL;
    }
}
