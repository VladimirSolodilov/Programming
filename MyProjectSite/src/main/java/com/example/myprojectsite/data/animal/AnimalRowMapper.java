package com.example.myprojectsite.data.animal;

import com.example.myprojectsite.domain.model.animal.Animal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class AnimalRowMapper implements RowMapper<Animal> {
    @Override
    public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
        Animal animalModel = new Animal();

        animalModel.setAnimalName(rs.getString("animal_name"));
        animalModel.setDescription(rs.getString("animal_description"));

        return animalModel;
    }
}
