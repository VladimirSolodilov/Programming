package com.example.myprojectsite.web.form.animal;

public class AnimalRegistationForm {
    private String animalName;
    private String description;

    public AnimalRegistationForm(String animalName, String description) {
        this.animalName = animalName;
        this.description = description;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
