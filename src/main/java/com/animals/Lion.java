package com.animals;

import com.diet.Carnivore;
import com.food.EFoodType;
import com.food.IEdible;
import com.mobility.Point;
import com.utilities.MessageUtility;

import java.util.Random;

/**
 * Lion class representing the Lion animal. It can roar!
 * @see com.animals.AnimalRoar
 */
public class Lion extends AnimalRoar {
     /**
     * constant double value of the default weight of a lion.
     */
    private static final double DEFAULT_WEIGHT = 408.2;
    /**
     * constant Point coordinates of the default starting location of a lion.
     */
    private static final Point DEFAULT_STARTING_LOCATION = new Point(20, 0);
    /**
     * constant integer value of the default scar count of a lion.
     */
    private static final int DEFAULT_SCAR_COUNT = 0;

    /**
     * integer value of the scar count of a lion.
     */
    private int scarCount;

    /**
     * Lion constructor.
     * setting default weight, scar count and diet!
     * passing name and location to super.
     * @see com.animals.AnimalRoar
     * @param name String representation of lion name.
     * @param location Point coordinates indicating lion location.
     */
    public Lion(String name, Point location) {
        super(name, location);
        setWeight(DEFAULT_WEIGHT);
        setDiet(new Carnivore());
        scarCount = DEFAULT_SCAR_COUNT;
        MessageUtility.logConstractor(getClass().getSimpleName(), name);
    }

    /**
     * Lion constructor.
     * passing name and default starting location to main constructor.
     * @param name String representation of lion name.
     */
    public Lion(String name) {
        this(name, DEFAULT_STARTING_LOCATION);
    }

    /**
     * if eat of Animal was successful, randomly increase the scar count.
     * @see com.animals.Animal eat() method.
     * @param food an edible type of animal to eat.
     * @return boolean value if the lion ate or not.
     */
    @Override
    public boolean eat(IEdible food) {
        boolean isSuccess = super.eat(food);
        if (isSuccess){
            Random random = new Random();
            if (random.nextInt(2) == 0) {
                this.scarCount++;
            }
        }
        MessageUtility.logBooleanFunction(getName(), "eat", food, isSuccess);
        return isSuccess;
    }

    /**
     * roar implementation of lion.
     */
    @Override
    public void roar() {
        MessageUtility.logSound(getName(), "Roars, then stretches and shakes its mane");
    }

    /**
     * food type getter.
     * @return EFoodType.MEAT
     */
    @Override
    public EFoodType getFoodtype() {
        return EFoodType.NOTFOOD;
    }

    /**
     * scar count getter.
     * @return integer value of the lion's scar count.
     */
    public int getScarCount() {
        MessageUtility.logGetter(getName(), "getScarCount", scarCount);
        return scarCount;
    }

    /**
     * equals method of Lion object.
     * @param o the object to check equality.
     * @return boolean value if objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Lion lion = (Lion) o;

        return getScarCount() == lion.getScarCount();
    }

    /**
     * toString of lion.
     * @return String representation of Lion.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
