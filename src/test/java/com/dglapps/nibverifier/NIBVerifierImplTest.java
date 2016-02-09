package com.dglapps.nibverifier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NIBVerifierImplTest {
	
	private NIBVerifier verifier;
	
	@Before
	public void setup() {
		verifier = new NIBVerifierImpl();
	}
	
	@Test
	public void checkInvalidNonDigitNib() {
		Assert.assertFalse(verifier.checkNib("23243C234234"));
	}
	
	@Test
	public void checkNullNib() {
		Assert.assertFalse(verifier.checkNib(null));
	}
	
	@Test
	public void checkInvalidWrongLengthNib() {
		Assert.assertFalse(verifier.checkNib("2234234"));
	}
	
	@Test
	public void checkValidNib() {
		Assert.assertTrue(verifier.checkNib("223423412345676543268"));
	}
	
	@Test
	public void getControlInvalidNonDigitNib() {
		Assert.assertEquals(0, verifier.getControl("23243C234234"));
	}
	
	@Test
	public void getControlNullNib() {
		Assert.assertEquals(0, verifier.getControl(null));
	}
	
	@Test
	public void getControlInvalidWrongLengthNib() {
		Assert.assertEquals(0, verifier.getControl("2234234"));
	}
	
	@Test
	public void getControlValidNib() {
		Assert.assertEquals(68, verifier.getControl("223423412345676543268"));
		Assert.assertEquals(9, verifier.getControl("2234234123456715432"));
		Assert.assertEquals(9, verifier.getControl("223423412345671543209"));
	}
	
}
