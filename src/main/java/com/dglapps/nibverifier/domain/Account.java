package com.dglapps.nibverifier.domain;

import com.dglapps.nibverifier.shared.IsDigit;

public class Account {
  public final static int LENGTH = 11;
  private final String value;

  public Account(String value) {
    if (value == null || value.length() != LENGTH) {
      throw new IllegalArgumentException("Account must have a length of " + LENGTH);
    }

    IsDigit.validate(value);

    this.value = value;
  }

  public String value() {
    return value;
  }

  public String formattedValue(String separator) {
    return value.substring(0, 4) + separator + value.substring(4, 8) + separator + value.substring(8);
  }
}
