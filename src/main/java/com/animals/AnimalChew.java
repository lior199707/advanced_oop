package com.animals;

import com.mobility.Point;

//ctor

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
     *
     * @param name , String value of the animal's name, should contain only letters.
     * @param location, Point (x,y) indicating the location of the animal.
     *                  X coordinate valid range: 0-800.
     *                  Y coordinate valid range: 0-600.
     * @param size , Int indicates the size of the animal, affect image size and eating.
     * @param horSpeed , Int value indicates animal's horizontal speed.
     * @param verSpeed , Int value indicates animal's vertical speed.
     * @param col , String representing animal's color, "BLUE", "RED", "NATURAL".
     */
    public AnimalChew(String name, Point location, int size, int horSpeed, int verSpeed, String col){
        super(name,location,size,horSpeed,verSpeed,col);
    }

    //end ctor

    //abstract methods
    /**
     * abstract method, need to be implemented in AnimalChew descendants.
     */
    public abstract void chew();
    //end abstract methods


    //override class Animal
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

    //end override class Animal
}
//end class Animal Chew
