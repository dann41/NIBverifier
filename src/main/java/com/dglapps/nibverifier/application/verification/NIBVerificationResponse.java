package com.dglapps.nibverifier.application.verification;

public class NIBVerificationResponse {
  private final String nib;
  private final boolean valid;

  public NIBVerificationResponse(String nib, boolean valid) {
    this.nib = nib;
    this.valid = valid;
  }

  public String nib() {
    return nib;
  }

  public boolean valid() {
    return valid;
  }
}
