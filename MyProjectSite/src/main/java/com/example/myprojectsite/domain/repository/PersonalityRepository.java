package com.example.myprojectsite.domain.repository;

import com.example.myprojectsite.domain.model.user.Personality;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalityRepository extends CrudRepository<Personality, Long> {

}