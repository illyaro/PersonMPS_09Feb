package com.illyaro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void NullNameOnCreationResultsException() {
        String name = null;
        int age = 20;
        String gender = "Male";
        assertThrows(PersonException.class, () -> new Person(name, age, gender),
                "Persons name cannot be null");
    }

    @Test
    void EmptyNameResultsException() {
        String name = "";
        int age = 20;
        String gender = "Female";
        assertThrows(PersonException.class, () -> new Person(name, age, gender),
                "Persons name cannot be empty");
    }

    @Test
    void NegativeAgeResultsException() {
        String name = "Laila";
        int age = -2;
        String gender = "Female";
        assertThrows(PersonException.class, () -> new Person(name, age, gender),
                "Persons age cannot be negative");
    }

    @Test
    void MaleGenderIsAccepted() {
        String name = "Robin";
        int age = 12;
        String genderMale = "Male";
        Person person = new Person(name, age, genderMale);
        assertEquals(name, person.name());
        assertEquals(age, person.age());
        assertEquals(genderMale, person.gender());
    }

    @Test
    void FemaleGenderIsAccepted() {
        String name = "Robin";
        int age = 12;
        String genderFemale = "Female";
        Person person = new Person(name, age, genderFemale);
        assertEquals(name, person.name());
        assertEquals(age, person.age());
        assertEquals(genderFemale, person.gender());
    }

    @Test
    void NotMaleOrFemaleGenderResultsException() {
        String name = "Robin";
        int age = 12;
        String otherGender = "Other";
        assertThrows(PersonException.class, () -> new Person(name, age, otherGender),
                "Inserted gender was: '" + otherGender + "'. Only 'Male' or 'Female' gender is accepted");
    }
}