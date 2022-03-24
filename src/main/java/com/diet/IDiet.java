package com.diet;

public interface IDiet {
    /**
     *
     */
    public boolean canEat(EFoodType food);

    public double eat(Animal animal, IEdible food);
}
