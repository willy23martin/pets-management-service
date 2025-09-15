package com.controllers;

import com.entities.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repositories.PetRepository;
import com.utils.TestUtils;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class PetControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    private static final String URL = "http://localhost:";

    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    void setUp() {
        petRepository.deleteAll();
        Pet lucky = new Pet("Lucky", "Dog");
        lucky.setAge(13);
        lucky.setOtherName("Luckysinho");
        petRepository.save(lucky);
    }

    @Test
    void test_RetrieveAllPetsWithTheirInformation_WhenGetAllPetsIsCalled() {
        ResponseEntity<Pet[]> petsResponse = testRestTemplate.getForEntity(URL + port + "/pets", Pet[].class);

        Assertions.assertThat(petsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(petsResponse.getBody()[0]).isNotNull();

        Pet pet = petsResponse.getBody()[0];

        TestUtils.assertPetIsLucky(pet);
    }

    @Test
    void test_CreateAPetWithItsInformation_WhenCreatePetIsCalled() throws Exception {
        Pet rocky = new Pet("Rocky", "Cat");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Pet> petResponse = testRestTemplate.postForEntity(
                URL + port + "/pets",
                new HttpEntity<>(objectMapper.writeValueAsString(rocky), headers),
                Pet.class
        );

        Assertions.assertThat(petResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(petResponse.getBody()).isNotNull();

        TestUtils.assertPetIsRocky(petResponse.getBody());
    }
}
