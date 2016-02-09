package com.dglapps.nibverifier;

public class NIBVerifierApplication {

	public static void main(final String... args) {
		if (args.length == 0) {
			final NIBVerifierFrame frame = new NIBVerifierFrame(new NIBVerifierImpl());
			frame.setVisible(true);
		} else {
			// command line
			NIBVerifier verifier = new NIBVerifierImpl();
			for (String arg : args) {
				if (!isDigitString(arg)) {
					System.out.println(arg + "\tNo digit");
				} else if (verifier.checkNib(arg)) {
					System.out.println(arg + "\tValid");
				} else {
					System.out.println(arg + "\tInvalid");
				}
			}
		}
	}

	private static boolean isDigitString(String value) {
		final char[] toValidate = value.toCharArray();
		for (char c : toValidate) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
}
