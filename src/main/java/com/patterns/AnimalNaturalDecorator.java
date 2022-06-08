package com.patterns;

import com.animals.Animal;

public class AnimalNaturalDecorator extends AnimalColorDecorator {

    public AnimalNaturalDecorator(Animal a) {
        super(a);
    }

    @Override
    public Animal makeAnimal() {
        setColor("NATURAL");
        return getAnimal();
    }
}