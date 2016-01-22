package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.jboss.netty.handler.codec.spdy.SpdySynReplyFrame;
import org.jdom2.JDOMException;
import org.json.JSONException;

import script.ToDBC;
import selenium.GameObj;
import selenium.TaobaoAuto;
import selenium.Wallet;
import taobao.ShouquanController;
import taobao.TaobaoController;
import taobao.Tid;
import bean.SteamGame;

import com.taobao.api.ApiException;

public class MyListener implements ActionListener,  ItemListener{
	public AutoSellerUI frame;
//	private Adder frame;
    TaobaoController tbc = TaobaoController.getInstance();
    private int columnrow = 0;
    
    public MyListener(){}
    
    public MyListener(AutoSellerUI frame)
    {
        this.frame = frame;
    }
    
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(frame.accountsComboBox.getSelectedItem()!=null){
			System.out.println(frame.accountsComboBox.getSelectedItem().toString());
		}
		
		if(frame.codeAccountsComboBox.getSelectedItem()!=null){
			String codeDefaultAccountID = frame.codeAccountsComboBox.getSelectedItem().toString();
			String codeDefaultAccountPW = frame.buyGameAccounts.get(codeDefaultAccountID);
			frame.codeDefaultAccountText.setText(codeDefaultAccountID);
			System.out.println(codeDefaultAccountPW);
		}
		
		
		if(frame.countrySelectBox.getSelectedItem()!=null){
			System.out.println(frame.countrySelectBox.getSelectedItem().toString());
			String country = frame.countrySelectBox.getSelectedItem().toString();
			switch(country){
			case "���": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-hk.properties";break;
			case "����": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-us.properties";break;
			case "�й�": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-cn.properties";break;
			case "����˹": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-ru.properties";break;
			case "�ڿ���": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-ua.properties";break;
			case "����": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-br.properties";break;
			case "�ձ�": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-jp.properties";break;
			case "ӡ��������": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-id.properties";break;
			case "��������": frame.buySteamGameXpathFilePath = "config/BuySteamGameXpath-my.properties";break;
			}
			try {
				changeBuySteamGameXpath(frame.buySteamGameXpathFilePath);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		if(e.getActionCommand()=="����1"){
//			new Thread(){
//				public void run(){
//					for (int i = 0; i < frame.gamesComboBox.getItemCount(); i++) {
//						Object object=frame.gamesComboBox.getItemAt(i);
//						CheckValue value=(CheckValue) frame.gamesComboBox.getItemAt(i);
//						if (value.bolValue) {
//							System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+value.value);
//							value.bolValue=false;
//						}
//					}
//				}
//			}.start();
//								
//		}
		
		if(e.getActionCommand()=="�˻�����"){
			
			new Thread(){
				public void run(){
					try {
						Runtime.getRuntime().exec("cmd  /c  start config\\Accounts_Purchase.txt");
						frame.getDefaultAccount();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
								
		}
		
		if(e.getActionCommand()=="��Ϸ����"){
			
			new Thread(){
				public void run(){
					try {
						Runtime.getRuntime().exec("cmd  /c  start config\\gameConfig.txt");
						frame.setJsonGames();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					} 
				}
			}.start();
								
		}
		
		if(e.getActionCommand()=="�����ȡ�Ա���Ȩ"){
			
			new Thread(){
				public void run(){
					TaobaoAuto tbAuto = new TaobaoAuto();
					tbAuto.setAutoUrl("https://oauth.taobao.com/authorize?response_type=code&client_id=21784353&redirect_uri=urn:ietf:wg:oauth:2.0:oob&state=1212");
					tbAuto.getTaobaoAuto();
				}
			}.start();
								
		}
		
		if(e.getActionCommand() =="��ȡSessionKey"){
        	ShouquanController sqc = ShouquanController.getInstance();     	
    		String shouquan=frame.Session_text.getText();
    		String sessionKey = sqc.getSessionKey(shouquan);
//    		String sessionKey = "6200725886354102840c4b1c9484287b3ZZfe0d1aeaacfa693314641";
    		try {
				sqc.setSessionKey(sessionKey);
				sqc.setSessionKeyFile(sessionKey);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		frame.Session_key_text.setText(sessionKey);
        }
		
		
		if(e.getActionCommand() == "ȷ�ϲ�����"){
			
			String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
			String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
			String defaultEmail = frame.default_email_text_sure.getText();
			String steamGameName = frame.gamesComboBox.getSelectedItem().toString();
			if("��ѡ���˻�".equals(defaultAccountID)||defaultAccountID==null||"��ѡ����Ϸ".equals(steamGameName)||
					steamGameName==null||defaultEmail==null||"".equals(defaultEmail)){
				AlertUI alertUI = new AlertUI();
				return;
			}		
			
			final BatchBuyUI batchFrame = new BatchBuyUI(this.frame);
			batchFrame.confirmBuyButton.setEnabled(false);
			batchFrame.confirmToWareButton.setEnabled(false);
			batchFrame.buyMyselfButton.setEnabled(false);
			batchFrame.jumpBuyMyselfButton.setEnabled(false);
			batchFrame.buyNumText.setEditable(false);
			
			
			new Thread(new Runnable(){
				@Override
        		public void run(){
					//��ȡ�ж��ٸ��û�
					int accountNum=frame.accountsComboBox.getItemCount();
					//��ȡ��ǰ�û��ڵڼ���
					int currentAcciunt=frame.accountsComboBox.getSelectedIndex();
					//����Ƿ��㹻,true����㹻
					boolean balanceHasEnough=false;
					//��Ϸ��
					String steamGameName = ((CheckValue)frame.gamesComboBox.getSelectedItem()).value;
					GameObj gameObj=null;
					//�������������һ���˻�
					while (!balanceHasEnough&&currentAcciunt<accountNum) {
						//����ǰ�˻���Ϊѡ��
						frame.accountsComboBox.setSelectedIndex(currentAcciunt);
						batchFrame.setText();
						
						String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
						String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
						String defaultEmail = frame.default_email_text_sure.getText();
						
						
						 gameObj = new GameObj(batchFrame);
						
						//��ѯ���˻���Ҫ������ٸ���Ϸ
						CheckValue value;
 						for (int i = 0; i < frame.gamesComboBox.getItemCount(); i++) {
						value=(CheckValue) frame.gamesComboBox.getItemAt(i);
						if (value.bolValue) {
							gameObj.buySteamGame.put(value.value,frame.steamGames.get(value.value));
							System.out.println(value.bolValue);
							System.out.println(value.value);
						}
						}
						gameObj.setDefault_account(defaultAccountID);
						gameObj.setDefaule_password(defaultAccountPW);
						gameObj.setDefault_email(ToDBC.ToDBC(defaultEmail));
						gameObj.setBatchFrame(batchFrame);
						gameObj.setBuyGameAccountID(batchFrame.frame.buyGameAccountID);
						gameObj.setBuyGameAccounts(batchFrame.frame.buyGameAccounts);
						gameObj.setGameNum(Integer.parseInt(batchFrame.buyNumText.getText()));
						gameObj.setFrame(frame);
						gameObj.setBuySteamGameXpath(frame.buySteamGameXpath);
						balanceHasEnough=gameObj.buyRun();
	        			currentAcciunt++;
					}
					//����������˳�
					batchFrame.info_buy_process_Text.setText("����������˳���");
					gameObj.exitBrowAfterRunning();
        		}
        	}).start();	
			
			
//				}
//			}
		}
		
		if(e.getActionCommand() == "�����ɹ�"){
			String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
			String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
			String defaultEmail = frame.default_email_text_sure.getText();
			String steamGameName = frame.gamesComboBox.getSelectedItem().toString();
			if("��ѡ���˻�".equals(defaultAccountID)||defaultAccountID==null||"��ѡ����Ϸ".equals(steamGameName)||steamGameName==null||defaultEmail==null||"".equals(defaultEmail)){
				AlertUI alertUI = new AlertUI();
				return;
			}		
			BatchBuyUI batchFrame = new BatchBuyUI(this.frame);
			batchFrame.buyMyselfButton.setEnabled(false);
			batchFrame.jumpBuyMyselfButton.setEnabled(false);
		}
		
		if(e.getActionCommand() == "Ϊ�Լ�����"){
			String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
			String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
			String defaultEmail = frame.default_email_text_sure.getText();
			String steamGameName = frame.gamesComboBox.getSelectedItem().toString();
			if("��ѡ���˻�".equals(defaultAccountID)||defaultAccountID==null||"��ѡ����Ϸ".equals(steamGameName)||steamGameName==null||defaultEmail==null||"".equals(defaultEmail)){
				AlertUI alertUI = new AlertUI();
				return;
			}		
			BatchBuyUI batchFrame = new BatchBuyUI(this.frame);
			batchFrame.info_default_email_text.setText("");
			batchFrame.confirmBuyButton.setEnabled(false);
			batchFrame.confirmToWareButton.setEnabled(false);
			batchFrame.jumpBuyMyselfButton.setEnabled(false);
		}
		
		if(e.getActionCommand() == "��������"){
			String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
			String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
			String defaultEmail = frame.default_email_text_sure.getText();
			String steamGameName = frame.gamesComboBox.getSelectedItem().toString();
			if("��ѡ���˻�".equals(defaultAccountID)||defaultAccountID==null||"��ѡ����Ϸ".equals(steamGameName)||steamGameName==null||defaultEmail==null||"".equals(defaultEmail)){
				AlertUI alertUI = new AlertUI();
				return;
			}		
			BatchBuyUI batchFrame = new BatchBuyUI(this.frame);
			batchFrame.confirmBuyButton.setEnabled(false);
			batchFrame.confirmToWareButton.setEnabled(false);
			batchFrame.buyMyselfButton.setEnabled(false);
		}
		
		if(e.getActionCommand() == "�޸��˻�"){
			//�Ƚ��޸ĺ���˻�������������ļ��������¼���
			try {
				updateBuyGameAccounts();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//������������ݣ��ٶ����˻���Ϣ
			clearAccounts();
			this.frame.getDefaultAccount();
			System.out.println("�����˻�"+this.frame.buyGameAccountID.size());
		}
		
		if(e.getActionCommand() == "ˢ��"){
			int count = this.frame.tableModel.getRowCount();
			System.out.println("count����"+count);
			for(int i=count-1;i>=0;i--){
				System.out.println(i);
				this.frame.tableModel.removeRow(i);
			}
			System.out.println("Remove OK");
			new Thread(new Runnable(){
        		@Override
        		public void run(){
        			tbc = new TaobaoController(frame);
        			tbc.newAutoSell(frame);
        		}
        	}).start();
		}
		
		if(e.getActionCommand() == "�ѷ���"){
			boolean status = false;
        	try {
				status = tbc.changeStatus(Tid.getInstance().getTid());
			} catch (ApiException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(status){
//        		clearTabel();
        		new Thread(new Runnable(){
            		@Override
            		public void run(){      
            			tbc = new TaobaoController();
            			tbc.newAutoSell(frame);
            		}
            	}).start();
        		System.out.println("�����ɹ�����");
        	}else{
        		System.out.println("����ʧ�ܣ���");
        	}
		}
		
		
		if(e.getActionCommand() == "����"){
			String nid = "";
			String sellerMemo = "";
			nid = (String) frame.table.getValueAt(frame.table.getSelectedRow(), 1);
			sellerMemo = frame.table_seller_message_text.getText();
			System.out.println(nid);
			if("".equals(nid)||nid==null){
				return;
			}
			
			boolean status = false;
        	try {
				status = tbc.changeStatus(Long.parseLong(nid));
			} catch (ApiException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	if(status){
        		System.out.println("�����ɹ�����");
        	}else{
        		System.out.println("����ʧ�ܣ���");
        	}
        	
        	try{
	        	if("".equals(sellerMemo)||sellerMemo==null){
	        		tbc.memoAdd(Long.parseLong(nid));
	        	}else{
	        		tbc.memoUpdate(Long.parseLong(nid));
	        	} 		
        	} catch (ApiException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	//������Ժ�ɾ������Ӧ����
        	this.frame.tableModel.removeRow(frame.table.getSelectedRow());
		}
		/*
		 * ��������ѡ��ѡ��
		 */
		if(frame.radioButton_1.isSelected()){
			//�Ȱ�����radioButton�ı�������ΪĬ�ϣ��ٸ���
			clearRadiosBackground();
			frame.radioButton_1.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_1.getText();
			
			//�ж�radioButton�����Ƿ��ǡ��������Ϸ����������򵯳������Ϸ�Ŀ�
			if("�������Ϸ".equals(frame.radioButton_1.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_2.isSelected()){
			clearRadiosBackground();
			frame.radioButton_2.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_2.getText();
			
			if("�������Ϸ".equals(frame.radioButton_2.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_3.isSelected()){
			clearRadiosBackground();
			frame.radioButton_3.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_3.getText();
			
			if("�������Ϸ".equals(frame.radioButton_3.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_4.isSelected()){
			clearRadiosBackground();
			frame.radioButton_4.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_4.getText();
			
			if("�������Ϸ".equals(frame.radioButton_4.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_5.isSelected()){
			clearRadiosBackground();
			frame.radioButton_5.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_5.getText();
			
			if("�������Ϸ".equals(frame.radioButton_5.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_6.isSelected()){
			clearRadiosBackground();
			frame.radioButton_6.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_6.getText();
			
			if("�������Ϸ".equals(frame.radioButton_6.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_7.isSelected()){		
			clearRadiosBackground();
			frame.radioButton_7.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_7.getText();
			
			if("�������Ϸ".equals(frame.radioButton_7.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_8.isSelected()){	
			clearRadiosBackground();
			frame.radioButton_8.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_8.getText();
			
			if("�������Ϸ".equals(frame.radioButton_8.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_9.isSelected()){		
			clearRadiosBackground();
			frame.radioButton_9.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_9.getText();
			
			if("�������Ϸ".equals(frame.radioButton_9.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_10.isSelected()){		
			clearRadiosBackground();
			frame.radioButton_10.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_10.getText();
			
			if("�������Ϸ".equals(frame.radioButton_10.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_11.isSelected()){		
			clearRadiosBackground();
			frame.radioButton_11.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_11.getText();
			
			if("�������Ϸ".equals(frame.radioButton_11.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_12.isSelected()){		
			clearRadiosBackground();
			frame.radioButton_12.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_12.getText();
			
			if("�������Ϸ".equals(frame.radioButton_12.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_13.isSelected()){		
			clearRadiosBackground();
			frame.radioButton_13.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_13.getText();
			
			if("�������Ϸ".equals(frame.radioButton_13.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_14.isSelected()){
			clearRadiosBackground();
			frame.radioButton_14.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_14.getText();
			
			if("�������Ϸ".equals(frame.radioButton_14.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_15.isSelected()){
			clearRadiosBackground();
			frame.radioButton_15.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_15.getText();

			if("�������Ϸ".equals(frame.radioButton_15.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_16.isSelected()){
			clearRadiosBackground();
			frame.radioButton_16.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_16.getText();
			
			if("�������Ϸ".equals(frame.radioButton_16.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_17.isSelected()){
			clearRadiosBackground();
			frame.radioButton_17.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_17.getText();
			
			if("�������Ϸ".equals(frame.radioButton_17.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_18.isSelected()){
			clearRadiosBackground();
			frame.radioButton_18.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_18.getText();
			
			if("�������Ϸ".equals(frame.radioButton_18.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_19.isSelected()){
			clearRadiosBackground();
			frame.radioButton_19.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_19.getText();
			
			if("�������Ϸ".equals(frame.radioButton_19.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_20.isSelected()){
			clearRadiosBackground();
			frame.radioButton_20.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_20.getText();
			
			if("�������Ϸ".equals(frame.radioButton_20.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_21.isSelected()){
			clearRadiosBackground();
			frame.radioButton_21.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_21.getText();
			
			if("�������Ϸ".equals(frame.radioButton_21.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_22.isSelected()){
			clearRadiosBackground();
			frame.radioButton_22.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_22.getText();
			
			if("�������Ϸ".equals(frame.radioButton_22.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_23.isSelected()){
			clearRadiosBackground();
			frame.radioButton_23.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_23.getText();
			
			if("�������Ϸ".equals(frame.radioButton_23.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_24.isSelected()){
			clearRadiosBackground();
			frame.radioButton_24.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_24.getText();
			
			if("�������Ϸ".equals(frame.radioButton_24.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_25.isSelected()){
			clearRadiosBackground();
			frame.radioButton_25.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_25.getText();
			
			if("�������Ϸ".equals(frame.radioButton_25.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}else if(frame.radioButton_26.isSelected()){
			clearRadiosBackground();
			frame.radioButton_26.setBackground(new Color(0, 206, 209));
			frame.editSteamGameName = frame.radioButton_26.getText();
			
			if("�������Ϸ".equals(frame.radioButton_26.getText())){
				showAddSteamGameUI();
				return;
			}
			
		}
		
		/*
		 * ���������õĵ�ѡ��ť�¼�
		 */
		if(frame.closeBroRadioButton.isSelected()){
			frame.exitBrowserAfterRunning = true;
		}
		if(frame.dontCloseRadioButton.isSelected()){
			frame.exitBrowserAfterRunning = false;
		}
		if(frame.showBroRadioButton.isSelected()){
			frame.showBrowserWhenRunning = true;
		}
		if(frame.dontShowRadioButton.isSelected()){
			frame.showBrowserWhenRunning = false;
		}
		
		if(e.getActionCommand() == "�����Ϸ"){
			AddSteamGameUI addSteamGameFrame = new AddSteamGameUI(this.frame);
			try {
				frame.setGames();
			} catch (JDOMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand() == "ɾ������Ϸ"){
			File f = new File("games");
			File[] fileLists = f.listFiles();
			
			//����Map��ȡ��Ӧ��Ϸ���ļ���
			System.out.println(frame.editSteamGameName);
			
			//�������ѡ����Ϸ�����˳�
			if("�������Ϸ".equals(frame.editSteamGameName)){
				return;
			}
			
			String fileName = frame.steamGames.get(frame.editSteamGameName).getFileName();
			System.out.println(fileName);
			for(int i=0;i<fileLists.length;i++){
				System.out.println(fileLists[i].getName());
				if(fileName.equals(fileLists[i].getName())){
					System.out.println(true);
					File file = new File("games\\"+fileName);
					file.delete();
				}
			}
			frame.editSteamGameName = "�������Ϸ";
			
			try {
				frame.setGames();
			} catch (JDOMException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.frame.group.clearSelection();
		}
		
		if(e.getActionCommand() == "�鿴���޸���Ϸ��Ϣ"){
			//�������ѡ����Ϸ�����˳�
			if("�������Ϸ".equals(frame.editSteamGameName)){
				return;
			}
			
			ShowOrEditSteamGameUI showOrEditSteamGameUI = new ShowOrEditSteamGameUI(this.frame);
		}
		
		/*
		 * Code��֤���ֵ��3��������
		 */
		if(e.getActionCommand() == "Code�Զ���ֵ"){
			String codeDefaultAccountID = frame.codeAccountsComboBox.getSelectedItem().toString();
			this.frame.codeDefaultAccountText.setText(codeDefaultAccountID);
			new Thread(new Runnable(){
				@Override
        		public void run(){
        			Wallet wallet = new Wallet(frame);
        			try {
						wallet.autoStart();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JDOMException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	}).start();		
		}
		
		if(e.getActionCommand() == "Code������֤"){
			System.out.println("!!!!��ʼ�ˣ�����");
			new Thread(new Runnable(){
				@Override
        		public void run(){
        			Wallet wallet = new Wallet(frame);
        			try {
						wallet.batchStart();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JDOMException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
        		}
        	}).start();		
		}
		
		//��־ҳ�棬���������־���¼�
		if(e.getActionCommand()=="�����ʷ��¼"){
			this.frame.steamLogTextArea.setText("");
			
			//ɾ����־�ļ�����Ϣ
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(new File("steam.log")));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				bw.append("");
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	/*
	 * ��������ʺ������ı���������Ϊ��ɫ
	 */
	public void clearAccountBackground(){
		this.frame.default_id_text_01.setBackground(new Color(255,255,255));
		this.frame.default_id_text_02.setBackground(new Color(255,255,255));
		this.frame.default_id_text_03.setBackground(new Color(255,255,255));
		this.frame.default_id_text_04.setBackground(new Color(255,255,255));
		this.frame.default_id_text_05.setBackground(new Color(255,255,255));
		this.frame.default_id_text_06.setBackground(new Color(255,255,255));
		
		this.frame.default_pw_text_01.setBackground(new Color(255,255,255));
		this.frame.default_pw_text_02.setBackground(new Color(255,255,255));
		this.frame.default_pw_text_03.setBackground(new Color(255,255,255));
		this.frame.default_pw_text_04.setBackground(new Color(255,255,255));
		this.frame.default_pw_text_05.setBackground(new Color(255,255,255));
		this.frame.default_pw_text_06.setBackground(new Color(255,255,255));	
	}
	
	/*
	 * �������radioButton�ı�������ΪĬ�ϵ�
	 */
	public void clearRadiosBackground(){
		this.frame.radioButton_1.setBackground(new Color(240,240,240));
		this.frame.radioButton_2.setBackground(new Color(240,240,240));
		this.frame.radioButton_3.setBackground(new Color(240,240,240));
		this.frame.radioButton_4.setBackground(new Color(240,240,240));
		this.frame.radioButton_5.setBackground(new Color(240,240,240));
		this.frame.radioButton_6.setBackground(new Color(240,240,240));
		this.frame.radioButton_7.setBackground(new Color(240,240,240));
		this.frame.radioButton_8.setBackground(new Color(240,240,240));
		this.frame.radioButton_9.setBackground(new Color(240,240,240));
		this.frame.radioButton_10.setBackground(new Color(240,240,240));
		this.frame.radioButton_11.setBackground(new Color(240,240,240));
		this.frame.radioButton_12.setBackground(new Color(240,240,240));
		this.frame.radioButton_13.setBackground(new Color(240,240,240));
		this.frame.radioButton_14.setBackground(new Color(240,240,240));
		this.frame.radioButton_15.setBackground(new Color(240,240,240));
		this.frame.radioButton_16.setBackground(new Color(240,240,240));
		this.frame.radioButton_17.setBackground(new Color(240,240,240));
		this.frame.radioButton_18.setBackground(new Color(240,240,240));
		this.frame.radioButton_19.setBackground(new Color(240,240,240));
		this.frame.radioButton_20.setBackground(new Color(240,240,240));
		this.frame.radioButton_21.setBackground(new Color(240,240,240));
		this.frame.radioButton_22.setBackground(new Color(240,240,240));
		this.frame.radioButton_23.setBackground(new Color(240,240,240));
		this.frame.radioButton_24.setBackground(new Color(240,240,240));
		this.frame.radioButton_25.setBackground(new Color(240,240,240));
		this.frame.radioButton_26.setBackground(new Color(240,240,240));
		
	}
	
	/*
	 * radioButton���������Ϸ�Ŀ�
	 */
	public void showAddSteamGameUI(){
		AddSteamGameUI addSteamGameFrame = new AddSteamGameUI(this.frame);
	}
	
	/*
	 * �޸��˻�ʱ�Զ����µĺ���
	 */
	public void updateBuyGameAccounts() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("config\\Accounts_Purchase.txt")));
		
		String default_id;
		String default_pw;
		default_id = this.frame.default_id_text_01.getText();
		default_pw = this.frame.default_pw_text_01.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_02.getText();
		default_pw = this.frame.default_pw_text_02.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_03.getText();
		default_pw = this.frame.default_pw_text_03.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_04.getText();
		default_pw = this.frame.default_pw_text_04.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_05.getText();
		default_pw = this.frame.default_pw_text_05.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_06.getText();
		default_pw = this.frame.default_pw_text_06.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_07.getText();
		default_pw = this.frame.default_pw_text_07.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_08.getText();
		default_pw = this.frame.default_pw_text_08.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_09.getText();
		default_pw = this.frame.default_pw_text_09.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_10.getText();
		default_pw = this.frame.default_pw_text_10.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
			
		bw.close();	
	}
	//�ж�������˻��������Ƿ�Ϊ���ַ���
	public boolean accountNull(String default_id, String default_pw){
		String newId = default_id.replaceAll(" ", "");
		String newPw = default_pw.replaceAll(" ", "");
		if(newId!=null&&!"".equals(newId)&&newPw!=null&&!"".equals(newPw)){
			return true;
		}else{
			return false;
		}
	}
	//����˻��������ı��������
	public void clearAccounts(){
		this.frame.default_id_text_01.setText("");
		this.frame.default_pw_text_01.setText("");
		this.frame.default_id_text_02.setText("");
		this.frame.default_pw_text_02.setText("");
		this.frame.default_id_text_03.setText("");
		this.frame.default_pw_text_03.setText("");
		this.frame.default_id_text_04.setText("");
		this.frame.default_pw_text_04.setText("");
		this.frame.default_id_text_05.setText("");
		this.frame.default_pw_text_05.setText("");
		this.frame.default_id_text_06.setText("");
		this.frame.default_pw_text_06.setText("");
		this.frame.default_id_text_07.setText("");
		this.frame.default_pw_text_07.setText("");
		this.frame.default_id_text_08.setText("");
		this.frame.default_pw_text_08.setText("");
		this.frame.default_id_text_09.setText("");
		this.frame.default_pw_text_09.setText("");
		this.frame.default_id_text_10.setText("");
		this.frame.default_pw_text_10.setText("");		
	}
	
	//���Ĳ�ͬ���ҵĵ�buySteamGameXpath�ļ�����ʼ��buySteamGameXpath�Ĺ�ϣ��
	public void changeBuySteamGameXpath(String filepath) throws FileNotFoundException, IOException{
		frame.buySteamGameXpath = new HashMap<String,String>();
		System.out.println(filepath);
		Properties pros = new Properties();
		pros.load(new FileInputStream(filepath));
		Iterator iter = pros.entrySet().iterator();
		while(iter.hasNext()){
			Entry entry = (Entry) iter.next();
			frame.buySteamGameXpath.put(entry.getKey().toString(), entry.getValue().toString());
		}
		System.out.println(frame.buySteamGameXpath.size());
	}
}
