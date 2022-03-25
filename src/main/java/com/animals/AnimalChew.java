package main.java.com.animals;

import main.java.com.mobility.Point;
import main.java.com.utilities.MessageUtility;

public abstract class AnimalChew extends Animal {
    public AnimalChew(String name, Point location) {
        super(name, location);
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    public abstract void chew();

    @Override
    public void makeSound() {
        chew();
    }
}
