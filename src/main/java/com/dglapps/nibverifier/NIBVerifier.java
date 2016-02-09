package com.dglapps.nibverifier;

public interface NIBVerifier {

	/**
	 * Check if the given nib is valid
	 * @param nib string made of digits
	 * @return
	 */
	boolean checkNib(String nib);
	
	/**
	 * Calculate the digit control
	 * @param nib string made of digits
	 * @return
	 */
	int getControl(String nib);
	
}
