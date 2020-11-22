package com.dglapps.nibverifier.infrastructure;

import com.dglapps.nibverifier.domain.NIBVerifier;
import com.dglapps.nibverifier.domain.NIBVerifierImpl;
import com.dglapps.nibverifier.infrastructure.cli.NIBVerifierCli;
import com.dglapps.nibverifier.infrastructure.ui.NIBVerifierFrame;

public class NIBVerifierApplication {

	public static void main(final String... args) {
		NIBVerifier nibVerifier = new NIBVerifierImpl();
		if (args.length == 0) {
			launchUI(nibVerifier);
		} else {
			processCommandLine(nibVerifier, args);
		}
	}

	private static void launchUI(NIBVerifier nibVerifier) {
		new NIBVerifierFrame(nibVerifier)
				.setVisible(true);
	}

	private static void processCommandLine(NIBVerifier nibVerifier, String[] args) {
		new NIBVerifierCli(nibVerifier).execute(args);
	}
	
}
