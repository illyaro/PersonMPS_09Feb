package com.illyaro;

import java.util.List;

/**
 * Class representing a person with a name, age and gender
 *
 * @author Illya Rozumovskyy
 */
public class Person {
    private final String name;
    private final int age;
    private final String gender; // Male, Female

    /**
     * Constructs a person with a name, age and gender.
     *
     * @throws PersonException in case any of the following conditions are met: name is null or empty, negative age,
     * gender different form 'Male' or 'Female'
     * @param name the name of the person
     * @param age the age of the person
     * @param gender the gender of the person
     */
    public Person(String name, int age, String gender){
        if (name == null) {
            throw new PersonException("Persons name cannot be null");
        }
        if(name.isEmpty()) {
            throw new PersonException("Persons name cannot be empty");
        }
        if(age < 0) {
            throw new PersonException("Persons age cannot be negative");
        }
        if( !(gender.equals("Male") || gender.equals("Female")) ){
            throw new PersonException("Inserted gender was: '" + gender + "'. Only 'Male' or 'Female' gender is accdepted");
        }
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    public String gender() {
        return gender;
    }

    /**
     * Computes the average age of male and female persons in a list and returns the result in
     * an array of two elements (the first element is the male mean age and the second one is the
     * female mean age)
     *
     * @param persons the list of persons to compute the average age from
     * @return an array of two elements, first is the male mean age and the second one is the female mean age
     *
     * @throws PersonException in case the persons list is null
     */
    public double[] averageAgePerGender(List<Person> persons){
        if(persons == null){
            throw new PersonException("Persons list cannot be null");
        }
        if(persons.isEmpty()){
            return new double[]{-1, -1};
        }
        return null;
    }
}
