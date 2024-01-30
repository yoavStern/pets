package com.signalpet.pets.repository;

import com.signalpet.pets.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface PetRepository extends JpaRepository<Pet, Integer> {


    @Query("SELECT p FROM Pet p LEFT JOIN FETCH p.photos WHERE p.id = :petId")
    @Transactional
    Pet findByIdWithXrays(Integer petId);
}