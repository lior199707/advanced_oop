package com.graphics;

import com.animals.Animal;

public class AnimalBlueDecorator extends AnimalColorDecorator {

    public AnimalBlueDecorator(Animal a) {
        super(a);
    }

    @Override
    public Animal makeAnimal() {
        setColor("BLUE");
        return getAnimal();
    }
}
