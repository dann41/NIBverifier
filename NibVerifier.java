import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NibVerifier extends JFrame implements KeyListener, ActionListener {
	
	private JTextField nibInput1;
	private JTextField nibInput2;
	private JTextField nibInput3;
	private JTextField nibInput4;
	private JButton nibCheck;
	private JButton nibClear;
	private JLabel nibResult;
	
	private static final long serialVersionUID = -6564074112664425080L;
	
	public NibVerifier(){
		super();
		this.setTitle("Verificador NIB");
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel nib = new JPanel(new FlowLayout());
		
		nibInput1 = new JTextField();
		nibInput1.setColumns(4);
		nibInput2 = new JTextField();
		nibInput2.setColumns(4);
		nibInput3 = new JTextField();
		nibInput3.setColumns(11);
		nibInput4 = new JTextField();
		nibInput4.setColumns(2);

		nib.add(nibInput1);
		nib.add(nibInput2);
		nib.add(nibInput3);
		nib.add(nibInput4);
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=2;
		gbc.weighty=4;
		gbc.weightx=4;
		gbc.fill= GridBagConstraints.BOTH;
		panel.add(nib, gbc);
		
		nibCheck = new JButton("Comprobar");
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.ipadx=0;
		gbc.weighty=2;
		gbc.weightx=2;
		gbc.fill= GridBagConstraints.BOTH;
		panel.add(nibCheck, gbc);
		
		nibClear = new JButton("Limpiar");
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.weighty=2;
		gbc.weightx=2;
		gbc.fill= GridBagConstraints.BOTH;
		panel.add(nibClear, gbc);
		
		nibResult = new JLabel();
		nibResult.setText("Introduce NIB");
		nibResult.validate();
		nibResult.setHorizontalAlignment(JLabel.CENTER);
		nibResult.setFont(new Font("Sans-Serif", Font.BOLD,14));
		
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.weighty=20;
		gbc.gridwidth=2;
		gbc.fill= GridBagConstraints.BOTH;
		panel.add(nibResult, gbc);
		
		nibInput1.addKeyListener(this);
		nibInput2.addKeyListener(this);
		nibInput3.addKeyListener(this);
		nibInput4.addKeyListener(this);
		nibCheck.addActionListener(this);
		nibClear.addActionListener(this);

		this.setContentPane(panel);
		this.pack();
		this.setMinimumSize(this.getPreferredSize());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String []args){
		NibVerifier nv = new NibVerifier();
		nv.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if ((arg0.getKeyCode() == KeyEvent.VK_V) && ((arg0.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
			arg0.consume();
        }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		// Permitimos solo dígitos
		if(!Character.isDigit(c)){
			arg0.consume();
		}
		JTextField textField = (JTextField) arg0.getComponent();
		if(textField.getText().length()==textField.getColumns()-1){
			arg0.getComponent().transferFocus();
		}
		else if(textField.getText().length()>=textField.getColumns()){
			arg0.consume();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==nibCheck){
		
			String nib1 = nibInput1.getText();
			String nib2 = nibInput2.getText();
			String nib3 = nibInput3.getText();
			String nib4 = nibInput4.getText();
			
			String nib = nib1+nib2+nib3+nib4;
			if(checkNib(nib)){
				System.out.println(nib);
				nibResult.setText("NIB correcto");
				nibResult.setForeground(Color.GREEN);
			} else {
				nibResult.setText("NIB incorrecto");
				nibResult.setForeground(Color.RED);
			}
		} else if(o==nibClear){
			nibInput1.setText("");
			nibInput2.setText("");
			nibInput3.setText("");
			nibInput4.setText("");
			nibResult.setText("Introduce NIB");
			nibResult.setForeground(Color.BLACK);
		}
		this.repaint();
	}
	
	public boolean checkNib(String nib){
		if(nib.length()==21){
	        char[] toValidate = nib.substring(0, 19).toCharArray();
	        Integer checkDigit = Integer.valueOf(nib.substring(19));
	        Integer[] wi = { 73, 17, 89, 38, 62, 45, 53, 15, 50, 5, 49, 34, 81, 76, 27, 90, 9, 30, 3 };
	        Integer sum = 0;
	        for (int i = 0; i < 19; i++) {
	            sum += Character.digit(toValidate[i], 10) * wi[i];
	        }
	        return checkDigit.equals(98 - (sum % 97));		
		}
		return false;
	}

}
