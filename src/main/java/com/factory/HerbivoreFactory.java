package com.factory;

import com.animals.Animal;
import com.animals.Elephant;
import com.animals.Giraffe;
import com.animals.Turtle;

public class HerbivoreFactory implements IAnimalFactory {
    @Override
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col) {
        System.out.println("In create animal");
        switch (animalType) {
            case "Giraffe" -> { return new Giraffe(name, size, horSpeed, verSpeed, col); }
            case "Elephant" -> { return new Elephant(name, size, horSpeed, verSpeed, col); }
            case "Turtle" -> { return new Turtle(name, size, horSpeed, verSpeed, col); }
        }
        return null;
    }
}
