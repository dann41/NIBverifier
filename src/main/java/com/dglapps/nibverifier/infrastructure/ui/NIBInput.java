package com.dglapps.nibverifier.infrastructure.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class NIBInput {

    private final List<JTextField> nibInputs;

    private final JPanel panel;

    NIBInput() {
        panel = new JPanel(new FlowLayout());

        nibInputs = Arrays.asList(
                getField(4),
                getField(4),
                getField(11),
                getField(2)
        );

        nibInputs.forEach(panel::add);
        final OnlyDigitsKeyListener listener = new OnlyDigitsKeyListener();
        nibInputs.forEach(input -> input.addKeyListener(listener));
    }

    Component getComponent() {
        return panel;
    }

    String getNib() {
        return nibInputs.stream()
                .map(JTextField::getText)
                .collect(Collectors.joining());
    }

    void clear() {
        nibInputs.forEach(input -> input.setText(""));
    }

    private JTextField getField(int columns) {
        JTextField field = new JTextField();
        field.setColumns(columns);
        return field;
    }
}
