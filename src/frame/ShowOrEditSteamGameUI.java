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
		
		JLabel gameLinkLabel = new JLabel("��Ϸ��ַ��");
		gameLinkLabel.setBounds(10, 74, 103, 37);
		contentPane.add(gameLinkLabel);
		
		JLabel GameXPathLabel = new JLabel("���빺�ﳵxpath��");
		GameXPathLabel.setBounds(10, 126, 117, 37);
		contentPane.add(GameXPathLabel);
		
		JLabel GameNameLabel = new JLabel("�Ա���Ʒ���ƣ�");
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
		
		EditGameButton = new JButton("ȷ���޸���Ϸ");
		EditGameButton.setBounds(166, 228, 126, 42);
		contentPane.add(EditGameButton);
		EditGameButton.addActionListener(new ShowOrEditSteamGameListener(this));
		
		clearButton = new JButton("��������");
		clearButton.setBounds(404, 228, 126, 42);
		contentPane.add(clearButton);
		clearButton.addActionListener(new ShowOrEditSteamGameListener(this));
		
		infoLabel = new JLabel("״̬����");
		infoLabel.setBounds(10, 20, 103, 37);
		contentPane.add(infoLabel);
		
		infoText = new JTextField();
		infoText.setForeground(Color.ORANGE);
		infoText.setFont(new Font("΢���ź�", Font.BOLD, 18));
		infoText.setEditable(false);
		infoText.setColumns(10);
		infoText.setBounds(137, 10, 463, 52);
		infoText.setText("�����鿴�����޸���Ϸ�Ķ�Ӧ��Ϣ����");
		contentPane.add(infoText);
		
		//��ӹرմ��ڵļ����¼����������޸ĵ���Ϣ
		addWindowListener(new ShowOrEditSteamGameCloseListener(this));
		
		//��ʼ������
		if("".equals(frame.editSteamGameName)||frame.editSteamGameName==null){
			this.infoText.setText("������ѡ����Ч����Ϸ����");
			return;
		}
		this.steamGame = frame.steamGames.get(frame.editSteamGameName);
		this.gameLinkText.setText(this.steamGame.getLink());
		this.gameNameText.setText(this.steamGame.getName());
		this.gameXPathText.setText(this.steamGame.getXpath());
		
		this.frame.setEnabled(false);
	}

}
