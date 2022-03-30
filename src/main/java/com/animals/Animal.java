package com.animals;

import com.diet.IDiet;
import com.food.IEdible;
import com.mobility.Mobile;
import com.mobility.Point;
import com.utilities.MessageUtility;

public abstract class Animal extends Mobile implements IEdible {
    private String name;
    private double weight;
    private IDiet diet;

    public Animal(String name, Point location) {
        super(location);
        setName(name);
        MessageUtility.logConstractor("Animal", name);
    }

    public abstract void makeSound();

    public boolean eat(IEdible food) {
        boolean isSuccess = false;
        double updatedWeight = getDiet().eat(this, food);
        if (updatedWeight != 0) {
            makeSound();
            setWeight(this.weight + updatedWeight);
            isSuccess = true;
        }
        MessageUtility.logBooleanFunction(this.name, "eat", food, isSuccess);
        return isSuccess;
    }

    @Override
    public double move(Point nextLocation) {
        double distance =  super.move(nextLocation);
        this.setWeight(this.weight - (distance * this.weight * 0.00025));
        return distance;
    }

    public boolean setName(String name) {
        boolean isSuccess = false;
        this.name = name.replaceAll("[0-9]","");
        if (name.matches("[a-zA-Z]+")) {
            this.name = name;
            isSuccess = true;
        }
        MessageUtility.logSetter(this.name, "setName", name, isSuccess);
        return isSuccess;
    }

    public String getName() {
        MessageUtility.logGetter(name, "getName", name);
        return name;
    }

    public double getWeight() {
        MessageUtility.logGetter(this.name, "getWeight", weight);
        return weight;
    }

    public boolean setWeight(double weight) {
        boolean isSuccess = false;
        if (weight > 0) {
            this.weight = weight;
            isSuccess = true;
        }
        MessageUtility.logSetter(getName(), "setWeight", getWeight(), isSuccess);
        return isSuccess;
    }

    public boolean setDiet(IDiet diet) {
        boolean isSuccess = true;
        this.diet = diet;
        MessageUtility.logSetter(this.name, "setDiet", this.diet, isSuccess);
        return isSuccess;
    }

    public IDiet getDiet() {
        MessageUtility.logGetter(this.name, "getDiet", this.diet);
        return diet;
    }

    @Override
    public Point getLocation() {
        MessageUtility.logGetter(this.name, "getLocation", super.getLocation());
        return super.getLocation();
    }

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

    @Override
    public String toString() {
        return "[!]" + this.name + ": total distance: [" + getTotalDistance() + "], weight: [" + this.weight + "]";
    }
}
