package com.dglapps.nibverifier.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NIBVerifierImplTest {
	
	private NIBVerifier verifier;
	
	@BeforeEach
	public void setup() {
		verifier = new NIBVerifierImpl();
	}
	
	@Test
	public void checkInvalidNonDigitNib() {
		assertFalse(verifier.checkNib("23243C234234"));
	}
	
	@Test
	public void checkNullNib() {
		assertFalse(verifier.checkNib(null));
	}
	
	@Test
	public void checkInvalidWrongLengthNib() {
		assertFalse(verifier.checkNib("2234234"));
	}
	
	@Test
	public void checkValidNib() {
		assertTrue(verifier.checkNib("223423412345676543268"));
	}
	
	@Test
	public void getControlInvalidNonDigitNib() {
		assertEquals(0, verifier.getControl("23243C234234"));
	}
	
	@Test
	public void getControlNullNib() {
		assertEquals(0, verifier.getControl(null));
	}
	
	@Test
	public void getControlInvalidWrongLengthNib() {
		assertEquals(0, verifier.getControl("2234234"));
	}
	
	@Test
	public void getControlValidNib() {
		assertEquals(68, verifier.getControl("223423412345676543268"));
		assertEquals(9, verifier.getControl("2234234123456715432"));
		assertEquals(9, verifier.getControl("223423412345671543209"));
	}
	
}
