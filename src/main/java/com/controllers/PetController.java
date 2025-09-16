package com.controllers;

import com.entities.Pet;
import com.repositories.PetRepository;
import com.services.PetService;
import jakarta.validation.Valid;
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

    @GetMapping(value = "/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id){
        try {
            Pet pet = petService.getPetById(id);
            return ResponseEntity.ok(pet);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/pets")
    public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet) {
        return ResponseEntity.ok(petService.createPet(pet));
    }

    @PutMapping(value = "/pets/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody Pet pet) {
        try {
            Pet updatedPet = petService.updatePet(id, pet);
            return ResponseEntity.ok(updatedPet);
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
