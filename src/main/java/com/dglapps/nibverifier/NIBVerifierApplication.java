package com.dglapps.nibverifier;

import com.dglapps.nibverifier.infrastructure.driver.commandline.CommandLineController;
import com.dglapps.nibverifier.infrastructure.driver.ui.UIController;

public class NIBVerifierApplication {

	private final CommandLineController commandLineController;
	private final UIController uiController;

	public NIBVerifierApplication(CommandLineController commandLineController, UIController uiController) {
		this.commandLineController = commandLineController;
		this.uiController = uiController;
	}

	private void execute(String[] args) {
		if (args.length == 0) {
			uiController.execute();
		} else {
			commandLineController.execute(args);
		}
	}

	public static void main(final String... args) {
		new NIBVerifierApplication(
				new CommandLineController(),
				new UIController()
		).execute(args);
	}
}
