package com.dglapps.nibverifier.infrastructure.driver.ui;

import com.dglapps.nibverifier.application.digitcontrol.CalculateDigitControlUseCase;
import com.dglapps.nibverifier.application.digitcontrol.DigitControlResponse;
import com.dglapps.nibverifier.application.verification.VerifyNIBUseCase;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NIBVerifierFrame extends JFrame implements KeyListener, ActionListener {

  private static final long serialVersionUID = -6564074112664425080L;

  private final JTextField bankInput;
  private final JTextField agencyInput;
  private final JTextField accountInput;
  private final JTextField controlInput;
  private final JButton nibCheck;
  private final JButton nibClear;
  private final JLabel nibResult;

  private final VerifyNIBUseCase verifyNIBUseCase;
  private final CalculateDigitControlUseCase calculateDigitControlUseCase;

  public NIBVerifierFrame() {
    super();

    verifyNIBUseCase = new VerifyNIBUseCase();
    calculateDigitControlUseCase = new CalculateDigitControlUseCase();

    this.setTitle("Verificador NIB");

    final JPanel panel = new JPanel(new GridBagLayout());
    final GridBagConstraints gbc = new GridBagConstraints();

    final JPanel nib = new JPanel(new FlowLayout());

    bankInput = new JTextField();
    bankInput.setColumns(4);
    agencyInput = new JTextField();
    agencyInput.setColumns(4);
    accountInput = new JTextField();
    accountInput.setColumns(11);
    controlInput = new JTextField();
    controlInput.setColumns(2);

    nib.add(bankInput);
    nib.add(agencyInput);
    nib.add(accountInput);
    nib.add(controlInput);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.weighty = 4;
    gbc.weightx = 4;
    gbc.fill = GridBagConstraints.BOTH;
    panel.add(nib, gbc);

    nibCheck = new JButton("Comprobar");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.ipadx = 0;
    gbc.weighty = 2;
    gbc.weightx = 2;
    gbc.fill = GridBagConstraints.BOTH;
    panel.add(nibCheck, gbc);

    nibClear = new JButton("Limpiar");
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weighty = 2;
    gbc.weightx = 2;
    gbc.fill = GridBagConstraints.BOTH;
    panel.add(nibClear, gbc);

    nibResult = new JLabel();
    nibResult.setText("Introduce NIB");
    nibResult.validate();
    nibResult.setHorizontalAlignment(JLabel.CENTER);
    nibResult.setFont(new Font("Sans-Serif", Font.BOLD, 14));

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weighty = 20;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.BOTH;
    panel.add(nibResult, gbc);

    bankInput.addKeyListener(this);
    agencyInput.addKeyListener(this);
    accountInput.addKeyListener(this);
    controlInput.addKeyListener(this);
    nibCheck.addActionListener(this);
    nibClear.addActionListener(this);

    this.setContentPane(panel);
    this.pack();
    this.setMinimumSize(this.getPreferredSize());

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void keyPressed(final KeyEvent keyEvent) {
    if ((keyEvent.getKeyCode() == KeyEvent.VK_V) && ((keyEvent.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
      keyEvent.consume();
    }
  }

  @Override
  public void keyReleased(final KeyEvent keyEvent) {
  }

  @Override
  public void keyTyped(final KeyEvent keyEvent) {
    final char c = keyEvent.getKeyChar();
    // Permitimos solo dígitos
    if (!Character.isDigit(c)) {
      keyEvent.consume();
    }
    final JTextField textField = (JTextField) keyEvent.getComponent();
    if (textField.getText().length() == textField.getColumns() - 1) {
      keyEvent.getComponent().transferFocus();
    } else if (textField.getText().length() >= textField.getColumns()) {
      keyEvent.consume();
    }
  }

  @Override
  public void actionPerformed(final ActionEvent e) {
    final Object o = e.getSource();
    if (o == nibCheck) {
      validateNib();
    } else if (o == nibClear) {
      clearScreen();
    }
    this.repaint();
  }

  private void validateNib() {
    final String bank = bankInput.getText();
    final String agency = agencyInput.getText();
    final String account = accountInput.getText();
    final String digitControl = controlInput.getText();

    final String nib = bank + agency + account + digitControl;

    if (verifyNIBUseCase.execute(nib).valid()) {
      nibResult.setText("NIB correcto");
      nibResult.setForeground(Color.GREEN);
    } else {
      DigitControlResponse response = calculateDigitControlUseCase.execute(nib);
      nibResult.setText("NIB incorrecto. Esperado " + response.digitControl());
      nibResult.setForeground(Color.RED);
    }
  }

  private void clearScreen() {
    bankInput.setText("");
    agencyInput.setText("");
    accountInput.setText("");
    controlInput.setText("");
    nibResult.setText("Introduce NIB");
    nibResult.setForeground(Color.BLACK);
  }
}
