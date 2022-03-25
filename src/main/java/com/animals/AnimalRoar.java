package main.java.com.animals;

import main.java.com.mobility.Point;
import main.java.com.utilities.MessageUtility;

public abstract class AnimalRoar extends Animal {
    public AnimalRoar(String name, Point location) {
        super(name, location);
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    public abstract void roar();

    @Override
    public void makeSound() {
        roar();
    }
}
