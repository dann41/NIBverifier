package com.dglapps.nibverifier.infrastructure.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NIBInput implements KeyListener {

    private final JTextField nibInput1;
    private final JTextField nibInput2;
    private final JTextField nibInput3;
    private final JTextField nibInput4;

    private final JPanel panel;

    public NIBInput() {
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

        nibInput1.addKeyListener(this);
        nibInput2.addKeyListener(this);
        nibInput3.addKeyListener(this);
        nibInput4.addKeyListener(this);

    }

    public JPanel getPanel() {
        return panel;
    }

    public String getNib() {
        final String nib1 = nibInput1.getText();
        final String nib2 = nibInput2.getText();
        final String nib3 = nibInput3.getText();
        final String nib4 = nibInput4.getText();
        return nib1 + nib2 + nib3 + nib4;
    }

    public void clearNib() {
        nibInput1.setText("");
        nibInput2.setText("");
        nibInput3.setText("");
        nibInput4.setText("");
    }

    @Override
    public void keyPressed(final KeyEvent arg0) {
        if ((arg0.getKeyCode() == KeyEvent.VK_V) && ((arg0.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            arg0.consume();
        }
    }

    @Override
    public void keyReleased(final KeyEvent arg0) {
    }

    @Override
    public void keyTyped(final KeyEvent arg0) {
        final char c = arg0.getKeyChar();
        if (!Character.isDigit(c)) {
            arg0.consume();
        }
        final JTextField textField = (JTextField) arg0.getComponent();
        if (textField.getText().length() == textField.getColumns() - 1) {
            arg0.getComponent().transferFocus();
        } else if (textField.getText().length() >= textField.getColumns()) {
            arg0.consume();
        }
    }

}
