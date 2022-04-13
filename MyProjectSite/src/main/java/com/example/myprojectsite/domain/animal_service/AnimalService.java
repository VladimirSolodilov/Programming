package com.example.myprojectsite.domain.animal_service;

import com.example.myprojectsite.domain.model.Animal;

import java.util.List;

public interface AnimalService {

    List<Animal> getAnimalList();
    List<Animal> getAnimalByNameLike(String name);
    int setAnimalList(String name, String description);
    int deleteAnimalList(String animal);
}
