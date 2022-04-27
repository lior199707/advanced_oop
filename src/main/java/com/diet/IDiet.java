package com.diet;

import com.food.EFoodType;
import com.animals.Animal;
import com.food.IEdible;

/**
 * Interface Idiot, describing eating functionality
 */
public interface IDiet {

    /**
     * @param food , the food type of the animal being eaten
     * @return true if this object can eat the food object
     */
    public boolean canEat(EFoodType food);

    /**
     * @param animal  , the anima that is eating
     * @param food , the food type of the animal that is being eaten
     * @return true if animal can eat food by its food type
     */
    public double eat(Animal animal, IEdible food);
}
//end interface IDiet
