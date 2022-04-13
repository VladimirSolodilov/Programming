package com.example.myprojectsite.domain.repository;

import com.example.myprojectsite.domain.model.user.Salt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaltRepository extends CrudRepository<Salt, Long> {

}