package main.java.com.diet;

import main.java.com.animals.Animal;
import main.java.com.food.IEdible;
import main.java.com.food.EFoodType;

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
