package com.signalpet.pets.rest.dto;


public record CreateOrUpdatePetDTO(
        String name,
        String specie,
        Integer age,
        String breed) {
}
