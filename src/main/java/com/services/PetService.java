package com.services;

import com.entities.Pet;
import com.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPetById(Long id) throws Exception {
        Optional<Pet> currentPet = petRepository.findById(id);
        if(currentPet.isPresent()) {
            return currentPet.get();
        } else {
            throw new Exception("Pet with id: " + id + " was not found.");
        }
    }

    public Pet updatePet(Long id, Pet pet) throws Exception {
        Optional<Pet> currentPet = petRepository.findById(id);
        if(currentPet.isPresent()) {
            return petRepository.save(pet);
        } else {
            throw new Exception("Pet with id: " + id + " was not found.");
        }
    }

    public Pet deletePetById(Long id) throws Exception {
        Optional<Pet> currentPet = petRepository.findById(id);
        if(currentPet.isPresent()) {
            petRepository.deleteById(id);
            return currentPet.get();
        } else {
            throw new Exception("Pet with id: " + id + " was not found.");
        }
    }
}
