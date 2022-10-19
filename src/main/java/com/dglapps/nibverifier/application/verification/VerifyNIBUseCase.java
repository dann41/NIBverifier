package com.dglapps.nibverifier.application.verification;

import com.dglapps.nibverifier.domain.InvalidDigitControlException;
import com.dglapps.nibverifier.domain.NIB;

public class VerifyNIBUseCase {

  public NIBVerificationResponse execute(String nib) {
    try {
      new NIB(nib);
      return new NIBVerificationResponse(nib, true);
    } catch (IllegalArgumentException | InvalidDigitControlException e) {
      return new NIBVerificationResponse(nib, false);
    }
  }
}
