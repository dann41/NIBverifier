package com.dglapps.nibverifier.domain;

import java.util.Optional;

public class NIBWithoutDC {
  private static final int NIB_WITHOUT_DC_LENGTH = 19;
  private final String value;

  public NIBWithoutDC(String value) {
    if (value == null || value.length() != NIB_WITHOUT_DC_LENGTH) {
      throw new IllegalArgumentException("NIB must have a length of " + NIB_WITHOUT_DC_LENGTH);
    }

    this.value = value;
  }

  public String value() {
    return value;
  }

  public static NIBWithoutDC fromNIB(String nib) {
    String partialNib = Optional.ofNullable(nib)
        .map(value -> value.substring(0, NIB_WITHOUT_DC_LENGTH))
        .orElse(null);
    return new NIBWithoutDC(partialNib);
  }
}
