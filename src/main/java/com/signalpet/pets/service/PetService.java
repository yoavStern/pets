package com.signalpet.pets.service;

import com.signalpet.pets.model.Pet;
import com.signalpet.pets.model.XRay;
import com.signalpet.pets.repository.PetRepository;
import com.signalpet.pets.repository.XRayRepository;
import com.signalpet.pets.rest.dto.CreateOrUpdatePetDTO;
import com.signalpet.pets.rest.dto.PetDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final XRayRepository xRayRepository;

    public PetService(PetRepository petRepository, XRayRepository xRayRepository) {
        this.petRepository = petRepository;
        this.xRayRepository = xRayRepository;
    }

    public PetDTO savePet(CreateOrUpdatePetDTO createPetDTO) {
        Pet pet = createPet(createPetDTO);
        pet = petRepository.save(pet);
        return createPetDTO(pet);
    }

    public PetDTO getPetById(Integer id) {
        Pet pet = petRepository.findByIdWithXrays(id);

        return createPetDTO(pet);
    }

    public void deleteById(Integer id) {
        try {
            petRepository.findById(id);
        } catch (Exception ex) {
            // should handle it has 404 ...
            throw new RuntimeException("entity not found ");
        }
        petRepository.deleteById(id);
    }

    public PetDTO update(Integer id, CreateOrUpdatePetDTO createOrUpdatePetDTO) {
        Pet pet = createPet(createOrUpdatePetDTO);
        pet.setId(id);
        Pet dbPet = petRepository.save(pet);
        return createPetDTO(dbPet);
    }

    public void assignImageToPet(Integer petId, Integer imageId) {
        xRayRepository.updateXRayPet(imageId, petId);
    }

    private Pet createPet(CreateOrUpdatePetDTO createPetDTO) {
        Pet pet = new Pet();
        pet.setAge(createPetDTO.age());
        pet.setSpecie(createPetDTO.specie());
        pet.setBreed(createPetDTO.name());
        pet.setName(createPetDTO.name());
        return pet;
    }

    private PetDTO createPetDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setAge(pet.getAge());
        dto.setSpecie(pet.getSpecie());
        dto.setBreed(pet.getBreed());
        dto.setName(pet.getName());
        dto.setPhotos(pet.getPhotos().stream().map(XRay::getId).collect(Collectors.toList()));
        return dto;
    }
}
