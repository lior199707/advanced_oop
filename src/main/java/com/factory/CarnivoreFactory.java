package com.factory;

import com.animals.Animal;
import com.animals.Lion;

public class CarnivoreFactory implements IAnimalFactory {


    @Override
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col) {
        System.out.println("I'm in create lion");
        if (animalType.equals("Lion")) {
            System.out.println("creating lion");
            return new Lion(name, size, horSpeed, verSpeed, col);
        }
        System.out.println("out of creating lion");

        return null;
    }
}
