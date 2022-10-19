package com.dglapps.nibverifier.domain;

public class InvalidDigitControlException extends RuntimeException {
  public InvalidDigitControlException(String nib, String digitControl) {
    super("Invalid digit control for nib " + nib + ". Digit Control: " + digitControl);
  }
}
