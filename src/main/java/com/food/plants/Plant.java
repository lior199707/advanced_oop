package com.food.plants;

import com.food.EFoodType;
import com.food.Food;
import com.utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public abstract class Plant extends Food {
	/**
	 * 
	 */
	public Plant() {
		MessageUtility.logConstractor("Plant", "Plant");
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see food.IFood#getFoodtype()
	 */
	@Override
	public EFoodType getFoodType() {
		MessageUtility.logGetter(this.getClass().getSimpleName(), "getFoodType", EFoodType.VEGETABLE);
		return EFoodType.VEGETABLE;
	}
}
