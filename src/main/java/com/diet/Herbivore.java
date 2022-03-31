package com.diet;

import com.animals.Animal;
import com.food.IEdible;
import com.food.EFoodType;

/**
 * class Herbivore, handles plant eating animals.
 */
public class Herbivore implements IDiet {

    public Herbivore(){}

    /**
     * if the animal can eat the food, and the food and the animal aren't pointing to the same animal
     * returns the weight the eating animal has gained in the process of eating, else returns 0
     * @param animal , the animal that eats
     * @param food, the animal that is being eaten
     * @return double, the weight the animal gained in the process of eating.
     */
    @Override
    public double eat(Animal animal, IEdible food) {
        // eating veggies increase animal weight by 7%
        if (canEat(food.getFoodtype()) && !(animal == food)) {
            return (animal.getWeight() * 0.07);
        }
        return 0;
    }

    /**
     * checks if this object can eat the food
     * @param food , reference to EFoodType
     * @return true if the food is vegetable, false otherwise
     */
    @Override
    public boolean canEat(EFoodType food) {
        // can eat VEGETABLE.
        return food == EFoodType.VEGETABLE;
    }

    /**
     * check if this carnivore object is the same reference as obj
     * @param obj , the second object
     * @return true if the same reference, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[Herbivore]";
    }
}
