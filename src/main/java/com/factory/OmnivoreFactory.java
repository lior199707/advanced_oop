package com.factory;

import com.animals.Animal;
import com.animals.Bear;

public class OmnivoreFactory implements IAnimalFactory {
    @Override
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col) {
        if (animalType.equals("Bear")) {
            return new Bear(name, size, horSpeed, verSpeed, col);
        }
        return null;
    }
}
