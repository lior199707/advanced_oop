package com.diet;

import com.food.EFoodType;
import com.animals.Animal;
import com.food.IEdible;

public interface IDiet {
    /**
     *
     */
    public boolean canEat(EFoodType food);

    public double eat(Animal animal, IEdible food);
}
