package com.dglapps.nibverifier.infrastructure.ui;

import javax.swing.*;
import java.awt.*;

class NIBInput {

    private final JTextField nibInput1;
    private final JTextField nibInput2;
    private final JTextField nibInput3;
    private final JTextField nibInput4;

    private final JPanel panel;

    NIBInput() {
        panel = new JPanel(new FlowLayout());

        nibInput1 = new JTextField();
        nibInput1.setColumns(4);
        nibInput2 = new JTextField();
        nibInput2.setColumns(4);
        nibInput3 = new JTextField();
        nibInput3.setColumns(11);
        nibInput4 = new JTextField();
        nibInput4.setColumns(2);

        panel.add(nibInput1);
        panel.add(nibInput2);
        panel.add(nibInput3);
        panel.add(nibInput4);

        OnlyDigitsKeyListener listener = new OnlyDigitsKeyListener();

        nibInput1.addKeyListener(listener);
        nibInput2.addKeyListener(listener);
        nibInput3.addKeyListener(listener);
        nibInput4.addKeyListener(listener);

    }

    JPanel getPanel() {
        return panel;
    }

    String getNib() {
        final String nib1 = nibInput1.getText();
        final String nib2 = nibInput2.getText();
        final String nib3 = nibInput3.getText();
        final String nib4 = nibInput4.getText();
        return nib1 + nib2 + nib3 + nib4;
    }

    void clearNib() {
        nibInput1.setText("");
        nibInput2.setText("");
        nibInput3.setText("");
        nibInput4.setText("");
    }
}
