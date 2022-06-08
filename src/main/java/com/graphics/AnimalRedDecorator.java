package com.graphics;

import com.animals.Animal;

public class AnimalRedDecorator extends AnimalColorDecorator {

    public AnimalRedDecorator(Animal a) {
        super(a);
    }

    @Override
    public Animal makeAnimal() {
        setColor("RED");
        return getAnimal();
    }
}

