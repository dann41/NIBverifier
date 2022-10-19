package com.dglapps.nibverifier;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NIBVerifierImplTest {

	private NIBVerifier verifier;

	@BeforeEach
	public void setup() {
		verifier = new NIBVerifierImpl();
	}

	@Test
	public void checkInvalidNonDigitNib() {
		assertThat(verifier.checkNib("23243C234234")).isFalse();
	}

	@Test
	public void checkNullNib() {
		assertThat(verifier.checkNib(null)).isFalse();
	}

	@Test
	public void checkInvalidWrongLengthNib() {
		assertThat(verifier.checkNib("2234234")).isFalse();
	}

	@Test
	public void checkValidNib() {
		assertThat(verifier.checkNib("223423412345676543268")).isTrue();
	}

	@Test
	public void getControlInvalidNonDigitNib() {
		assertThat(verifier.getControl("23243C234234")).isEqualTo(0);
	}

	@Test
	public void getControlNullNib() {
		assertThat(verifier.getControl(null)).isEqualTo(0);
	}

	@Test
	public void getControlInvalidWrongLengthNib() {
		assertThat(verifier.getControl("2234234")).isEqualTo(0);
	}

	@Test
	public void getControlValidNib() {
		assertThat(verifier.getControl("223423412345676543268")).isEqualTo(68);
		assertThat(verifier.getControl("2234234123456715432")).isEqualTo(9);
		assertThat(verifier.getControl("223423412345671543209")).isEqualTo(9);
	}

}
