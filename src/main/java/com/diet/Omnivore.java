package com.diet;

import com.animals.Animal;
import com.food.IEdible;
import com.food.EFoodType;

/**
 * All-type eating animals.
 */
public class Omnivore implements IDiet {
    @Override
    public double eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodtype())) {
            if (food.getFoodtype() == EFoodType.MEAT) {
                Carnivore carnivore = new Carnivore();
                return carnivore.eat(animal, food);
            } else {
                Herbivore herbivore = new Herbivore();
                return herbivore.eat(animal, food);
            }
        }
        return 0;
    }

    @Override
    public boolean canEat(EFoodType food) {
        return food == EFoodType.MEAT || food == EFoodType.VEGETABLE;
    }
}
