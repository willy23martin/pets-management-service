package com.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @NotBlank
    @Getter
    @Setter
    private String species;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private String otherName;

    protected Pet(){} // JPA

    public Pet(@NotNull String name, @NotNull String species) {
        this.name = name;
        this.species = species;
    }

    public void setAge(int age) throws IllegalArgumentException {
        if(age < 0) {
            throw new IllegalArgumentException("Age should be greater than 0");
        } else {
            this.age = age;
        }
    }

}
