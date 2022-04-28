package com.food.plants;

import com.food.EFoodType;
import com.food.Food;
import com.utilities.MessageUtility;

/**
 * @author baroh
 *abstract class Plant extends abstract class Foods, handles food that are vegetables.
 */
public abstract class Plant extends Food {

	//Ctor
	/**
	 * Plant constructor
	 */
	public Plant() {
		MessageUtility.logConstractor("Plant", "Plant");
	}

	//end Ctor


	//override class Food

	/**
	 * @see com.food.IEdible getFoodtype()
	 * @return the food type of plant: Vegetable.
	 */
	@Override
	public EFoodType getFoodType() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
		return EFoodType.VEGETABLE;
	}

	//end override class Food

}
//end abstract class Plant
