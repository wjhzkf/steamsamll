package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdom2.JDOMException;

import bean.SteamGame;

public class ShowOrEditSteamGameUI extends JFrame {

	public AutoSellerUI frame;
	private JPanel contentPane;
	public JTextField gameLinkText;
	public JTextField gameXPathText;
	public JTextField gameNameText;
	private JButton EditGameButton;
	private JButton clearButton;
	private JLabel infoLabel;
	public JTextField infoText;
	
	public Map<String, SteamGame> steamGames;
	public SteamGame steamGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public ShowOrEditSteamGameUI(AutoSellerUI frame) {
		this.frame = frame;
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
		
		EditGameButton = new JButton("确认修改游戏");
		EditGameButton.setBounds(166, 228, 126, 42);
		contentPane.add(EditGameButton);
		EditGameButton.addActionListener(new ShowOrEditSteamGameListener(this));
		
		clearButton = new JButton("清空输入框");
		clearButton.setBounds(404, 228, 126, 42);
		contentPane.add(clearButton);
		clearButton.addActionListener(new ShowOrEditSteamGameListener(this));
		
		infoLabel = new JLabel("状态栏：");
		infoLabel.setBounds(10, 20, 103, 37);
		contentPane.add(infoLabel);
		
		infoText = new JTextField();
		infoText.setForeground(Color.ORANGE);
		infoText.setFont(new Font("微软雅黑", Font.BOLD, 18));
		infoText.setEditable(false);
		infoText.setColumns(10);
		infoText.setBounds(137, 10, 463, 52);
		infoText.setText("！！查看或者修改游戏的对应信息！！");
		contentPane.add(infoText);
		
		//添加关闭窗口的监听事件，即保存修改的信息
		addWindowListener(new ShowOrEditSteamGameCloseListener(this));
		
		//初始化界面
		if("".equals(frame.editSteamGameName)||frame.editSteamGameName==null){
			this.infoText.setText("！！请选择有效的游戏！！");
			return;
		}
		this.steamGame = frame.steamGames.get(frame.editSteamGameName);
		this.gameLinkText.setText(this.steamGame.getLink());
		this.gameNameText.setText(this.steamGame.getName());
		this.gameXPathText.setText(this.steamGame.getXpath());
		
		this.frame.setEnabled(false);
	}

}
