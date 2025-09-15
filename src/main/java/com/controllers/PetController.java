package com.controllers;

import com.entities.Pet;
import com.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping(value = "/pets")
    public ResponseEntity<List<Pet>> getAllPets(){
        return ResponseEntity.ok(petService.getAllPets());
    }

    @PostMapping(value = "/pets")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(petService.createPet(pet));
    }
}
