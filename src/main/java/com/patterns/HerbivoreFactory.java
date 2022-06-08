package com.patterns;

import com.animals.Animal;

public class HerbivoreFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        // call to addAnimalDialog for herbivores - herbivore factory
        return null;
    }
}
