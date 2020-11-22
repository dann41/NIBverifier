package com.dglapps.nibverifier.infrastructure.cli;

import com.dglapps.nibverifier.domain.NIBVerifier;

public class NIBVerifierCli {

    private final NIBVerifier nibVerifier;

    public NIBVerifierCli(NIBVerifier nibVerifier) {
        this.nibVerifier = nibVerifier;
    }

    public void execute(String[] args) {
        for (String arg : args) {
            if (!isDigitString(arg)) {
                System.out.println(arg + "\tNo digit");
            } else if (nibVerifier.checkNib(arg)) {
                System.out.println(arg + "\tValid");
            } else {
                System.out.println(arg + "\tInvalid");
            }
        }
    }

    private boolean isDigitString(String value) {
        return value.chars()
                .allMatch(Character::isDigit);
    }

}
