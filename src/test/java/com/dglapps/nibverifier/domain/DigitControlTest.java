package com.dglapps.nibverifier.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DigitControlTest {


  @Test
  public void nullControlThrowsIllegalArgumentException() {
    assertThatThrownBy(() -> new DigitControl(null))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void wrongLengthThrowsIllegalArgumentException() {
    assertThatThrownBy(() -> new DigitControl("123"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void withCharactersThrowsIllegalArgumentException() {
    assertThatThrownBy(() -> new DigitControl("1C"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void fromPartialReturnsCalculatedControl() {
    DigitControl digitControl = DigitControl.from("2234234123456765432");

    assertThat(digitControl.value()).isEqualTo("68");
  }

  @Test
  public void fromPartialReturnsCalculatedControlWithLeading0() {
    DigitControl digitControl = DigitControl.from("2234234123456715432");

    assertThat(digitControl.value()).isEqualTo("09");
  }
}