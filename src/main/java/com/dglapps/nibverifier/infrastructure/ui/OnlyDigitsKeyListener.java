package com.dglapps.nibverifier.infrastructure.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OnlyDigitsKeyListener implements KeyListener {
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        if (isPasteTextCombination(keyEvent)) {
            keyEvent.consume();
        }
    }

    @Override
    public void keyReleased(final KeyEvent keyEvent) {
    }

    @Override
    public void keyTyped(final KeyEvent keyEvent) {
        final char typedChar = keyEvent.getKeyChar();
        if (!Character.isDigit(typedChar)) {
            keyEvent.consume();
            return;
        }

        final JTextField textField = (JTextField) keyEvent.getComponent();

        if (isFieldFull(textField)) {
            jumpToNextField(keyEvent);
            return;
        }

        if (isFieldOverflow(textField)) {
            keyEvent.consume();
        }
    }

    private boolean isFieldOverflow(JTextField textField) {
        return textField.getSelectedText() == null && textField.getText().length() >= textField.getColumns();
    }

    private boolean isFieldFull(JTextField textField) {
        return textField.getSelectedText() == null && textField.getText().length() == textField.getColumns() - 1;
    }

    private void jumpToNextField(KeyEvent keyEvent) {
        keyEvent.getComponent().transferFocus();
    }

    private boolean isPasteTextCombination(KeyEvent keyEvent) {
        return (keyEvent.getKeyCode() == KeyEvent.VK_V) && ((keyEvent.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0);
    }
}
