package com.dglapps.nibverifier.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NIBTest {

  @Test
  public void invalidSizeShouldThrowIllegalArgumentException() {
    assertThatThrownBy(() -> new NIB("23243C234234"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void nullShouldThrowIllegalArgumentException() {
    assertThatThrownBy(() -> new NIB(null))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void nibWithCharactersThrowIllegalArgumentException() {
    assertThatThrownBy(() -> new NIB(" 23243C234234"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void validNibGetsInstantiated() {
    NIB nib = new NIB("223423412345676543268");
    assertThat(nib.value()).isEqualTo("223423412345676543268");
  }

  @Test
  public void validNibReturnsFormattedText() {
    NIB nib = new NIB("223423412345676543268");
    assertThat(nib.formattedValue(" ")).isEqualTo("2234 2341 2345 6765 432 68");
  }

}
