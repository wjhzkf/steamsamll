package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class AddSteamGameUI extends JFrame {

	public AutoSellerUI frame;
	private JPanel contentPane;
	public JTextField gameLinkText;
	public JTextField gameXPathText;
	public JTextField gameNameText;
	private JButton addGameButton;
	private JButton clearButton;
	private JLabel infoLabel;
	public JTextField infoText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public AddSteamGameUI(AutoSellerUI frame) {
		this.frame = frame;
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 610, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel gameLinkLabel = new JLabel("游戏地址：");
		gameLinkLabel.setBounds(10, 74, 103, 37);
		contentPane.add(gameLinkLabel);
		
		JLabel GameXPathLabel = new JLabel("加入购物车xpath：");
		GameXPathLabel.setBounds(10, 126, 117, 37);
		contentPane.add(GameXPathLabel);
		
		JLabel GameNameLabel = new JLabel("淘宝商品名称：");
		GameNameLabel.setBounds(10, 173, 103, 37);
		contentPane.add(GameNameLabel);
		
		gameLinkText = new JTextField();
		gameLinkText.setBounds(137, 72, 463, 42);
		contentPane.add(gameLinkText);
		gameLinkText.setColumns(10);
		
		gameXPathText = new JTextField();
		gameXPathText.setColumns(10);
		gameXPathText.setBounds(137, 124, 463, 42);
		contentPane.add(gameXPathText);
		
		gameNameText = new JTextField();
		gameNameText.setColumns(10);
		gameNameText.setBounds(137, 176, 463, 42);
		contentPane.add(gameNameText);
		
		addGameButton = new JButton("确认添加游戏");
		addGameButton.setBounds(166, 228, 126, 42);
		contentPane.add(addGameButton);
		addGameButton.addActionListener(new AddSteamGameListener(this));
		
		clearButton = new JButton("清空输入框");
		clearButton.setBounds(404, 228, 126, 42);
		contentPane.add(clearButton);
		clearButton.addActionListener(new AddSteamGameListener(this));
		
		infoLabel = new JLabel("加入购物车按钮Xpath格式：");
		infoLabel.setBounds(10, 20, 103, 37);
		contentPane.add(infoLabel);
		
		infoText = new JTextField();
		infoText.setForeground(Color.ORANGE);
		infoText.setFont(new Font("微软雅黑", Font.BOLD, 18));
		infoText.setEditable(false);
		infoText.setColumns(10);
		infoText.setBounds(137, 10, 463, 52);
		infoText.setText("确保按钮xpath准确！");
		contentPane.add(infoText);
		this.frame.setEnabled(false);
		addWindowListener(new AllUICloseListener(this.frame));
	}

}
