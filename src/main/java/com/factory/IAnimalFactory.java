package com.factory;

import com.animals.Animal;

public interface IAnimalFactory {
    public Animal createAnimal(String animalType, String name, int size, int horSpeed, int verSpeed, String col);
}
