package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlertUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private AlertUI alertUI;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlertUI frame = new AlertUI();
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
	public AlertUI() {
		this.alertUI = this;
		setResizable(false);
		setVisible(true);
		setLocation(400,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 441, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 417, 170);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 18));
		textField.setEditable(false);
		textField.setBounds(10, 10, 390, 46);
		panel.add(textField);
		textField.setColumns(10);
		textField.setText("£°£°£°«Î—°‘Ò”––ßµƒ”Œœ∑°¢’Àªß∫Õ” œ‰£°£°£°");
		
		JButton btnNewButton = new JButton("πÿ±’");
		btnNewButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				alertUI.dispose();
			}
		});
		btnNewButton.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 16));
		btnNewButton.setBounds(129, 89, 152, 46);
		panel.add(btnNewButton);
		
	}
}
