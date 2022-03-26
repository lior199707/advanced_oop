package com.diet;

import com.animals.Animal;
import com.food.EFoodType;
import com.food.IEdible;

/**
 * Meat eating animals.
 */
public class Carnivore implements IDiet {
    @Override
    public double eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodtype())) {
            return (animal.getWeight() * 0.1);
        }
        return 0;
    }

    @Override
    public boolean canEat(EFoodType food) {
        return food == EFoodType.MEAT;
    }
}
