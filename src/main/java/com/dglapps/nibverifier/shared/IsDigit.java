package com.dglapps.nibverifier.shared;

public final class IsDigit {

  public static void validate(String value) {
    final char[] toValidate = value.toCharArray();
    for (char c : toValidate) {
      if (!Character.isDigit(c)) {
        throw new IllegalArgumentException(value + " is not a digit string");
      }
    }
  }
}
