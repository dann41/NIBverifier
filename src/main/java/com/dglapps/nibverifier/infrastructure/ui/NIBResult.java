package com.dglapps.nibverifier.infrastructure.ui;

import javax.swing.*;
import java.awt.*;

class NIBResult {

    private static final String INPUT_NIB = "Introduce NIB";
    private static final String VALID_NIB = "NIB correcto";
    private static final String INVALID_NIB = "NIB incorrecto. Esperado %d";

    private final JLabel nibResult;

    NIBResult() {
        nibResult = new JLabel();
        nibResult.setText(INPUT_NIB);
        nibResult.validate();
        nibResult.setHorizontalAlignment(JLabel.CENTER);
        nibResult.setFont(new Font("Sans-Serif", Font.BOLD, 14));
    }

    Component getComponent() {
        return nibResult;
    }

    void setCorrectText() {
        nibResult.setText(VALID_NIB);
        nibResult.setForeground(Color.GREEN);
    }

    void setIncorrectText(int expectedControl) {
        nibResult.setText(String.format(INVALID_NIB, expectedControl));
        nibResult.setForeground(Color.RED);
    }

    void clear() {
        nibResult.setText(INPUT_NIB);
        nibResult.setForeground(Color.BLACK);
    }
}
