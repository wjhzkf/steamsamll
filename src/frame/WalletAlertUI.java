package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class WalletAlertUI extends JFrame {

	private JPanel contentPane;
	public JTextField warningField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WalletAlertUI frame = new WalletAlertUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WalletAlertUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 464, 231);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		warningField = new JTextField();
		warningField.setForeground(Color.RED);
		warningField.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 16));
		warningField.setEditable(false);
		warningField.setBounds(44, 30, 357, 74);
		contentPane.add(warningField);
		warningField.setColumns(10);
		
		JButton sureButton = new JButton("»∑»œ≥‰÷µ");
		sureButton.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		sureButton.setBounds(44, 130, 155, 50);
		contentPane.add(sureButton);
		sureButton.addActionListener(new WalletAlertListener(this));
		
		JButton cancelButton = new JButton("»°œ˚");
		cancelButton.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		cancelButton.setBounds(246, 130, 155, 50);
		contentPane.add(cancelButton);
		cancelButton.addActionListener(new WalletAlertListener(this));
	}
}
