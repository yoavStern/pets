package com.signalpet.pets.rest.dto;

import com.signalpet.pets.model.XRay;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;


public class PetDTO{

    public PetDTO(Integer id, String name, String specie, Integer age, String breed, List<Integer> photos) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.age = age;
        this.breed = breed;
    }

    public PetDTO() {
    }

    Integer id;
        String name;
        String specie;
        Integer age;
        String breed;
        List<Integer> photos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Integer> photos) {
        this.photos = photos;
    }
}
