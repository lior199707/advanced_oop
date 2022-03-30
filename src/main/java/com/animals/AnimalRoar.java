package com.animals;

import com.mobility.Point;

public abstract class AnimalRoar extends Animal {
    public AnimalRoar(String name, Point location) {
        super(name, location);
    }

    public abstract void roar();

    @Override
    public void makeSound() {
        roar();
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
