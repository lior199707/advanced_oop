package com.animals;

import com.mobility.Point;

/**
 * AnimalChew is an abstract class representing animals that can chew.
 * it is an Animal descendant.
 * @see com.animals.Animal
 */
public abstract class AnimalChew extends Animal {

    /**
     * AnimalChew constructor
     * passing values from descendant to Animal
     * @see com.animals.Animal
     * @param name String representation of the animal.
     * @param location Point coordinates of the current location of an animal.
     */
    public AnimalChew(String name, Point location) {
        super(name, location);
    }

    /**
     * abstract method, need to be implemented in AnimalChew descendants.
     */
    public abstract void chew();

    /**
     * makeSound activates the chew method.
     */
    @Override
    public void makeSound() {
        chew();
    }

    /**
     * equals of animalchew object.
     * @param o the object to check equality.
     * @return boolean value if the objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * toString of animalchew object.
     * @see com.animals.Animal toString() method.
     * @return String representation of the animalchew object.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
