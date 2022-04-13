package com.example.myprojectsite.domain.model.animal;

public class Animal {

    private int animalId;
    private String animalName;
    private String description;

    public Animal(int animalId, String animalName, String description) {
        this.animalId = animalId;
        this.animalName = animalName;
        this.description = description;
    }

    public Animal() {

    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
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
