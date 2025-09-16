package com.controllers;

import com.entities.Pet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repositories.PetRepository;
import com.utils.TestUtils;
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

    private Pet savedPet;

    @BeforeEach
    void setUp() {
        petRepository.deleteAll();
        Pet lucky = new Pet("Lucky", "Dog");
        lucky.setAge(13);
        lucky.setOtherName("Luckysinho");
        savedPet = petRepository.save(lucky);
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

    @Test
    void test_UpdatePetInformation_WhenUpdatePetIsCalled() throws JsonProcessingException {
        Pet updatedLucky = new Pet("Lucky updated", "Dog");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Pet> petResponse = testRestTemplate.exchange(
                URL + port + "/pets/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(objectMapper.writeValueAsString(updatedLucky), headers),
                Pet.class,
                savedPet.getId()
        );

        Assertions.assertThat(petResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(petResponse.getBody()).isNotNull();

        TestUtils.assertPetIsUpdatedLucky(petResponse.getBody());
    }

    @Test
    void test_NotFound_WhenUpdatePetIsCalledWithAnInvalidPetId() throws JsonProcessingException {
        Pet updatedLucky = new Pet("Lucky updated", "Dog");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Pet> petResponse = testRestTemplate.exchange(
                URL + port + "/pets/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(objectMapper.writeValueAsString(updatedLucky), headers),
                Pet.class,
                2000L
        );

        Assertions.assertThat(petResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void test_GetPetInformation_WhenGetPetByIdIsCalled() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Pet> petResponse = testRestTemplate.getForEntity(
                URL + port + "/pets/{id}",
                Pet.class,
                savedPet.getId()
        );

        Assertions.assertThat(petResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(petResponse.getBody()).isNotNull();

        TestUtils.assertPetIsLucky(petResponse.getBody());
    }

    @Test
    void test_NotFound_WhenGetPetByIdIsCalledWithAnInvalidPetId() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Pet> petResponse = testRestTemplate.getForEntity(
                URL + port + "/pets/{id}",
                Pet.class,
                2000L
        );

        Assertions.assertThat(petResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
