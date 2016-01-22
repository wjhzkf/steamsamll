package frame;

import java.awt.Font;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class ShowXpathUI extends JFrame {

	public JPanel contentPane;
	public JTextField xpathTextField;
	public AutoSellerUI frame;
	public Map<String, String> buySteamGameXpath;
	public Map<String, String> WalletXpath;
	public JComboBox xpathComboBox;
	public JLabel infoLabel;
	//标记要查看的是SteamGame的还是Wallet的Xpath
	public String tag;
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
	}

	/**
	 * Create the frame.
	 */
	public ShowXpathUI(AutoSellerUI frame, String tag) {
		this.tag = tag;
		this.frame = frame;
//		this.buySteamGameXpath = frame.buySteamGameXpath;
//		this.WalletXpath = frame.WalletXpath;
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 634, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		xpathComboBox = new JComboBox();
		xpathComboBox.setForeground(Color.RED);
		xpathComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		xpathComboBox.setBounds(139, 21, 299, 36);
		contentPane.add(xpathComboBox);
		xpathComboBox.addItemListener(new ShowXpathListener(this));
		
		JButton deleteXpathButton = new JButton("删除xpath");
		deleteXpathButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		deleteXpathButton.setBounds(448, 21, 150, 36);
		contentPane.add(deleteXpathButton);
		
		infoLabel = new JLabel("New label");
		infoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		infoLabel.setBounds(0, 21, 143, 36);
		contentPane.add(infoLabel);
		
		xpathTextField = new JTextField();
		xpathTextField.setForeground(Color.RED);
		xpathTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		xpathTextField.setBounds(139, 67, 459, 36);
		contentPane.add(xpathTextField);
		xpathTextField.setColumns(10);
		
		if("SteamGame".equals(this.tag)){
			showBuySteamGameXpath();			
		}else if("Wallet".equals(this.tag)){
			showWalletXpath();
		}
	}

	/*
	 * 初始化BuySteamGameXpath的下拉菜单
	 */
	public void showBuySteamGameXpath(){
		this.infoLabel.setText("SteamGameXpath：");
		Iterator it = this.buySteamGameXpath.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = (Entry)it.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			
			this.xpathComboBox.addItem(key);
		}
	}
	/*
	 * 初始化WalletXpath的下拉菜单
	 */
	public void showWalletXpath(){
		this.infoLabel.setText("WalletXpath：");
		Iterator it = this.WalletXpath.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = (Entry)it.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			
			this.xpathComboBox.addItem(key);
		}
	}
	
}
