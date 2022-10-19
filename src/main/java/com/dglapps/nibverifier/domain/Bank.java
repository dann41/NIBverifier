package com.dglapps.nibverifier.domain;

import com.dglapps.nibverifier.shared.IsDigit;

public class Bank {
  public final static int LENGTH = 4;
  private final String value;

  public Bank(String value) {
    if (value == null || value.length() != LENGTH) {
      throw new IllegalArgumentException("Bank part must have a length of " + LENGTH);
    }

    IsDigit.validate(value);

    this.value = value;
  }

  public String value() {
    return value;
  }
}
