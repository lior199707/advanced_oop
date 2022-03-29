package com.diet;

import com.animals.Animal;
import com.food.IEdible;
import com.food.EFoodType;

/**
 * All-type eating animals.
 */
public class Omnivore implements IDiet {
    private final IDiet carnivore;
    private final IDiet herbivore;

    public Omnivore(){
        this.carnivore = new Carnivore();
        this.herbivore = new Herbivore();
    }

    @Override
    public double eat(Animal animal, IEdible food) {
        if (canEat(food.getFoodtype()) && !(animal == food)) {
            if (food.getFoodtype() == EFoodType.MEAT) {
                return carnivore.eat(animal, food);
            } else {
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
