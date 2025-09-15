package com.repositories;

import com.entities.Pet;
import java.util.List;

import com.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:petsdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    void test_PetRepositoryLoadsLucky_WhenItLoads() {
        List<Pet> pets = petRepository.findAll();
        Pet pet = pets.stream().findFirst().get();

        Assertions.assertThat(pets.size()).isEqualTo(1);
        TestUtils.assertPetIsLucky(pet);
    }


}
