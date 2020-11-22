package com.dglapps.nibverifier.infrastructure.ui;

import com.dglapps.nibverifier.domain.NIBVerifier;

import javax.swing.*;
import java.awt.*;

public class NIBVerifierFrame extends JFrame {

    private static final long serialVersionUID = -6564074112664425080L;

    private NIBInput nibInput;
    private NIBResult nibResult;

    private final NIBVerifier nibVerifier;

    public NIBVerifierFrame(final NIBVerifier nibVerifier) {
        super();
        this.nibVerifier = nibVerifier;

        initUI();
    }

    private void initUI() {
        setTitle("Verificador NIB");
        final JPanel panel = new JPanel(new GridBagLayout());

        nibInput = new NIBInput();
        placeNibInput(panel, nibInput.getComponent());

        JButton nibCheck = new JButton("Comprobar");
        placeNibCheckButton(panel, nibCheck);
        nibCheck.addActionListener(actionEvent -> onCheckNib());

        JButton nibClear = new JButton("Limpiar");
        placeCleanButton(panel, nibClear);
        nibClear.addActionListener(actionEvent -> onClearNib());

        nibResult = new NIBResult();
        placeResult(panel, nibResult.getComponent());

        setContentPane(panel);
        pack();
        setMinimumSize(getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void placeNibInput(JPanel panel, Component nib) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weighty = 4;
        constraints.weightx = 4;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(nib, constraints);
    }

    private void placeNibCheckButton(JPanel panel, Component nibCheck) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.ipadx = 0;
        constraints.weighty = 2;
        constraints.weightx = 2;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(nibCheck, constraints);
    }

    private void placeCleanButton(JPanel panel, Component nibClear) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 2;
        constraints.weightx = 2;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(nibClear, constraints);
    }

    private void placeResult(JPanel panel, Component nibResult) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weighty = 20;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(nibResult, constraints);
    }

    private void onCheckNib() {
        String nib = nibInput.getNib();
        if (nibVerifier.checkNib(nib)) {
            nibResult.setCorrectText();
        } else {
            final int expected = nibVerifier.getControl(nib);
            nibResult.setIncorrectText(expected);
        }
        repaint();
    }

    private void onClearNib() {
        nibInput.clear();
        nibResult.clear();
        repaint();
    }

}
