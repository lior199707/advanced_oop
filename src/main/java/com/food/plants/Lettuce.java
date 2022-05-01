package com.food.plants;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {

	//Ctor

	/**
	 * Lettuce constructor.
	 */
	public Lettuce() { }

	//end Ctor


	//override class Food
	/**
	 * override abstract class Food animalShortPathName().
	 * @return String representation of the short path name for loading Lettuce image.
	 */
	@Override
	public String foodShortPathName() {
		return "Lettuce";
	}

	//end override class Food

}
//end class Lettuce
