package com.dglapps.nibverifier.domain;

import com.dglapps.nibverifier.shared.IsDigit;

public class Agency {
  public final static int LENGTH = 4;
  private final String value;

  public Agency(String value) {
    if (value == null || value.length() != LENGTH) {
      throw new IllegalArgumentException("Agency part must have a length of " + LENGTH);
    }

    IsDigit.validate(value);

    this.value = value;
  }

  public String value() {
    return value;
  }
}
