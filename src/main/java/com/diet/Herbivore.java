package main.java.com.diet;

import main.java.com.animals.Animal;
import main.java.com.food.IEdible;
import main.java.com.food.EFoodType;

/**
 * Plant eating animals.
 */
public class Herbivore implements IDiet {
    @Override
    public double eat(Animal animal, IEdible food) {
        // eating veggies increase animal weight by 7%
        if (canEat(food.getFoodtype())) {
            return (animal.getWeight() * 0.07);
        }
        return 0;
    }

    @Override
    public boolean canEat(EFoodType food) {
        // can eat VEGETABLE.
        return food == EFoodType.VEGETABLE;
    }
}
