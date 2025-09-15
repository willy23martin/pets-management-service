package com.utils;

import com.entities.Pet;
import org.assertj.core.api.Assertions;

public class TestUtils {

    public static void assertPetIsLucky(Pet pet) {
        Assertions.assertThat(pet.getName()).isEqualTo("Lucky");
        Assertions.assertThat(pet.getSpecies()).isEqualTo("Dog");
        Assertions.assertThat(pet.getAge()).isGreaterThanOrEqualTo(0);
        Assertions.assertThat(pet.getOtherName()).isEqualTo("Luckysinho");
    }

    public static void assertPetIsRocky(Pet pet) {
        Assertions.assertThat(pet.getName()).isEqualTo("Rocky");
        Assertions.assertThat(pet.getSpecies()).isEqualTo("Cat");
    }

}
