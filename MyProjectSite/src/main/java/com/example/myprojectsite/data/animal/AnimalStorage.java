package com.example.myprojectsite.data.animal;

import com.example.myprojectsite.domain.model.Animal;

import java.util.List;

public interface AnimalStorage {
    List<Animal> getAllAnimal(String pattern);
    int setAnimal(String animalName, String description);
    int deleteAnimal(String animalName);

}
