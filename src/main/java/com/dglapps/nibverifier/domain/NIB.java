package com.dglapps.nibverifier.domain;

import com.dglapps.nibverifier.shared.IsDigit;

public class NIB {
  public static final int NIB_LENGTH = 21;
  private final Bank bank;
  private final Agency agency;
  private final Account account;
  private final DigitControl digitControl;

  public NIB(String value) {
    if (value == null || value.length() != NIB_LENGTH) {
      throw new IllegalArgumentException("NIB must have a length of " + NIB_LENGTH);
    }

    IsDigit.validate(value);

    bank = new Bank(value.substring(0, Bank.LENGTH));
    agency = new Agency(value.substring(Bank.LENGTH, Bank.LENGTH + Agency.LENGTH));
    account = new Account(value.substring(Bank.LENGTH + Agency.LENGTH, Bank.LENGTH + Agency.LENGTH + Account.LENGTH));
    digitControl = new DigitControl(value.substring(NIB_LENGTH - DigitControl.LENGTH, NIB_LENGTH));

    verifyDigitControl(value, digitControl, DigitControl.from(bank, agency, account));
  }

  private void verifyDigitControl(String nib, DigitControl digitControl, DigitControl expectedDigitControl) {
    if (!digitControl.equals(expectedDigitControl)) {
      throw new InvalidDigitControlException(nib, digitControl.value());
    }
  }

  public String value() {
    return bank.value() + agency.value() + account.value() + digitControl.value();
  }

  public String formattedValue(String separator) {
    return bank.value() + separator +
        agency.value() + separator +
        account.formattedValue(separator) + separator +
        digitControl.value();
  }
}
