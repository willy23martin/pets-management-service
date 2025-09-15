package com.repository;

import com.entities.Pet;
import java.util.List;

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
        Pet lucky = pets.stream().findFirst().get();

        Assertions.assertThat(pets.size()).isEqualTo(1);
        Assertions.assertThat(lucky.getId()).isEqualTo(1L);
        Assertions.assertThat(lucky.getName()).isEqualTo("Lucky");
        Assertions.assertThat(lucky.getSpecies()).isEqualTo("Dog");
        Assertions.assertThat(lucky.getAge()).isGreaterThanOrEqualTo(0);
        Assertions.assertThat(lucky.getOtherName()).isEqualTo("Luckysinho");
    }

}
