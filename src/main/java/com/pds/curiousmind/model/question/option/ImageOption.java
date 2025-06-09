package com.pds.curiousmind.model.question.option;

public class ImageOption extends Option {
    private String imageURL;

    public ImageOption(String label, String imageURL) {
        super(label);
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }
}

