package com.signalpet.pets.rest.controller;

import com.signalpet.pets.rest.dto.CreateOrUpdatePetDTO;
import com.signalpet.pets.rest.dto.PetDTO;
import com.signalpet.pets.service.PetService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pet")
public class PetsController {
    private final PetService petService;

    public PetsController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetDTO> savePet(@RequestBody CreateOrUpdatePetDTO createOrUpdatePetDTO) {
        PetDTO petDTO = petService.savePet(createOrUpdatePetDTO);
        return ResponseEntity.ok(petDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetDTO> getPet(@PathVariable Integer id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletePet(@PathVariable Integer id) {
        petService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping(value = "/{id}")
//    public ResponseEntity updateImageWithMetadata(@PathVariable Integer id, @ModelAttribute CreateOrUpdatePetDTO createOrUpdatePetDTO) {
//        return ResponseEntity.ok(petService.update(id,createOrUpdatePetDTO));
//
//    }

    @PatchMapping("/pets/{petId}/xrayImages/{imageId}")
    public ResponseEntity assignImageToPet(@PathVariable Integer petId, @PathVariable Integer imageId) {
        petService.assignImageToPet(petId, imageId);
        return ResponseEntity.noContent().build();
    }


}

