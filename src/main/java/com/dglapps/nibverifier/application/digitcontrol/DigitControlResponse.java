package com.dglapps.nibverifier.application.digitcontrol;

public class DigitControlResponse {
  private final String partialNib;
  private final String digitControl;

  public DigitControlResponse(String partialNib, String digitControl) {
    this.partialNib = partialNib;
    this.digitControl = digitControl;
  }

  public String partialNib() {
    return partialNib;
  }

  public String digitControl() {
    return digitControl;
  }

  public String nib() {
    return partialNib + digitControl;
  }
}
