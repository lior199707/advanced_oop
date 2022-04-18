package com.plants;

import com.utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {
	/**
	 * Lettuce constructor.
	 */
	public Lettuce() {
		MessageUtility.logConstractor("Lettuce", "Lettuce");
	}

	@Override
	public String foodShortPathName() {
		return "Lettuce";
	}
}
