package com.dglapps.nibverifier.infrastructure.ui;

import com.dglapps.nibverifier.domain.NIBVerifier;

import javax.swing.*;
import java.awt.*;

public class NIBVerifierFrame extends JFrame {

    private static final long serialVersionUID = -6564074112664425080L;

    private NIBInput nibInput;
    private JLabel nibResult;

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
        final JPanel nib = nibInput.getPanel();
        placeNibInput(panel, nib);

        JButton nibCheck = new JButton("Comprobar");
        placeNibCheckButton(panel, nibCheck);

        JButton nibClear = new JButton("Limpiar");
        placeCleanButton(panel, nibClear);

        nibResult = new JLabel();
        nibResult.setText("Introduce NIB");
        nibResult.validate();
        nibResult.setHorizontalAlignment(JLabel.CENTER);
        nibResult.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        placeResult(panel, nibResult);

        nibCheck.addActionListener(actionEvent -> onCheckNib());
        nibClear.addActionListener(actionEvent -> onClearNib());

        setContentPane(panel);
        pack();
        setMinimumSize(getPreferredSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void placeNibInput(JPanel panel, JPanel nib) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weighty = 4;
        constraints.weightx = 4;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(nib, constraints);
    }

    private void placeNibCheckButton(JPanel panel, JButton nibCheck) {
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

    private void placeCleanButton(JPanel panel, JButton nibClear) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 2;
        constraints.weightx = 2;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(nibClear, constraints);
    }

    private void placeResult(JPanel panel, JLabel nibResult) {
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
            System.out.println(nib);
            nibResult.setText("NIB correcto");
            nibResult.setForeground(Color.GREEN);
        } else {
            final int expected = nibVerifier.getControl(nib);
            nibResult.setText("NIB incorrecto. Esperado " + expected);
            nibResult.setForeground(Color.RED);
        }
        repaint();
    }

    private void onClearNib() {
        nibInput.clearNib();
        nibResult.setText("Introduce NIB");
        nibResult.setForeground(Color.BLACK);
        repaint();
    }

}
