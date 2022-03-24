package animals;

import diet.IDiet;
import food.IEdible;
import mobility.Mobile;
import mobility.Point;
import utilities.MessageUtility;

public abstract class Animal extends Mobile implements IEdible {
    private String name;
    private double weight;
    private IDiet diet;

    public Animal(String name, double weight, Point location) {
        super(location);
        this.name = name;
        setWeight(weight);
        MessageUtility.logConstractor(this.getClass().getSimpleName(), name);
    }

    public abstract void makeSound();

    public abstract boolean eat(IEdible food);

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public IDiet getDiet() {
        return diet;
    }

    public boolean setWeight(double weight) {
        if (weight > 0) {
            this.weight = weight;
            return true;
        }
        return false;
    }
}
