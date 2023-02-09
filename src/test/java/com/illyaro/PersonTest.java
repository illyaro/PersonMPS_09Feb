package com.illyaro;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person testPerson;

    @BeforeEach
    void setUp() {
        String name = "Robin";
        int age = 20;
        String genderMale = "Male";
        testPerson = new Person(name, age, genderMale);
    }

    @AfterEach
    void tearDown() {
        testPerson = null;
    }

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
                "Inserted gender was: '" + otherGender + "'. Only 'Male' or 'Female' gender is accdepted");
    }

    @Test
    void NullPersonsListResultsException() {
        List<Person> persons = null;
        assertThrows(PersonException.class, () -> testPerson.averageAgePerGender(persons),
                "Persons list cannot be null");
    }

    @Test
    void EmptyPersonsListReturnArrayWithValuesMinusOneForEachGender() {
        List<Person> persons = List.of();
        double[] expected = {-1, -1};
        assertArrayEquals(expected, testPerson.averageAgePerGender(persons));
    }

    @Test
    void OnlyMalesListReturnArrayWithMinusOneInItsSecondPosition(){
        Person secondPerson = new Person("Peter", 40, "Male");
        Person thirdPerson = new Person("John", 60, "Male");
        List<Person> persons = List.of(testPerson, secondPerson, thirdPerson);

        double expectedMeanAge = (testPerson.age() + secondPerson.age() + thirdPerson.age())/persons.size();
        double[] expected = {expectedMeanAge, -1};

        assertArrayEquals(expected, testPerson.averageAgePerGender(persons));
    }

    @Test
    void OnlyFemalesListReturnArrayWithMinusOneInItsSecondPosition(){
        Person firstPerson = new Person("Anita", 15, "Female");
        Person secondPerson = new Person("Sarah", 30, "Female");
        Person thirdPerson = new Person("Angela", 60, "Female");
        List<Person> persons = List.of(firstPerson, secondPerson, thirdPerson);

        double expectedMeanAge = (firstPerson.age() + secondPerson.age() + thirdPerson.age())/persons.size();
        double[] expected = {-1, expectedMeanAge};

        assertArrayEquals(expected, testPerson.averageAgePerGender(persons));
    }

    @Test
    void ListWithMalesAndFemalesReturnAnArrayWithBothNotNegativeElements(){
        Person secondMale = new Person("Peter", 24, "Male");
        Person thirdMale = new Person("John", 5, "Male");

        Person firstFemale = new Person("Anita", 78, "Female");
        Person secondFemale = new Person("Sarah", 36, "Female");
        Person thirdFemale = new Person("Angela", 64, "Female");

        double expectedMeanAgeMale = (testPerson.age() + secondMale.age() + thirdMale.age())/3;
        double expectedMeanAgeFemale = (firstFemale.age() + secondFemale.age() + thirdFemale.age())/3;
        double[] expected = {expectedMeanAgeMale, expectedMeanAgeFemale};

        List<Person> persons = new ArrayList<>();
        persons.add(testPerson);
        persons.add(secondMale);
        persons.add(thirdMale);
        persons.add(firstFemale);
        persons.add(secondFemale);
        persons.add(thirdFemale);

        Collections.shuffle(persons);

        assertArrayEquals(expected, testPerson.averageAgePerGender(persons));
    }
}