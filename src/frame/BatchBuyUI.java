package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Game;
import bean.SteamGame;

public class BatchBuyUI extends JFrame {

	
	public AutoSellerUI frame;
	public Game game;
	public SteamGame steamGame;
	private JPanel contentPane;
	public JTextField info_gameName_text;
	public JTextField info_default_account_text;
	public JTextField info_default_email_text;
	public JTextField info_buy_process_Text;
	public boolean closeDriverTag = false;
	public JTextField buyNumText;
	public JButton confirmBuyButton;
	public JButton confirmToWareButton;
	public JButton cancel_button;
	public JButton buyMyselfButton;
	public JButton jumpBuyMyselfButton;
	public JTextField hasBuyNumText;
	/**
	 * Create the frame.
	 */
	public BatchBuyUI(AutoSellerUI frame) {
		setVisible(true);
		setLocation(150,150);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setPreferredSize(new Dimension(500, 420));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("购买信息确认"));
		
		JLabel info_gameName_label = new JLabel("充值信息：");
		info_gameName_label.setBounds(26, 22, 116, 46);
		panel.add(info_gameName_label);
		
		JLabel info_default_account_label = new JLabel("当前账户：");
		info_default_account_label.setBounds(26, 120, 116, 46);
		panel.add(info_default_account_label);
		
		
		JLabel info_default_email_label = new JLabel("默认邮箱：");
		info_default_email_label.setBounds(26, 72, 116, 46);
		panel.add(info_default_email_label);
		
		
		info_gameName_text = new JTextField();
		info_gameName_text.setEditable(false);
		info_gameName_text.setBounds(114, 30, 360, 32);
		panel.add(info_gameName_text);
		info_gameName_text.setColumns(10);
		
		
		info_default_account_text = new JTextField();
		info_default_account_text.setEditable(false);
		info_default_account_text.setColumns(10);
		info_default_account_text.setBounds(114, 128, 360, 32);
		panel.add(info_default_account_text);
		
		
		info_default_email_text = new JTextField();
		info_default_email_text.setEditable(false);
		info_default_email_text.setColumns(10);
		info_default_email_text.setBounds(114, 78, 360, 32);
		panel.add(info_default_email_text);
		
		
		info_buy_process_Text = new JTextField();
		info_buy_process_Text.setForeground(new Color(255, 127, 80));
		info_buy_process_Text.setFont(new Font("微软雅黑", Font.BOLD, 15));
		info_buy_process_Text.setEditable(false);
		info_buy_process_Text.setColumns(10);
		info_buy_process_Text.setBounds(114, 256, 360, 46);
		panel.add(info_buy_process_Text);
		
		JLabel info_buy_process_label = new JLabel("购买进度：");
		info_buy_process_label.setBounds(26, 257, 94, 46);
		panel.add(info_buy_process_label);
		this.info_buy_process_Text.setText("");
		
		
		cancel_button = new JButton("取消");
		cancel_button.setBounds(343, 305, 130, 46);
		panel.add(cancel_button);
		cancel_button.addActionListener(new BatchBuyListener(this, frame));
		
		confirmBuyButton = new JButton("确认并购买");
		confirmBuyButton.setBounds(36, 305, 137, 46);
		panel.add(confirmBuyButton);
		
		JLabel buyNumLabel = new JLabel("购买个数：");
		buyNumLabel.setBounds(26, 166, 130, 46);
		panel.add(buyNumLabel);
		
		buyNumText = new JTextField();
		buyNumText.setColumns(10);
		buyNumText.setBounds(114, 176, 360, 32);
		panel.add(buyNumText);
		buyNumText.setText("1");
		
		JLabel label = new JLabel("购买状态：");
		label.setBounds(26, 206, 94, 46);
		panel.add(label);
		
		hasBuyNumText = new JTextField();
		hasBuyNumText.setForeground(new Color(255, 127, 80));
		hasBuyNumText.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		hasBuyNumText.setEditable(false);
		hasBuyNumText.setText("");
		hasBuyNumText.setColumns(10);
		hasBuyNumText.setBounds(114, 214, 360, 32);
		panel.add(hasBuyNumText);
		
		confirmToWareButton = new JButton("确认并加入仓库");
		confirmToWareButton.setBounds(192, 305, 130, 46);
		panel.add(confirmToWareButton);
		
		buyMyselfButton = new JButton("为自己购买");
		buyMyselfButton.setBounds(85, 361, 137, 46);
		panel.add(buyMyselfButton);
		buyMyselfButton.addActionListener(new BatchBuyListener(this, frame));
		
		jumpBuyMyselfButton = new JButton("跳区购买");
		jumpBuyMyselfButton.setBounds(267, 361, 137, 46);
		panel.add(jumpBuyMyselfButton);
		jumpBuyMyselfButton.addActionListener(new BatchBuyListener(this, frame));
		
		confirmToWareButton.addActionListener(new BatchBuyListener(this, frame));
		confirmBuyButton.addActionListener(new BatchBuyListener(this, frame));
		
		//内容初始化
		this.frame = frame;
		setText();
		this.frame.setEnabled(false);
		addWindowListener(new AllUICloseListener(this.frame));
	}
	
	public void setText(){
		//启动界面后初始化
		String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
		String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
		String defaultEmail = frame.default_email_text_sure.getText();
		String steamGameName = ((CheckValue)frame.gamesComboBox.getSelectedItem()).value;
		
		this.steamGame = frame.steamGames.get(steamGameName);			//获取传入的游戏
		this.info_gameName_text.setText(steamGameName);
		this.info_default_account_text.setText(defaultAccountID);
		this.info_default_email_text.setText(defaultEmail);
	}
}
