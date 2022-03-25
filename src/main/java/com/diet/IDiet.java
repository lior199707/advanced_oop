package main.java.com.diet;

import main.java.com.food.EFoodType;
import main.java.com.animals.Animal;
import main.java.com.food.IEdible;

public interface IDiet {
    /**
     *
     */
    public boolean canEat(EFoodType food);

    public double eat(Animal animal, IEdible food);
}
