package frame;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdom2.JDOMException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.Game;
import bean.SteamGame;


public class AutoSellerUI extends JFrame {

	public List games;
	public Map<String, SteamGame> steamGames;
	public Game game;
	public SteamGame steamGame;
	
	//checkbox
	public MyComboBox gamesComboBox;
	public JPanel panel_8;
	
	//BuySteamGameXpath���ļ���ַ
	public String buySteamGameXpathFilePath;
	
	//����BuySteamGameXpath�Ĺ�ϣ��
	public Map<String, String> buySteamGameXpath;
	//����WalletXpath�Ĺ�ϣ��
	public Map<String, String> WalletXpath;
	//�������ִ����ɺ��Ƿ�ر�������ı���
	public boolean exitBrowserAfterRunning = true;
	//�������й������Ƿ���ʾ������ı���
	public boolean showBrowserWhenRunning = true;
	/*
	 * ����steam��Ϸ��Ҫ������˻�������
	 */
	public Map<String, String> buyGameAccounts;
	public List<String> buyGameAccountID;
	public String editSteamGameName = "�������Ϸ";
	
	String str[] = {"��ѡ����Ϸ"}; 
	String str2[] = {"��ѡ���˻�"};
	
	public JPanel MainPanel;
	
	public JTabbedPane tabbedPane;
	//�˻�ҳ��Ԫ��
	public JTextField default_id_text_01;
	public JTextField default_pw_text_01;
	public JTextField default_id_text_02;
	public JTextField default_pw_text_02;
	public JTextField default_id_text_03;
	public JTextField default_pw_text_03;
	public JTextField default_id_text_04;
	public JTextField default_id_text_05;
	public JTextField default_id_text_06;
	public JTextField default_pw_text_04;
	public JTextField default_pw_text_05;
	public JTextField default_pw_text_06;
	public JTextField default_pw_text_sure;
	
	
	//��Ȩҳ��Ԫ��
	public JTextField Session_key_text;
	public JTextField Session_text;
	
		
	//��ѡ��ť��
	public ButtonGroup group;
	JRadioButton radioButton_1 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_2 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_3 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_4 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_5 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_6 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_7 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_8 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_9 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_10 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_11 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_12 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_13 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_14 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_15 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_16 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_17 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_18 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_19 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_20 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_21 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_22 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_23 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_24 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_25 = new JRadioButton("�������Ϸ");
	JRadioButton radioButton_26 = new JRadioButton("�������Ϸ");
	
	//����ҳ��Ԫ��
	DefaultTableModel tableModel = new DefaultTableModel(0,9);
	JTable table;
	public JTextField table_gameName_text = new JTextField(37);
	public JTextField table_buyer_id_text = new JTextField(37);
	public JTextField table_buyer_email_text = new JTextField(37);
	public JTextField table_buyer_message_text = new JTextField(37);
	public JComboBox account_type_combo = new JComboBox(str2);
	public JTextField default_pw_text_sure2;
	public JTextField codeDefaultAccountText;
	public JTextField statusText;
	public JTextArea codesTextArea;
	public JTextArea codesStatusTextArea;
	public JTextArea steamLogTextArea;
	
	//���������˵�
	JComboBox accountsComboBox;
//	JComboBox gamesComboBox;
	public JComboBox codeAccountsComboBox;
	public JComboBox countrySelectBox;
	public JComboBox uniGamesComboBox;
	/*
	 * Code��֤��ֵ��Ҫ���������
	 */
	public Map<String,String> accounts = new HashMap<String,String>();
	public List<String> accountIDs = new ArrayList<String>();
	public JTextField default_email_text_sure;
	public JTextField default_id_text_07;
	public JTextField default_id_text_08;
	public JTextField default_id_text_09;
	public JTextField default_id_text_10;
	public JTextField default_pw_text_07;
	public JTextField default_pw_text_08;
	public JTextField default_pw_text_09;
	public JTextField default_pw_text_10;
	
	//�������ó���RadioButton
	public JRadioButton closeBroRadioButton;
	public JRadioButton dontCloseRadioButton;
	public JRadioButton showBroRadioButton;
	public JRadioButton dontShowRadioButton;
	public JRadioButton autoSendRadioButton;
	public JRadioButton manualSendRadioButton;
	public JTextField startTimeField;
	public JTextField endTimeField;
	public JTextField table_gameNum_text;
	public JTextField table_seller_message_text;
	public JTextField removeLinkTextField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoSellerUI frame = new AutoSellerUI();
					frame.setLocation(0,0);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws JSONException 
	 */
	public AutoSellerUI() throws JDOMException, IOException, JSONException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		MainPanel = new JPanel();
		MainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		MainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(MainPanel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		MainPanel.add(tabbedPane, BorderLayout.NORTH);
		/*
		 * ***************��Ȩ��ȡSessionKey��Panel********************
		 */
		
		
		/*
		 * *************������Ϸ**************
		 */
		
		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(850,580));
		tabbedPane.addTab("������Ϸ", null, panel_7, null);
		panel_7.setLayout(null);
		
		//ȷ����Ϣ
		 panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "ȷ����Ϣ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(10, 21, 849, 225);
		panel_7.add(panel_8);
		panel_8.setLayout(null);
		//����
		JPanel panel_set = new JPanel();
		panel_set.setBorder(new TitledBorder(null, "��Ϣ����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_set.setBounds(10, 280, 849, 100);
		panel_7.add(panel_set);
		panel_set.setLayout(null);
		//�˻�����
		JButton btnAccountSet = new JButton("�˻�����");
		btnAccountSet.setFont(new Font("΢���ź�", Font.BOLD, 14));
		btnAccountSet.setBounds(200,31, 162, 47);
		panel_set.add(btnAccountSet);
		btnAccountSet.addActionListener(new MyListener(this));
		//��Ϸ����
		JButton btnGameSet = new JButton("��Ϸ����");
		btnGameSet.setFont(new Font("΢���ź�", Font.BOLD, 14));
		btnGameSet.setBounds(497,31, 162, 47);
		panel_set.add(btnGameSet);
		btnGameSet.addActionListener(new MyListener(this));
		//����
//		JButton testbtn = new JButton("����1");
//		testbtn.setFont(new Font("΢���ź�", Font.BOLD, 14));
//		testbtn.setBounds(50,31, 162, 47);
//		panel_set.add(testbtn);
//		testbtn.addActionListener(new MyListener(this));
		
		JLabel lblNewLabel_1 = new JLabel("�����˻���");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 25, 94, 36);
		panel_8.add(lblNewLabel_1);
		
		String str2[] = {"��ѡ���˻�"};
		accountsComboBox = new JComboBox();
		accountsComboBox.setForeground(Color.RED);
		accountsComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		accountsComboBox.setBounds(114, 24, 249, 41);
		panel_8.add(accountsComboBox);
//		accountsComboBox.addItemListener(new MyListener(this));
		
		JLabel default_email_label = new JLabel("�ջ����䣺");
		default_email_label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		default_email_label.setForeground(new Color(128, 0, 0));
		default_email_label.setBounds(373, 25, 94, 36);
		panel_8.add(default_email_label);
		
		default_email_text_sure = new JTextField("slickdeal@qq.com");
		default_email_text_sure.setForeground(Color.RED);
		default_email_text_sure.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		default_email_text_sure.setBounds(477, 24, 185, 41);
		panel_8.add(default_email_text_sure);
		default_email_text_sure.setColumns(10);
		
		JLabel label_4 = new JLabel("������Ϸ��");
		label_4.setForeground(new Color(128, 0, 0));
		label_4.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label_4.setBounds(10, 92, 94, 36);
		panel_8.add(label_4);
		
//		gamesComboBox = new JComboBox();
//		gamesComboBox.setForeground(Color.RED);
//		gamesComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 16));
//		gamesComboBox.setBounds(114, 88, 548, 47);
//		panel_8.add(gamesComboBox);
//		gamesComboBox.addItemListener(new MyListener(this));
		
		

		//checkbox
		gamesComboBox=new MyComboBox();
		gamesComboBox.setFont(new Font("΢���ź�", Font.BOLD, 14));
		gamesComboBox.setBounds(114, 88, 548, 47);
		panel_8.add(gamesComboBox);
		gamesComboBox.setRenderer(new CheckListCellRenderer());
		
		
		JButton btnNewButton_1 = new JButton("ȷ�ϲ�����");
		btnNewButton_1.setFont(new Font("΢���ź�", Font.BOLD, 14));
		btnNewButton_1.setBounds(677, 20, 162, 47);
//		panel_8.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new MyListener(this));
		
		JButton button = new JButton("�����ɹ�");
		button.setFont(new Font("΢���ź�", Font.BOLD, 14));
		button.setBounds(677,38, 162, 47);
		panel_8.add(button);
		
		JLabel label_8 = new JLabel("ѡ����ң�");
		label_8.setForeground(new Color(128, 0, 0));
		label_8.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label_8.setBounds(10, 159, 94, 36);
		panel_8.add(label_8);
		
		String[] countries = {"����˹", "�ڿ���", "���", "����", "�й�", "����", "�ձ�", "ӡ��������", "��������"};
		countrySelectBox = new JComboBox(countries);
		countrySelectBox.setForeground(Color.RED);
		countrySelectBox.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		countrySelectBox.setBounds(114, 154, 249, 47);
		panel_8.add(countrySelectBox);
		
		JButton buyMyselfButton = new JButton("Ϊ�Լ�����");
		buyMyselfButton.setFont(new Font("΢���ź�", Font.BOLD, 14));
		buyMyselfButton.setBounds(677, 154, 162, 47);
		panel_8.add(buyMyselfButton);
		
		JLabel label_9 = new JLabel("������Ϸ��");
		label_9.setForeground(new Color(128, 0, 0));
		label_9.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label_9.setBounds(10, 223, 94, 36);
//		panel_8.add(label_9);                              /////////////////////////////////////////////����������Ϸ                                               
		
		uniGamesComboBox = new JComboBox();
		uniGamesComboBox.setForeground(Color.RED);
		uniGamesComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		uniGamesComboBox.setBounds(114, 218, 249, 47);
//		panel_8.add(uniGamesComboBox);                      //////////////////////////////////////////////
		uniGamesComboBox.addItemListener(new MyListener(this));
		
		JButton jumpBuyButton = new JButton("��������");
		jumpBuyButton.setFont(new Font("΢���ź�", Font.BOLD, 14));
		jumpBuyButton.setBounds(677, 218, 162, 47);
//		panel_8.add(jumpBuyButton);                        //////////////////////////////////////////////������������
		
		JLabel label_10 = new JLabel("�Ƴ�����λ�ã�");
		label_10.setForeground(new Color(128, 0, 0));
		label_10.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label_10.setBounds(373, 223, 94, 36);
//		panel_8.add(label_10);                             /////////////////////////////////////////////
		
		removeLinkTextField = new JTextField("2");
		removeLinkTextField.setForeground(Color.RED);
		removeLinkTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		removeLinkTextField.setColumns(10);
		removeLinkTextField.setBounds(477, 221, 185, 41);
//		panel_8.add(removeLinkTextField);                 /////////////////////////////////////////////////
		jumpBuyButton.addActionListener(new MyListener(this));
		buyMyselfButton.addActionListener(new MyListener(this));
		
		countrySelectBox.addItemListener(new MyListener(this));
		
		/*
		 * ***************************�˻�����*****************************
		 */
		JPanel AccountSet_Panel = new JPanel();
		AccountSet_Panel.setPreferredSize(new Dimension(850,580));
//		tabbedPane.addTab("�˻�����", null, AccountSet_Panel, null);                  /////////////////////////////////�����˻�����
		AccountSet_Panel.setLayout(null);
		button.addActionListener(new MyListener(this));
		
		default_pw_text_sure = new JTextField();
		default_pw_text_sure.setBounds(332, 21, 186, 34);
//		panel_1.add(default_pw_text_sure);
		default_pw_text_sure.setVisible(false);
		default_pw_text_sure.setForeground(Color.RED);
		default_pw_text_sure.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		default_pw_text_sure.setEditable(false);
		default_pw_text_sure.setColumns(10);
		default_pw_text_sure.setText("");
		
		JLabel default_pw_label_sure = new JLabel("���룺");
		default_pw_label_sure.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_label_sure.setForeground(new Color(128, 0, 0));
		default_pw_label_sure.setBounds(281, 21, 86, 34);
		default_pw_label_sure.setVisible(false);
		
		JPanel accountSetPanel = new JPanel();
		accountSetPanel.setBorder(new TitledBorder(null, "\u8D26\u6237\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		accountSetPanel.setBounds(0, 10, 859, 560);
		AccountSet_Panel.add(accountSetPanel);
		accountSetPanel.setLayout(null);
		
		JLabel default_id_label_01 = new JLabel("�ʻ� 1��");
		default_id_label_01.setBounds(84, 10, 86, 34);
		accountSetPanel.add(default_id_label_01);
		default_id_label_01.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		JLabel default_id_label_02 = new JLabel("�˻� 2��");
		default_id_label_02.setBounds(84, 66, 86, 34);
		accountSetPanel.add(default_id_label_02);
		default_id_label_02.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		JLabel default_id_label_03 = new JLabel("�˻� 3��");
		default_id_label_03.setBounds(84, 123, 86, 34);
		accountSetPanel.add(default_id_label_03);
		default_id_label_03.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		JLabel default_id_label_04 = new JLabel("�˻� 4��");
		default_id_label_04.setBounds(84, 183, 86, 34);
		accountSetPanel.add(default_id_label_04);
		default_id_label_04.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		JLabel default_id_label_05 = new JLabel("�˻� 5��");
		default_id_label_05.setBounds(84, 240, 86, 34);
		accountSetPanel.add(default_id_label_05);
		default_id_label_05.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		JLabel default_id_label_06 = new JLabel("�˻� 6��");
		default_id_label_06.setBounds(84, 291, 86, 34);
		accountSetPanel.add(default_id_label_06);
		default_id_label_06.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		JLabel default_pw_label_01 = new JLabel("���룺");
		default_pw_label_01.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_label_01.setBounds(403, 10, 74, 34);
		accountSetPanel.add(default_pw_label_01);
		
		JLabel default_pw_label_02 = new JLabel("���룺");
		default_pw_label_02.setBounds(403, 66, 74, 34);
		accountSetPanel.add(default_pw_label_02);
		default_pw_label_02.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		JLabel default_pw_label_03 = new JLabel("���룺");
		default_pw_label_03.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_label_03.setBounds(403, 123, 86, 34);
		accountSetPanel.add(default_pw_label_03);
		
		JLabel default_pw_label_04 = new JLabel("���룺");
		default_pw_label_04.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_label_04.setBounds(403, 183, 86, 34);
		accountSetPanel.add(default_pw_label_04);
		
		JLabel default_pw_label_05 = new JLabel("���룺");
		default_pw_label_05.setBounds(403, 240, 86, 34);
		accountSetPanel.add(default_pw_label_05);
		default_pw_label_05.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		JLabel default_pw_label_06 = new JLabel("���룺");
		default_pw_label_06.setBounds(403, 291, 86, 34);
		accountSetPanel.add(default_pw_label_06);
		default_pw_label_06.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		
		default_id_text_01 = new JTextField("");
		default_id_text_01.setBounds(169, 11, 186, 34);
		accountSetPanel.add(default_id_text_01);
		default_id_text_01.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_01.setBackground(Color.WHITE);
		default_id_text_01.setColumns(10);
		
		default_id_text_02 = new JTextField("");
		default_id_text_02.setBounds(169, 67, 186, 34);
		accountSetPanel.add(default_id_text_02);
		default_id_text_02.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_02.setColumns(10);
		
		default_id_text_03 = new JTextField("");
		default_id_text_03.setBounds(169, 124, 186, 34);
		accountSetPanel.add(default_id_text_03);
		default_id_text_03.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_03.setColumns(10);
		
		default_id_text_04 = new JTextField("");
		default_id_text_04.setBounds(169, 184, 186, 34);
		accountSetPanel.add(default_id_text_04);
		default_id_text_04.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_04.setText((String) null);
		default_id_text_04.setColumns(10);
		
		default_id_text_05 = new JTextField("");
		default_id_text_05.setBounds(169, 241, 186, 34);
		accountSetPanel.add(default_id_text_05);
		default_id_text_05.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_05.setText((String) null);
		default_id_text_05.setColumns(10);
		
		default_id_text_06 = new JTextField("");
		default_id_text_06.setBounds(169, 292, 186, 34);
		accountSetPanel.add(default_id_text_06);
		default_id_text_06.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_06.setText((String) null);
		default_id_text_06.setColumns(10);
		
		default_pw_text_01 = new JTextField("");
		default_pw_text_01.setBounds(475, 11, 186, 34);
		accountSetPanel.add(default_pw_text_01);
		default_pw_text_01.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_01.setColumns(10);
		
		default_pw_text_02 = new JTextField("");
		default_pw_text_02.setBounds(475, 67, 186, 34);
		accountSetPanel.add(default_pw_text_02);
		default_pw_text_02.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_02.setColumns(10);
		
		default_pw_text_03 = new JTextField("");
		default_pw_text_03.setBounds(475, 124, 186, 34);
		accountSetPanel.add(default_pw_text_03);
		default_pw_text_03.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_03.setColumns(10);
		
		default_pw_text_04 = new JTextField("");
		default_pw_text_04.setBounds(475, 184, 186, 34);
		accountSetPanel.add(default_pw_text_04);
		default_pw_text_04.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_04.setText((String) null);
		default_pw_text_04.setColumns(10);
		
		default_pw_text_05 = new JTextField("");
		default_pw_text_05.setBounds(475, 241, 186, 34);
		accountSetPanel.add(default_pw_text_05);
		default_pw_text_05.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_05.setText((String) null);
		default_pw_text_05.setColumns(10);
		
		default_pw_text_06 = new JTextField("");
		default_pw_text_06.setBounds(475, 292, 186, 34);
		accountSetPanel.add(default_pw_text_06);
		default_pw_text_06.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_06.setText((String) null);
		default_pw_text_06.setColumns(10);
		
		JLabel default_id_label_07 = new JLabel("�˻� 7��");
		default_id_label_07.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_label_07.setBounds(84, 349, 86, 34);
		accountSetPanel.add(default_id_label_07);
		
		JLabel default_id_label_08 = new JLabel("�˻� 8��");
		default_id_label_08.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_label_08.setBounds(84, 403, 86, 34);
		accountSetPanel.add(default_id_label_08);
		
		JLabel default_id_label_09 = new JLabel("�˻� 9��");
		default_id_label_09.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_label_09.setBounds(84, 453, 86, 34);
		accountSetPanel.add(default_id_label_09);
		
		JLabel default_id_label_10 = new JLabel("�˻� 10��");
		default_id_label_10.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_label_10.setBounds(84, 505, 86, 34);
		accountSetPanel.add(default_id_label_10);
		
		default_id_text_07 = new JTextField("");
		default_id_text_07.setText((String) null);
		default_id_text_07.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_07.setColumns(10);
		default_id_text_07.setBounds(169, 350, 186, 34);
		accountSetPanel.add(default_id_text_07);
		
		default_id_text_08 = new JTextField("");
		default_id_text_08.setText((String) null);
		default_id_text_08.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_08.setColumns(10);
		default_id_text_08.setBounds(169, 404, 186, 34);
		accountSetPanel.add(default_id_text_08);
		
		default_id_text_09 = new JTextField("");
		default_id_text_09.setText((String) null);
		default_id_text_09.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_09.setColumns(10);
		default_id_text_09.setBounds(169, 454, 186, 34);
		accountSetPanel.add(default_id_text_09);
		
		default_id_text_10 = new JTextField("");
		default_id_text_10.setText((String) null);
		default_id_text_10.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_id_text_10.setColumns(10);
		default_id_text_10.setBounds(169, 506, 186, 34);
		accountSetPanel.add(default_id_text_10);
		
		JLabel default_pw_label_07 = new JLabel("\u5BC6\u7801\uFF1A");
		default_pw_label_07.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_label_07.setBounds(403, 349, 86, 34);
		accountSetPanel.add(default_pw_label_07);
		
		JLabel default_pw_label_08 = new JLabel("\u5BC6\u7801\uFF1A");
		default_pw_label_08.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_label_08.setBounds(403, 403, 86, 34);
		accountSetPanel.add(default_pw_label_08);
		
		JLabel default_pw_label_09 = new JLabel("\u5BC6\u7801\uFF1A");
		default_pw_label_09.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_label_09.setBounds(403, 453, 86, 34);
		accountSetPanel.add(default_pw_label_09);
		
		JLabel default_pw_label_10 = new JLabel("\u5BC6\u7801\uFF1A");
		default_pw_label_10.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_label_10.setBounds(403, 505, 86, 34);
		accountSetPanel.add(default_pw_label_10);
		
		default_pw_text_07 = new JTextField("");
		default_pw_text_07.setText((String) null);
		default_pw_text_07.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_07.setColumns(10);
		default_pw_text_07.setBounds(475, 350, 186, 34);
		accountSetPanel.add(default_pw_text_07);
		
		default_pw_text_08 = new JTextField("");
		default_pw_text_08.setText((String) null);
		default_pw_text_08.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_08.setColumns(10);
		default_pw_text_08.setBounds(475, 404, 186, 34);
		accountSetPanel.add(default_pw_text_08);
		
		default_pw_text_09 = new JTextField("");
		default_pw_text_09.setText((String) null);
		default_pw_text_09.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_09.setColumns(10);
		default_pw_text_09.setBounds(475, 454, 186, 34);
		accountSetPanel.add(default_pw_text_09);
		
		default_pw_text_10 = new JTextField("");
		default_pw_text_10.setText((String) null);
		default_pw_text_10.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		default_pw_text_10.setColumns(10);
		default_pw_text_10.setBounds(475, 506, 186, 34);
		accountSetPanel.add(default_pw_text_10);
		
		JButton changAccountsButton = new JButton("�޸��˻�");
		changAccountsButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		changAccountsButton.setBounds(706, 252, 123, 40);
		accountSetPanel.add(changAccountsButton);
		changAccountsButton.addActionListener(new MyListener(this));
		
		/*
		 * ****************��Ϸ����*****************
		 */
		JPanel AutoSell_Panel = new JPanel();
//		tabbedPane.addTab("��Ϸ����", null, AutoSell_Panel, null);                      ////////////////////////////////������Ϸ����
		AutoSell_Panel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 849, 570);
		AutoSell_Panel.add(panel);
		panel.setLayout(null);
		
		radioButton_1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		
		radioButton_1.setBounds(9, 40, 421, 38);
		panel.add(radioButton_1);		
		radioButton_2.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_2.setBounds(9, 80, 421, 38);
		panel.add(radioButton_2);		
		radioButton_3.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_3.setBounds(9, 120, 421, 36);
		panel.add(radioButton_3);		
		radioButton_4.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_4.setBounds(9, 158, 421, 38);
		panel.add(radioButton_4);		
		radioButton_5.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_5.setBounds(9, 198, 421, 35);
		panel.add(radioButton_5);
		radioButton_6.setFont(new Font("΢���ź�", Font.PLAIN, 14));
				
		radioButton_6.setBounds(9, 235, 421, 38);
		panel.add(radioButton_6);		
		radioButton_7.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_7.setBounds(9, 275, 421, 35);
		panel.add(radioButton_7);
		radioButton_8.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_8.setBounds(9, 312, 421, 35);
		panel.add(radioButton_8);
		radioButton_9.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_9.setBounds(9, 354, 421, 35);
		panel.add(radioButton_9);
		radioButton_10.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_10.setBounds(9, 391, 421, 35);
		panel.add(radioButton_10);
		radioButton_11.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_11.setBounds(9, 428, 411, 38);
		panel.add(radioButton_11);
		radioButton_12.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_12.setBounds(9, 468, 411, 38);
		panel.add(radioButton_12);
		radioButton_13.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_13.setBounds(9, 508, 411, 38);
		panel.add(radioButton_13);
		radioButton_14.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_14.setBounds(432, 40, 411, 38);
		panel.add(radioButton_14);
		radioButton_15.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_15.setBounds(432, 80, 411, 38);
		panel.add(radioButton_15);
		radioButton_16.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_16.setBounds(432, 119, 411, 38);
		panel.add(radioButton_16);
		radioButton_17.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_17.setBounds(432, 158, 411, 38);
		panel.add(radioButton_17);
		radioButton_18.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_18.setBounds(432, 196, 411, 38);
		panel.add(radioButton_18);
		radioButton_19.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		radioButton_19.setBounds(432, 235, 411, 38);
		panel.add(radioButton_19);
		radioButton_20.setFont(new Font("΢���ź�", Font.PLAIN, 14));

		radioButton_20.setBounds(432, 273, 411, 38);
		panel.add(radioButton_20);
		
		group = new ButtonGroup();
		group.add(radioButton_1);
		group.add(radioButton_2);
		group.add(radioButton_3);
		group.add(radioButton_4);
		group.add(radioButton_5);
		group.add(radioButton_6);
		group.add(radioButton_7);
		group.add(radioButton_8);
		group.add(radioButton_9);
		group.add(radioButton_10);
		group.add(radioButton_11);
		group.add(radioButton_12);
		group.add(radioButton_13);
		group.add(radioButton_14);
		group.add(radioButton_15);
		group.add(radioButton_16);
		group.add(radioButton_17);
		group.add(radioButton_18);
		group.add(radioButton_19);
		group.add(radioButton_20);
		group.add(radioButton_21);
		group.add(radioButton_22);
		group.add(radioButton_23);
		group.add(radioButton_24);
		group.add(radioButton_25);
		group.add(radioButton_26);
		
		JButton editGameButton = new JButton("�鿴���޸���Ϸ��Ϣ");
		editGameButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		editGameButton.setBounds(50, 5, 162, 33);
		panel.add(editGameButton);
		editGameButton.addActionListener(new MyListener(this));
		
		JButton delGameButton = new JButton("ɾ������Ϸ");
		delGameButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		delGameButton.setBounds(250, 5, 162, 34);
		panel.add(delGameButton);
		
		radioButton_21.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		radioButton_21.setBounds(432, 309, 411, 38);
		panel.add(radioButton_21);
		
		radioButton_22.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		radioButton_22.setBounds(432, 351, 411, 38);
		panel.add(radioButton_22);
		
		radioButton_23.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		radioButton_23.setBounds(432, 388, 411, 38);
		panel.add(radioButton_23);
		
		radioButton_24.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		radioButton_24.setBounds(432, 428, 411, 38);
		panel.add(radioButton_24);
		
		radioButton_25.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		radioButton_25.setBounds(432, 468, 411, 38);
		panel.add(radioButton_25);
		
		radioButton_26.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		radioButton_26.setBounds(432, 508, 411, 38);
		panel.add(radioButton_26);
		
		default_pw_text_sure2 = new JTextField();
		default_pw_text_sure2.setText("");
		default_pw_text_sure2.setForeground(Color.RED);
		default_pw_text_sure2.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		default_pw_text_sure2.setEditable(false);
		default_pw_text_sure2.setColumns(10);
		default_pw_text_sure2.setBounds(356, 20, 186, 34);
//		panel_2.add(default_pw_text_sure2);
		default_pw_text_sure2.setVisible(false);
		
		JLabel label_1 = new JLabel("���룺");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		label_1.setForeground(new Color(128, 0, 0));
		label_1.setBounds(292, 17, 76, 34);
//		panel_2.add(label_1);
		label_1.setVisible(false);
		delGameButton.addActionListener(new MyListener(this));
		
		radioButton_1.addActionListener(new MyListener(this));
		radioButton_2.addActionListener(new MyListener(this));
		radioButton_3.addActionListener(new MyListener(this));
		radioButton_4.addActionListener(new MyListener(this));
		radioButton_5.addActionListener(new MyListener(this));
		radioButton_6.addActionListener(new MyListener(this));
		radioButton_7.addActionListener(new MyListener(this));
		radioButton_8.addActionListener(new MyListener(this));
		radioButton_9.addActionListener(new MyListener(this));
		radioButton_10.addActionListener(new MyListener(this));
		radioButton_11.addActionListener(new MyListener(this));
		radioButton_12.addActionListener(new MyListener(this));
		radioButton_13.addActionListener(new MyListener(this));
		radioButton_14.addActionListener(new MyListener(this));
		radioButton_15.addActionListener(new MyListener(this));
		radioButton_16.addActionListener(new MyListener(this));
		radioButton_17.addActionListener(new MyListener(this));
		radioButton_18.addActionListener(new MyListener(this));
		radioButton_19.addActionListener(new MyListener(this));
		radioButton_20.addActionListener(new MyListener(this));
		radioButton_21.addActionListener(new MyListener(this));
		radioButton_22.addActionListener(new MyListener(this));
		radioButton_23.addActionListener(new MyListener(this));
		radioButton_24.addActionListener(new MyListener(this));
		radioButton_25.addActionListener(new MyListener(this));
		radioButton_26.addActionListener(new MyListener(this));		
		
		/*
		 * ***************�Ա�������ѯ��Panel**************
		 */
		JPanel panel_3 = new JPanel();
//		tabbedPane.addTab("Steam��ֵ", null, panel_3, null);                                  ///////////////////////////////////// ����steam��ֵ
		panel_3.setLayout(null);
		
		JLabel codeDefaultAccountLabel = new JLabel("��ǰ�˻���");
		codeDefaultAccountLabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		codeDefaultAccountLabel.setBounds(358, 21, 83, 36);
		panel_3.add(codeDefaultAccountLabel);
		
		JLabel codesLabel = new JLabel("��ֵ�룺");
		codesLabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		codesLabel.setBounds(10, 70, 69, 36);
		panel_3.add(codesLabel);
		
		JLabel codesStatusLabel = new JLabel("��ֵ��״̬��");
		codesStatusLabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		codesStatusLabel.setBounds(358, 67, 96, 36);
		panel_3.add(codesStatusLabel);
		
		JLabel statusLabel = new JLabel("��ֵ״̬��");
		statusLabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		statusLabel.setBounds(10, 416, 69, 36);
		panel_3.add(statusLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 76, 264, 309);
		panel_3.add(scrollPane);
		
		codesTextArea = new JTextArea();
		codesTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		scrollPane.setViewportView(codesTextArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(451, 76, 396, 309);
		panel_3.add(scrollPane_1);
		
		codesStatusTextArea = new JTextArea();
		codesStatusTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		codesStatusTextArea.setBackground(SystemColor.control);
		codesStatusTextArea.setEditable(false);
		scrollPane_1.setViewportView(codesStatusTextArea);
		
		codeDefaultAccountText = new JTextField();
		codeDefaultAccountText.setEditable(false);
		codeDefaultAccountText.setBackground(new Color(72, 209, 204));
		codeDefaultAccountText.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		codeDefaultAccountText.setBounds(451, 16, 396, 41);
		panel_3.add(codeDefaultAccountText);
		codeDefaultAccountText.setColumns(10);
		
		statusText = new JTextField();
		statusText.setForeground(Color.RED);
		statusText.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		statusText.setEditable(false);
		statusText.setColumns(10);
		statusText.setBounds(73, 406, 774, 57);
		panel_3.add(statusText);
		
		JButton codeAutoButton = new JButton("Code�Զ���ֵ");
		codeAutoButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		codeAutoButton.setBounds(156, 487, 209, 48);
		panel_3.add(codeAutoButton);
		codeAutoButton.addActionListener(new MyListener(this));
		
		JButton codeCheckButton = new JButton("Code������֤");
		codeCheckButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		codeCheckButton.setBounds(550, 487, 209, 48);
		panel_3.add(codeCheckButton);
		
		codeAccountsComboBox = new JComboBox();
		codeAccountsComboBox.setForeground(Color.RED);
		codeAccountsComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		codeAccountsComboBox.setBounds(73, 16, 264, 41);
		panel_3.add(codeAccountsComboBox);
		codeAccountsComboBox.addItemListener(new MyListener(this));
		
		JLabel label = new JLabel("ѡ���˻���");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		label.setBounds(10, 21, 83, 36);
		panel_3.add(label);
		codeCheckButton.addActionListener(new MyListener(this));
		
		
		/*
		 * �Ա�����
		 */
		
		JPanel Nid_Panel = new JPanel();
//		tabbedPane.addTab("������ѯ", null, Nid_Panel, null);                             /////////////////////////////////�����Ա�����

		String[] columnNames = {"�µ�ʱ��","�������","��Ʒ����","�������","ʵ�տ�","����״̬","�������","��������","���ұ�ע"};
		String[][] column_01 = {};
		tableModel =  new DefaultTableModel(column_01,columnNames);
		
        JLabel table_nid_id_label = new JLabel("��Ʒ���ƣ�");  //�ĳ���Ʒ����
        table_nid_id_label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_nid_id_label.setBounds(6, 18, 93, 40);
        JLabel table_buyer_id_label = new JLabel("����û�����");
        table_buyer_id_label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_buyer_id_label.setBounds(6, 114, 93, 40);
        JLabel table_buyer_email_label = new JLabel("������䣺"); //�ĳɿ���������ߴ���ұ�ע�ж�ȡ ��֮ǰ�������䴦������
        table_buyer_email_label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_buyer_email_label.setBounds(434, 69, 93, 40);
        JLabel table_buyer_note_label = new JLabel("��ұ�ע��");
        table_buyer_note_label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_buyer_note_label.setBounds(434, 115, 93, 40);
        JLabel table_buyer_game_lable = new JLabel("�����˻���");
        table_buyer_game_lable.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_buyer_game_lable.setBounds(6, 214, 418, 40);
        account_type_combo.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        account_type_combo.setBounds(426, 214, 418, 40);
        account_type_combo.addItemListener(new MyListener(this));
        JButton autoSeller_button2 = new JButton("�ֶ���ֵ");     
        autoSeller_button2.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        autoSeller_button2.setBounds(426, 264, 200, 40);
        JButton change_state_button1 = new JButton("����");
        change_state_button1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        change_state_button1.setBounds(640, 264, 200, 40);
        autoSeller_button2.addActionListener(new MyListener(this));
        change_state_button1.addActionListener(new MyListener(this));
        
        JPanel sub_table_panel = new JPanel();
        sub_table_panel.setBounds(9, 266, 850, 314);
        sub_table_panel.setPreferredSize(new Dimension(850, 350));
        sub_table_panel.setBorder(BorderFactory.createTitledBorder("��������"));
        sub_table_panel.setLayout(null);
        
        sub_table_panel.add(table_nid_id_label);
        table_gameName_text.setEditable(false);
        table_gameName_text.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_gameName_text.setBounds(96, 19, 740, 40);
        sub_table_panel.add(table_gameName_text);
        
        sub_table_panel.add(table_buyer_id_label);
        table_buyer_id_text.setEditable(false);
        table_buyer_id_text.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_buyer_id_text.setBounds(96, 115, 317, 40);
        sub_table_panel.add(table_buyer_id_text);
        
        sub_table_panel.add(table_buyer_email_label);
        table_buyer_email_text.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_buyer_email_text.setBounds(525, 69, 311, 40);
        sub_table_panel.add(table_buyer_email_text);
        
        sub_table_panel.add(table_buyer_note_label);
        table_buyer_message_text.setEditable(false);
        table_buyer_message_text.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_buyer_message_text.setBounds(525, 115, 317, 40);
        sub_table_panel.add(table_buyer_message_text);
        
        sub_table_panel.add(table_buyer_game_lable);
        sub_table_panel.add(account_type_combo);
        sub_table_panel.add(autoSeller_button2);
        sub_table_panel.add(change_state_button1);
        
        Nid_Panel.add(sub_table_panel);
        
        JLabel label_3 = new JLabel("���������");
        label_3.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        label_3.setBounds(6, 64, 80, 40);
        sub_table_panel.add(label_3);
        
        table_gameNum_text = new JTextField(37);
        table_gameNum_text.setEditable(false);
        table_gameNum_text.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_gameNum_text.setBounds(96, 68, 317, 40);
        sub_table_panel.add(table_gameNum_text);
        
        JLabel label_7 = new JLabel("���ұ�ע��");
        label_7.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        label_7.setBounds(6, 164, 93, 40);
        sub_table_panel.add(label_7);
        
        table_seller_message_text = new JTextField(37);
        table_seller_message_text.setEditable(false);
        table_seller_message_text.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table_seller_message_text.setBounds(96, 164, 317, 40);
        sub_table_panel.add(table_seller_message_text);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(9, 60, 850, 196);
        Nid_Panel.add(scrollPane_3);
        
        table = new JTable(tableModel);
        table.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        scrollPane_3.setViewportView(table);
        table.setRowHeight(35);
        
      //�������ñ��Ŀ��
        TableColumn col_01 = table.getColumn("�µ�ʱ��");
        TableColumn col_02 = table.getColumn("�������");
        TableColumn col_03 = table.getColumn("��Ʒ����");
        TableColumn col_04 = table.getColumn("�������");
        TableColumn col_05 = table.getColumn("ʵ�տ�");
        TableColumn col_06 = table.getColumn("����״̬");
        TableColumn col_07 = table.getColumn("�������");
        TableColumn col_08 = table.getColumn("��������");
        TableColumn col_09 = table.getColumn("���ұ�ע");
        col_01.setPreferredWidth(125);
        col_02.setPreferredWidth(115);
        col_03.setPreferredWidth(350);
        col_04.setPreferredWidth(100);
        col_05.setPreferredWidth(50);
        col_06.setPreferredWidth(110);
        
      //����ʾ�ջ���ַ��һ�У��������ݻ���
        col_07.setMaxWidth(0);
        col_07.setMinWidth(0);
        col_07.setPreferredWidth(0);
        col_08.setMaxWidth(0);
		col_08.setMinWidth(0);
        col_08.setPreferredWidth(0);
        col_09.setMaxWidth(0);
		col_09.setMinWidth(0);
        col_09.setPreferredWidth(0);
		Nid_Panel.setLayout(null);
        
        table.addMouseListener(new MyTableListener(this));
        
        //        JScrollPane orderListScrollPane = new JScrollPane(table);   //֧�ֹ���
        Nid_Panel.add(table.getTableHeader());
        
        JButton start_clash_button = new JButton("ˢ��");
        start_clash_button.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        start_clash_button.setBounds(670, 10, 189, 40);
        Nid_Panel.add(start_clash_button);
        
        JLabel lblNewLabel = new JLabel("��ʼʱ�䣺");
        lblNewLabel.setFont(new Font("΢���ź�", Font.BOLD, 14));
        lblNewLabel.setBounds(9, 10, 94, 40);
        Nid_Panel.add(lblNewLabel);
        
        JLabel label_2 = new JLabel("����ʱ�䣺");
        label_2.setFont(new Font("΢���ź�", Font.BOLD, 14));
        label_2.setBounds(338, 10, 118, 40);
        Nid_Panel.add(label_2);
        
        startTimeField = new JTextField();
        startTimeField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        startTimeField.setBounds(86, 10, 242, 40);
        Nid_Panel.add(startTimeField);
        startTimeField.setColumns(10);
        
        endTimeField = new JTextField();
        endTimeField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        endTimeField.setColumns(10);
        endTimeField.setBounds(418, 10, 242, 40);
        Nid_Panel.add(endTimeField);
        
        start_clash_button.addActionListener(new MyListener(this));
        
		/*
		 * �Ա���Ȩ
		 */
		
		ButtonGroup group1 = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();
		ButtonGroup group3 = new ButtonGroup();
		JPanel Session_Panel = new JPanel();
//		tabbedPane.addTab("�Ա���Ȩ", null, Session_Panel, null);              /////////////////////////////////////�����Ա���Ȩ
		Session_Panel.setLayout(null);
		
		JLabel Session_label = new JLabel("����Session");
		Session_label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		Session_label.setBounds(38, 20, 111, 38);
		Session_Panel.add(Session_label);
		
		Session_text = new JTextField();
		Session_text.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		Session_text.setBounds(182, 18, 634, 38);
		Session_Panel.add(Session_text);
		Session_text.setColumns(10);
		
		JLabel Session_key_label = new JLabel("��õ�SessionKey");
		Session_key_label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		Session_key_label.setBounds(38, 78, 111, 38);
		Session_Panel.add(Session_key_label);
		
		Session_key_text = new JTextField();
		Session_key_text.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		Session_key_text.setEditable(false);
		Session_key_text.setColumns(10);
		Session_key_text.setBounds(182, 78, 634, 38);
		Session_Panel.add(Session_key_text);
		
		JButton Seesion_key_button = new JButton("��ȡSessionKey");
		Seesion_key_button.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		Seesion_key_button.setBounds(452, 153, 161, 43);
		Session_Panel.add(Seesion_key_button);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "��������", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(10, 276, 849, 272);
		Session_Panel.add(panel_9);
		panel_9.setLayout(null);
		closeBroRadioButton = new JRadioButton("�ر�",true);
		closeBroRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		group1.add(closeBroRadioButton);
		closeBroRadioButton.setBounds(388, 56, 121, 23);
		panel_9.add(closeBroRadioButton);
		closeBroRadioButton.addActionListener(new MyListener(this));
		
		dontCloseRadioButton = new JRadioButton("���ر�");
		dontCloseRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		group1.add(dontCloseRadioButton);
		dontCloseRadioButton.setBounds(579, 56, 121, 23);
		panel_9.add(dontCloseRadioButton);
		dontCloseRadioButton.addActionListener(new MyListener(this));
		
		showBroRadioButton = new JRadioButton("��ʾ",true);
		showBroRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		group2.add(showBroRadioButton);
		showBroRadioButton.setBounds(388, 115, 121, 23);
		panel_9.add(showBroRadioButton);
		showBroRadioButton.addActionListener(new MyListener(this));
		
		dontShowRadioButton = new JRadioButton("����ʾ");
		dontShowRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		group2.add(dontShowRadioButton);
		dontShowRadioButton.setBounds(579, 115, 121, 23);
		panel_9.add(dontShowRadioButton);
		dontShowRadioButton.addActionListener(new MyListener(this));
		
		autoSendRadioButton = new JRadioButton("�ֶ�����",true);
		autoSendRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		group3.add(autoSendRadioButton);
		autoSendRadioButton.setBounds(388, 174, 121, 23);
		panel_9.add(autoSendRadioButton);
		autoSendRadioButton.addActionListener(new MyListener(this));
		
		manualSendRadioButton = new JRadioButton("�Զ�����");
		manualSendRadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		group3.add(manualSendRadioButton);
		manualSendRadioButton.setBounds(579, 174, 121, 23);
		panel_9.add(manualSendRadioButton);
		manualSendRadioButton.addActionListener(new MyListener(this));
		
		
		JLabel lblNewLabel_2 = new JLabel("��ɺ��Ƿ�ر������(Ĭ�Ϲر�)��");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(94, 48, 260, 38);
		panel_9.add(lblNewLabel_2);
		
		JLabel label_5 = new JLabel("�����Ƿ���Ҫ��ʾ�����(Ĭ����ʾ)��");
		label_5.setForeground(new Color(128, 0, 0));
		label_5.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label_5.setBounds(94, 106, 260, 38);
		panel_9.add(label_5);
		
		JLabel label_6 = new JLabel("�����������Ա�������Ĭ���ֶ���������");
		label_6.setForeground(new Color(128, 0, 0));
		label_6.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		label_6.setBounds(94, 164, 260, 38);
		panel_9.add(label_6);

		
		JButton taobaoAutoButton = new JButton("�����ȡ�Ա���Ȩ");
		taobaoAutoButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		taobaoAutoButton.setBounds(182, 153, 161, 43);
		Session_Panel.add(taobaoAutoButton);
		taobaoAutoButton.addActionListener(new MyListener(this));
		Seesion_key_button.addActionListener(new MyListener(this));
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("������־", null, panel_6, null);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(5,5, 870, 520);
		panel_6.add(scrollPane_2);
		
		steamLogTextArea = new JTextArea();
		scrollPane_2.setViewportView(steamLogTextArea);
		steamLogTextArea.setForeground(new Color(0, 139, 139));
		steamLogTextArea.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		
		JButton clearLogButton = new JButton("�����ʷ��¼");
		clearLogButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		clearLogButton.setBounds(730, 530, 143, 39);
		panel_6.add(clearLogButton);
		clearLogButton.addActionListener(new MyListener(this));
		
		
		// **************�����˻�ҳ���ʼ������**************	 
		getDefaultAccount();
		
		//������Ϸѡ������ʼ������
//		setGames();
		setJsonGames();
		
		//Code��֤���ֵʱ��ʼ���˻�����
		getCodeAccounts();
		
		//��ȡ������־
		getAllLog();
		
		//��ȡBuySteamGameXpath�ĳ�ʼ������
		getBuySteamGameXpath();
		
		//��ʼ��ʱ��
		setStartEndTime();
		
		//����TabPanel��Ӽ���
//		tabbedPane.addChangeListener(new MyChangeListener(this));
		
	}
	
	/*
	 * *****************�˻������ʼ��******************
	 */	
	public void getDefaultAccount() {
		BufferedReader br = null;
		
		this.buyGameAccounts = new HashMap<String, String>();
		this.buyGameAccountID = new ArrayList<String>();
		
		try {
			br = new BufferedReader(new FileReader(new File("config\\Accounts_Purchase.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String temp;	
		
		//ע��������⣬�������֧��utf-8���������е�txt�ļ�����Ϊansi��
		//��ȡĬ���˻��ļ�
		int i = 0;
		try {
			br.readLine();
//			while((temp=br.readLine())!=null&&i++<10){
			while((temp=br.readLine())!=null){
				String default_id = temp.split("-")[0];
				String default_pw = temp.split("-")[1];
				this.buyGameAccountID.add(default_id);
				this.buyGameAccounts.put(default_id, default_pw);
//				switch(i){
//				case 1:
//					this.default_id_text_01.setText(default_id);
//					this.default_pw_text_01.setText(default_pw);
//					break;
//				case 2:
//					this.default_id_text_02.setText(default_id);
//					this.default_pw_text_02.setText(default_pw);
//					break;
//				case 3:
//					this.default_id_text_03.setText(default_id);
//					this.default_pw_text_03.setText(default_pw);
//					break;
//				case 4:
//					this.default_id_text_04.setText(default_id);
//					this.default_pw_text_04.setText(default_pw);
//					break;
//				case 5:
//					this.default_id_text_05.setText(default_id);
//					this.default_pw_text_05.setText(default_pw);
//					break;
//				case 6:
//					this.default_id_text_06.setText(default_id);
//					this.default_pw_text_06.setText(default_pw);
//					break;
//				case 7:
//					this.default_id_text_07.setText(default_id);
//					this.default_pw_text_07.setText(default_pw);
//					break;
//				case 8:
//					this.default_id_text_08.setText(default_id);
//					this.default_pw_text_08.setText(default_pw);
//					break;
//				case 9:
//					this.default_id_text_09.setText(default_id);
//					this.default_pw_text_09.setText(default_pw);
//					break;
//				case 10:
//					this.default_id_text_10.setText(default_id);
//					this.default_pw_text_10.setText(default_pw);
//					break;
//				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��ɾ��������Ŀ�����
		this.account_type_combo.removeAllItems();
		this.accountsComboBox.removeAllItems();
		this.codeAccountsComboBox.removeAllItems();
		for(i=0;i<this.buyGameAccountID.size();i++){
			this.accountsComboBox.addItem(this.buyGameAccountID.get(i));
			this.account_type_combo.addItem(this.buyGameAccountID.get(i));
			this.codeAccountsComboBox.addItem(this.buyGameAccountID.get(i));
		}
		
	}
	/*
	 * *******��Ϸѡ������ʼ������**********
	 */
	public void setGames() throws JDOMException, IOException{
		File f = new File("games");
		File[] fileLists = f.listFiles();
		steamGames = new HashMap<String, SteamGame>();
		games = new ArrayList<String>();
		
		radioButton_1.setText("�������Ϸ");
		radioButton_2.setText("�������Ϸ");
		radioButton_3.setText("�������Ϸ");
		radioButton_4.setText("�������Ϸ");
		radioButton_5.setText("�������Ϸ");
		radioButton_6.setText("�������Ϸ");
		radioButton_7.setText("�������Ϸ");
		radioButton_8.setText("�������Ϸ");
		radioButton_9.setText("�������Ϸ");
		radioButton_10.setText("�������Ϸ");
		radioButton_11.setText("�������Ϸ");
		radioButton_12.setText("�������Ϸ");
		radioButton_13.setText("�������Ϸ");
		radioButton_14.setText("�������Ϸ");
		radioButton_15.setText("�������Ϸ");
		radioButton_16.setText("�������Ϸ");
		radioButton_17.setText("�������Ϸ");
		radioButton_18.setText("�������Ϸ");
		radioButton_19.setText("�������Ϸ");
		radioButton_20.setText("�������Ϸ");
		radioButton_21.setText("�������Ϸ");
		radioButton_22.setText("�������Ϸ");
		radioButton_23.setText("�������Ϸ");
		radioButton_24.setText("�������Ϸ");
		radioButton_25.setText("�������Ϸ");
		radioButton_26.setText("�������Ϸ");
		
		//��˳���ȡ�ļ������浽Map
		for(int i=0;i<fileLists.length&&i<26;i++){
			BufferedReader br = new BufferedReader(new FileReader(fileLists[i]));
			SteamGame steamGame = new SteamGame();
			
			steamGame.setFileName(fileLists[i].getName());
			steamGame.setLink(br.readLine());
			steamGame.setXpath(br.readLine());
			steamGame.setName(br.readLine());
			
			steamGames.put(steamGame.getName(), steamGame);
			games.add(steamGame.getName());
			
			switch(i+1){
			case 1:
				radioButton_1.setText(steamGame.getName());
				break;
			case 2:
				radioButton_2.setText(steamGame.getName());
				break;
			case 3:
				radioButton_3.setText(steamGame.getName());
				break;
			case 4:
				radioButton_4.setText(steamGame.getName());
				break;
			case 5:
				radioButton_5.setText(steamGame.getName());
				break;
			case 6:
				radioButton_6.setText(steamGame.getName());
				break;
			case 7:
				radioButton_7.setText(steamGame.getName());
				break;
			case 8:
				radioButton_8.setText(steamGame.getName());
				break;
			case 9:
				radioButton_9.setText(steamGame.getName());
				break;
			case 10:
				radioButton_10.setText(steamGame.getName());
				break;
			case 11:
				radioButton_11.setText(steamGame.getName());
				break;
			case 12:
				radioButton_12.setText(steamGame.getName());
				break;
			case 13:
				radioButton_13.setText(steamGame.getName());
				break;
			case 14:
				radioButton_14.setText(steamGame.getName());
				break;
			case 15:
				radioButton_15.setText(steamGame.getName());
				break;
			case 16:
				radioButton_16.setText(steamGame.getName());
				break;
			case 17:
				radioButton_17.setText(steamGame.getName());
				break;
			case 18:
				radioButton_18.setText(steamGame.getName());
				break;
			case 19:
				radioButton_19.setText(steamGame.getName());
				break;
			case 20:
				radioButton_20.setText(steamGame.getName());
				break;
			case 21:
				radioButton_21.setText(steamGame.getName());
				break;
			case 22:
				radioButton_22.setText(steamGame.getName());
				break;
			case 23:
				radioButton_23.setText(steamGame.getName());
				break;
			case 24:
				radioButton_24.setText(steamGame.getName());
				break;
			case 25:
				radioButton_25.setText(steamGame.getName());
				break;
			case 26:
				radioButton_26.setText(steamGame.getName());
				break;
			}
			
			br.close();
		}
		
		//��ɾ�����е���Ŀ��Ȼ�������
		this.gamesComboBox.removeAllItems();                              
		for(int i=0;i<games.size();i++){
			this.gamesComboBox.addItem(games.get(i));
		}

		
		
		this.uniGamesComboBox.removeAllItems();
		for(int i=0;i<games.size();i++){
			if("������Ϸ".equals(games.get(i))){
				this.uniGamesComboBox.addItem(games.get(i));
			}
		}
	}
	
	/*
	 * Code��֤���ֵ��ʼ���˻��ĺ���
	 */
	public void getCodeAccounts() throws IOException{
		BufferedReader bf = null;
		
		try {
			bf = new BufferedReader(new FileReader(new File("config\\Accounts_Validate.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String temp;
		String defaultId = null;
		String defaultPassword = null;
		
		while((temp=bf.readLine())!=null){
			defaultId = temp.split("-")[0];
			System.out.println(defaultId);
			defaultPassword = temp.split("-")[1];
			System.out.println(defaultPassword);
			accounts.put(defaultId,defaultPassword);
			accountIDs.add(defaultId);
		}
		
	}
	
	/*
	 * ��־ҳ���ʼ������
	 */
	public void getAllLog() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File("steam.log")));
		String temp;
		while((temp=br.readLine())!=null){
			steamLogTextArea.setText(steamLogTextArea.getText()+temp+"\n");
		}
	}
	
	/*
	 * ��ȡBuySteamGameXpath�ĳ�ʼ������
	 */
	public void getBuySteamGameXpath() throws IOException{
//		this.buySteamGameXpath = new HashMap<String,String>();
		this.WalletXpath = new HashMap<String,String>();
//		BufferedReader br = new BufferedReader(new FileReader(new File("config/BuySteamGameXpath.txt")));
		String temp;
//		while((temp=br.readLine())!=null){
//			String xpathName = temp.split("-")[0];
//			String xpath = temp.split("-")[1];
//			this.buySteamGameXpath.put(xpathName, xpath);
//		}
		BufferedReader br2 = new BufferedReader(new FileReader(new File("config/WalletXpath.txt")));
		while((temp=br2.readLine())!=null){
			String xpathName = temp.split("-")[0];
			String xpath = temp.split("-")[1];
			this.WalletXpath.put(xpathName, xpath);
		}
	}
	
	//ʱ���ʼ��
	public void setStartEndTime(){
		Date startDate = new Date(System.currentTimeMillis()-86400000);
		Date endDate = new Date();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = format.format(startDate);
		String endTime = format.format(endDate);
		
		this.startTimeField.setText(startTime);
		this.endTimeField.setText(endTime);
	}
	
	
	//���ñ��ĺ���
	public void setAdder(final ArrayList arr,final int row){			 
		setTableModel(arr,row);		
		String buyer_nick = (String) arr.get(4);
		String title = (String)arr.get(3);
		String receiver_address = (String)arr.get(6);
//		String num = (String)arr.get(4);
//		auto_buyer_id_text.setText(diamond.getName());
//		auto_buyer_pw_text.setText(diamond.getPw());
//		auto_dia_type_text.setText(title);
//		auto_dia_num_text.setText(diamond.getAmount());
		System.out.println("this is adder~~~~~~");		
	}
	
	public void setTableModel(ArrayList arr,int row){
		String status = "";
		switch((String)arr.get(5)){
			case "BUYER_PAY": status = "�ȴ���Ҹ���"; break;
			case "WAIT_SELLER_SEND_GOODS": status = "�ȴ����ҷ���"; break;
			case "WAIT_BUYER_CONFIRM_GOODS": status = "�ȴ����ȷ���ջ�"; break;
			case "TRADE_FINISHED": status = "���׳ɹ�"; break;
		}
		
		String tidTime = (String) arr.get(0);
		String nid = (String) arr.get(1);
		String gameName = (String) arr.get(2);
		String buyerWW = (String) arr.get(3);
		String payment = (String) arr.get(4);
		String tradeStatus = status;
		String message = (String) arr.get(6);
		String buyNum = (String) arr.get(7);
		String sellerMomo = (String) arr.get(8);
		String rowValue[] = {tidTime,nid,gameName,buyerWW,payment,tradeStatus,message,buyNum,sellerMomo};
		System.out.println("�����������������û����ԣ�������������������"+message);
		tableModel.addRow(rowValue);
    	System.out.println(tableModel.getValueAt(row, 0));
    }
	public void setJsonGames() throws IOException, JSONException {
		steamGames = new HashMap<String, SteamGame>();
		games = new ArrayList<String>();
		
		StringBuffer stringBuffer = new StringBuffer();
		String line = null ;
		BufferedReader br = new BufferedReader(new FileReader(new File("config\\gameConfig.txt")));
		while( (line = br.readLine())!= null ){
			stringBuffer.append(line);
		} 
		//��Json�ļ������γ�JSONObject����
		JSONObject jsonObject = new JSONObject(stringBuffer.toString());
		//��ȡJSONObject�������ݲ���ӡ
		JSONArray gameArr= jsonObject.getJSONArray("games");
		for (int i = 0; i < gameArr.length(); i++) {
			SteamGame steamGame = new SteamGame();
			JSONObject game=gameArr.getJSONObject(i);
			steamGame.setLink(game.getString("address"));
			steamGame.setXpath(game.getString("xpath"));
			steamGame.setName(game.getString("name"));
			steamGames.put(steamGame.getName(), steamGame);
			games.add(steamGame.getName());
		}
		//��ʼ����Ϸ������
		gamesComboBox.removeActionListener(gamesComboBox.boxListener);
		gamesComboBox.removeAllItems();
//		gamesComboBox.addItem(new CheckValue(true, "Select All"));
		
		CheckValue cValue;
		for(int i=0;i<games.size();i++){
//			this.gamesComboBox.addItem(games.get(i));
			cValue = new CheckValue();
			cValue.value = games.get(i).toString();
			gamesComboBox.addItem(cValue);
		}
		
		gamesComboBox.addActionListener(gamesComboBox.boxListener);
	}
}
