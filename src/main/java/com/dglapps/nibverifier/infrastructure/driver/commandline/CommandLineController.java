package com.dglapps.nibverifier.infrastructure.driver.commandline;

import com.dglapps.nibverifier.application.verification.NIBVerificationResponse;
import com.dglapps.nibverifier.application.verification.VerifyNIBUseCase;

public class CommandLineController {

  private final VerifyNIBUseCase verifyNIBUseCase;

  public CommandLineController() {
    verifyNIBUseCase = new VerifyNIBUseCase();
  }

  public void execute(String... args) {
    if (args == null || args.length == 0) {
      System.out.println("Please add at least one NIB to verify");
      return;
    }

    for (String arg : args) {
      NIBVerificationResponse response = verifyNIBUseCase.execute(arg);
      if (response.valid()) {
        System.out.println(response.nib() + "\tValid");
      } else {
        System.out.println(response.nib() + "\tInvalid");
      }
    }
  }

}
