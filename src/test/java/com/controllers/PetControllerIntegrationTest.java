package com.controllers;

import com.entities.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

    @Test
    void test_RetrieveAllPetsWithTheirInformation_WhenGetAllPetsIsCalled() {
        ResponseEntity<Pet[]> petsResponse = testRestTemplate.getForEntity("http://localhost:" + port + "/pets", Pet[].class);

        Assertions.assertThat(petsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(petsResponse.getBody()[0]).isNotNull();

        Pet pet = petsResponse.getBody()[0];

        Assertions.assertThat(pet.getId()).isEqualTo(1L);
        Assertions.assertThat(pet.getName()).isEqualTo("Lucky");
        Assertions.assertThat(pet.getSpecies()).isEqualTo("Dog");
        Assertions.assertThat(pet.getAge()).isGreaterThanOrEqualTo(0);
        Assertions.assertThat(pet.getOtherName()).isEqualTo("Luckysinho");

    }
}
