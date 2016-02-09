package com.dglapps.nibverifier;

public class NIBVerifierImpl implements NIBVerifier {

	private static final int NIB_LENGTH = 21;
	private static final int[] WI = { 73, 17, 89, 38, 62, 45, 53, 15, 50, 5, 49, 34, 81, 76, 27, 90, 9, 30, 3 };

	@Override
	public boolean checkNib(final String nib) {
		if (nib != null && nib.length() == NIB_LENGTH) {
			final int realCheckDigit = Integer.valueOf(nib.substring(19));
			final int validCheckDigit = getControl(nib);
			return realCheckDigit == validCheckDigit;
		}
		return false;
	}

	@Override
	public int getControl(String nib) {
		if (nib != null && nib.length() >= NIB_LENGTH - 2) {
			final char[] toValidate = nib.substring(0, NIB_LENGTH - 2).toCharArray();
			int sum = 0;

			for (int i = 0; i < toValidate.length; i++) {
				sum += Character.digit(toValidate[i], 10) * WI[i];
			}

			return 98 - (sum % 97);
		}
		return 0;
	}
}
