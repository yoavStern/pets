package com.signalpet.pets.repository;

import com.signalpet.pets.model.Pet;
import com.signalpet.pets.model.XRay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface XRayRepository extends JpaRepository<XRay, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE xray SET pet_id = :petId WHERE id = :id", nativeQuery = true)
    void updateXRayPet(Integer id, Integer petId);

}