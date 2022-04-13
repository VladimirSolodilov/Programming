package com.example.myprojectsite.data.animal;

import com.example.myprojectsite.domain.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AnimalStorageMariaDb implements AnimalStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Animal> getAllAnimal(String pattern) {
        StringBuffer sqlQuery = new StringBuffer("select * from animal ");
        String sqlPattern = null;

        if (pattern != null) {
            sqlQuery.append(" where animal.animal_name like ? ");
            sqlPattern = "%" + pattern + "%";
            return jdbcTemplate.query(sqlQuery.toString(), new AnimalRowMapper(), sqlPattern);
        } else {
            return jdbcTemplate.query(sqlQuery.toString(), new AnimalRowMapper());
        }
    }

    @Override
    public int setAnimal(String animalName, String description) {
        String sqlQuery = "insert into animal values (?, ?)";
        return jdbcTemplate.update(sqlQuery, animalName, description);
    }

    @Override
    public int deleteAnimal(String animalName) {
        String sqlQuery = "delete from animal where animal_name = ?";
        return jdbcTemplate.update(sqlQuery, animalName);
    }
}
