package com.example.myprojectsite.domain.animal_service;

import com.example.myprojectsite.data.animal.AnimalStorage;
import com.example.myprojectsite.domain.model.animal.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceDomain implements AnimalService {

    @Autowired
    private AnimalStorage animalStorage;


    @Override
    public List<Animal> getAnimalList() {
        return animalStorage.getAllAnimal(null);
    }

    @Override
    public List<Animal> getAnimalByNameLike(String name) {
        return animalStorage.getAllAnimal(name);
    }

    @Override
    public int setAnimalList(String name, String description) {
        return animalStorage.setAnimal(name, description);
    }

    @Override
    public int deleteAnimalList(String animal) {
        return animalStorage.deleteAnimal(animal);
    }
}
