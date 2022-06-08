package com.graphics;

import com.animals.Animal;

public abstract class AnimalColorDecorator implements IColorDecorator {
    private Animal animal;

    public AnimalColorDecorator(Animal a) {
        this.animal = a;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    @Override
    public void setColor(String color) {
        animal.setColor(color);
        animal.loadImages(animal.animalShortPathName());
    }

    public abstract Animal makeAnimal();
}



