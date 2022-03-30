package com.animals;

import com.mobility.Point;

public abstract class AnimalChew extends Animal {
    public AnimalChew(String name, Point location) {
        super(name, location);
    }

    public abstract void chew();

    @Override
    public void makeSound() {
        chew();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
