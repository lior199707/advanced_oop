package com.food.plants;

import com.food.EFoodType;
import com.food.Food;

/**
 * @author baroh
 *abstract class Plant extends abstract class Foods, handles food that are vegetables.
 */
public abstract class Plant extends Food {

	//Ctor
	/**
	 * Plant constructor
	 */
	public Plant() { }

	//end Ctor


	//override class Food

	/**
	 * @see com.food.IEdible getFoodtype()
	 * @return the food type of plant: Vegetable.
	 */
	@Override
	public EFoodType getFoodType() {
		return EFoodType.VEGETABLE;
	}

	//end override class Food

}
//end abstract class Plant
