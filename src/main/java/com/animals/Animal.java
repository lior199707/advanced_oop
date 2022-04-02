package com.animals;

import com.diet.IDiet;
import com.food.EFoodType;
import com.food.IEdible;
import com.mobility.Mobile;
import com.mobility.Point;
import com.utilities.MessageUtility;

import java.util.Objects;

/**
 * Animal class is an abstract representing a mobile animal.
 * Every animal type is also edible.
 * @see com.mobility.Mobile
 * @see com.food.IEdible
 */
public abstract class Animal extends Mobile implements IEdible {
    /**
     * String value of the animal name.
     */
    private String name;
    /**
     * double value of the animal weight.
     */
    private double weight;
    /**
     * IDiet reference indicating the diet type of animal.
     */
    private IDiet diet;

    /**
     * Animal constructor.
     * @param name - String value of animal name.
     * @param location - Point object of the current location.
     */
    public Animal(String name, Point location) {
        super(location);
        setName(name);
        if (location != null) {
            getLocation();
        }
        MessageUtility.logConstractor("Animal", getName());
    }

    /**
     * abstract method, animals will have to implement their own sounds.
     */
    public abstract void makeSound();

    /**
     * eat activates the appropriate eat method based on the current animal's diet.
     * if the current animal is able to eat the given animal it will activate makeSound
     * of the current animal and set the new weight.
     * @param food an edible type of animal to eat.
     * @return boolean value if this animal was able to eat the given animal or not.
     */
    public boolean eat(IEdible food) {
        boolean isSuccess = false;
        double updatedWeight = this.diet.eat(this, food);
        if (updatedWeight != 0) {
            makeSound();
            setWeight(this.weight + updatedWeight);
            isSuccess = true;
        }
        MessageUtility.logBooleanFunction(this.name, "eat", food, isSuccess);
        return isSuccess;
    }

    /**
     * move uses Mobile.move() method to set the new location and calculate the distance between
     * the current and nextLocation.
     * It evaluates the new weight with the distance based on this formula:
     * current weight - (distance * current weight * CONST = 0.00025)
     *
     * @see com.mobility.Mobile move() for reference.
     * @param nextLocation - Point object to change location to.
     * @return distance traveled between two points.
     */
    @Override
    public double move(Point nextLocation) {
        final double CONST = 0.00025;
        double distance = super.move(nextLocation);
        if (distance > 0)
            this.setWeight(this.weight - (distance * this.weight * CONST));
        return distance;
    }

    /**
     * name setter.
     * this method checks if given name param is alphabetic, if so it updates the name field.
     * otherwise, it removes all non-alphabetic characters.
     * if the String is not empty assigns the remaining characters to name, otherwise sets "NaN",
     * indicating the animal has no name.
     *
     * @param name the String object to set animal name.
     * @return boolean value if name was set or not (NaN).
     */
    public boolean setName(String name) {
        boolean isSuccess = name.chars().allMatch(Character::isAlphabetic);
        if (isSuccess){
            this.name = name;
        } else {
            System.out.println("-- Removing non-alphabetic characters from name --");
            this.name = name.replaceAll("[^A-Za-z]", "");
            System.out.println("New name: " + this.name);
            isSuccess = !Objects.equals(this.name, "");
            if (!isSuccess){
                this.name = "NaN";
            }
        }
        MessageUtility.logSetter(this.name, "setName", name, isSuccess);
        return isSuccess;
    }

    /**
     * name getter.
     * @return String value representing animal name.
     */
    public String getName() {
        MessageUtility.logGetter(name, "getName", name);
        return name;
    }

    /**
     * weight getter.
     * @return double value representing animal weight.
     */
    public double getWeight() {
        MessageUtility.logGetter(this.name, "getWeight", weight);
        return weight;
    }

    /**
     * weight setter
     * checks if weight >= 0, if so it updates the weight field.
     * otherwise, set the weight to 0.  weight cannot be negative!
     *
     * @param weight double value representing weight.
     * @return boolean value if weight was set or not.
     */
    public boolean setWeight(double weight) {
        boolean isSuccess = weight > 0;
        if (isSuccess) {
            this.weight = weight;
        }
        MessageUtility.logSetter(this.name, "setWeight", weight, isSuccess);
        return isSuccess;
    }

    /**
     * diet setter
     * @param diet the diet object to set.
     * @return always true.
     */
    public boolean setDiet(IDiet diet) {
        this.diet = diet;
        MessageUtility.logSetter(this.name, "setDiet", diet, true);
        return true;
    }

    /**
     * diet getter
     * @return IDiet reference.
     */
    public IDiet getDiet() {
        MessageUtility.logGetter(this.name, "getDiet", this.diet);
        return diet;
    }

    /**
     * location getter.
     * @see com.mobility.Mobile getLocation() for reference.
     * @return current location of the animal, based on Mobile.getLocation.
     */
    @Override
    public Point getLocation() {
        MessageUtility.logGetter(this.name, "getLocation", super.getLocation());
        return super.getLocation();
    }

    @Override
    public EFoodType getFoodtype() {
        MessageUtility.logGetter(this.name, "getFoodtype", EFoodType.MEAT);
        return EFoodType.MEAT;
    }

    /**
     * equals method of animal object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Animal animal = (Animal) o;

        if (Double.compare(animal.getWeight(), getWeight()) != 0) return false;
        if (getName() != null ? !getName().equals(animal.getName()) : animal.getName() != null) return false;
        return getDiet() != null ? getDiet().equals(animal.getDiet()) : animal.getDiet() == null;
    }

    /**
     * @return String representation of the animal object.
     */
    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "]: " + this.name;
    }
}
