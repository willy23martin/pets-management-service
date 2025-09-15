package com.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PetTest {

    @Test
    void test_ThrowsIllegalArgumentException_WhenPetAgeLessThanZero() {
        Pet pet = new Pet(1L, "Lucky", "Dog");
        Assertions.assertThrows(IllegalArgumentException.class , () -> {
            pet.setAge(-1);
        });
    }

    @Test
    void test_PetAgeShouldBeTheNewOne_WhenSetAgeToANewValue() {
        Pet pet = new Pet(1L, "Lucky", "Dog");
        pet.setAge(13);
        org.assertj.core.api.Assertions.assertThat(pet.getAge()).isEqualTo(13);
    }

}
