package com.dglapps.nibverifier.domain;

import com.dglapps.nibverifier.shared.IsDigit;

import java.util.Objects;

public class DigitControl {

  public static final int LENGTH = 2;
  private static final int[] WI = {73, 17, 89, 38, 62, 45, 53, 15, 50, 5, 49, 34, 81, 76, 27, 90, 9, 30, 3};
  private final String value;

  public DigitControl(String value) {
    if (value == null || value.length() != LENGTH) {
      throw new IllegalArgumentException("DigitControl part must have a length of " + LENGTH);
    }

    IsDigit.validate(value);

    this.value = value;
  }

  public String value() {
    return value;
  }

  public static DigitControl from(Bank bank, Agency agency, Account account) {
    return from(bank.value() + agency.value() + account.value());
  }

  public static DigitControl from(String partialNib) {
    int expectedLength = NIB.NIB_LENGTH - LENGTH;
    if (partialNib == null || partialNib.length() != expectedLength) {
      throw new IllegalArgumentException("Partial NIB has wrong length. Expecting " + expectedLength);
    }

    final char[] toValidate = (partialNib).toCharArray();

    int sum = 0;

    for (int i = 0; i < toValidate.length; i++) {
      sum += Character.digit(toValidate[i], 10) * WI[i];
    }

    int control = 98 - (sum % 97);

    return new DigitControl(String.format("%02d", control));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DigitControl that = (DigitControl) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
