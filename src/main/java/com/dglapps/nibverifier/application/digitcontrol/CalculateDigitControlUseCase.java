package com.dglapps.nibverifier.application.digitcontrol;

import com.dglapps.nibverifier.domain.DigitControl;

public class CalculateDigitControlUseCase {
  public DigitControlResponse execute(String partialNib) {
    DigitControl digitControl = DigitControl.from(partialNib);
    return new DigitControlResponse(partialNib, digitControl.value());
  }
}
