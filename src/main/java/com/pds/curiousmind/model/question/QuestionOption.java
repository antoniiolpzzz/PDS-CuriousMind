package com.pds.curiousmind.model.question;

public class QuestionOption {

    String label;
    String imageURL;

    public QuestionOption(String label, String imageURL) {
        this.label = label;
        this.imageURL = imageURL;
    }

    public QuestionOption(String label) {
        this.label = label;
        this.imageURL = null;
    }

    public String getLabel() {
        return label;
    }

    public String getImageURL() {
        return imageURL;
    }

}
