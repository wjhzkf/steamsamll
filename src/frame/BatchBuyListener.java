package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import script.ToDBC;
import selenium.GameObj;
import bean.SteamGame;

public class BatchBuyListener implements ActionListener {

	private static final int DISPOSE_ON_CLOSE = 0;
	public BatchBuyUI batchFrame;
	public AutoSellerUI frame;
	GameObj gameObj;
	
	public BatchBuyListener(BatchBuyUI batchFrame, AutoSellerUI frame){
		this.batchFrame = batchFrame;
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="ȷ�ϲ�����ֿ�"){
			this.batchFrame.closeDriverTag = false;
			new Thread(new Runnable(){
				@Override
        		public void run(){
//					String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
//					String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
//					String defaultEmail = frame.default_email_text_sure.getText();
//					String steamGameName = frame.gamesComboBox.getSelectedItem().toString();
//					SteamGame steamGame = frame.steamGames.get(steamGameName);
//					
//					GameObj gameObj = new GameObj(batchFrame);
//					gameObj.setDefault_account(defaultAccountID);
//					gameObj.setDefaule_password(defaultAccountPW);
//					gameObj.setDefault_email(ToDBC.ToDBC(defaultEmail));
//					gameObj.setGame_name(steamGameName);
//					gameObj.setBatchFrame(batchFrame);
//					gameObj.setSteamGame(steamGame);
//					gameObj.setBuyGameAccountID(batchFrame.frame.buyGameAccountID);
//					gameObj.setBuyGameAccounts(batchFrame.frame.buyGameAccounts);
//					gameObj.setGameNum(Integer.parseInt(batchFrame.buyNumText.getText()));
//					gameObj.setFrame(frame);
//					gameObj.setBuySteamGameXpath(frame.buySteamGameXpath);
//        			gameObj.batchBuyToWare();
  
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
						balanceHasEnough=gameObj.batchBuyToWare();
	        			currentAcciunt++;
					}
					//����������˳�
					batchFrame.info_buy_process_Text.setText("����������˳���");
					gameObj.exitBrowAfterRunning();
        		}
        	}).start();	
		}
		
		if(e.getActionCommand() == "ȷ�ϲ�����"){
			this.batchFrame.closeDriverTag = false;
			new Thread(new Runnable(){
				@Override
        		public void run(){
//					String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
//					String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
//					String defaultEmail = frame.default_email_text_sure.getText();
//					String steamGameName = ((CheckValue)frame.gamesComboBox.getSelectedItem()).value;
//					SteamGame steamGame = frame.steamGames.get(steamGameName);
//					
//					GameObj gameObj = new GameObj(batchFrame);
//					gameObj.setDefault_account(defaultAccountID);
//					gameObj.setDefaule_password(defaultAccountPW);
//					gameObj.setDefault_email(ToDBC.ToDBC(defaultEmail));
//					gameObj.setGame_name(steamGameName);
//					gameObj.setBatchFrame(batchFrame);
//					gameObj.setSteamGame(steamGame);
//					gameObj.setBuyGameAccountID(batchFrame.frame.buyGameAccountID);
//					gameObj.setBuyGameAccounts(batchFrame.frame.buyGameAccounts);
//					gameObj.setGameNum(Integer.parseInt(batchFrame.buyNumText.getText()));
//					gameObj.setFrame(frame);
//					gameObj.setBuySteamGameXpath(frame.buySteamGameXpath);
//        			gameObj.batchBuy();
					
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
		}
		
		if(e.getActionCommand() == "Ϊ�Լ�����"){
			this.batchFrame.closeDriverTag = false;
			new Thread(new Runnable(){
				@Override
        		public void run(){
//					String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
//					String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
//					String defaultEmail = frame.default_email_text_sure.getText();
//					String steamGameName = ((CheckValue)frame.gamesComboBox.getSelectedItem()).value;
//					SteamGame steamGame = frame.steamGames.get(steamGameName);
//					
//					GameObj gameObj = new GameObj(batchFrame);
//					gameObj.setDefault_account(defaultAccountID);
//					gameObj.setDefaule_password(defaultAccountPW);
//					gameObj.setDefault_email(ToDBC.ToDBC(defaultEmail));
//					gameObj.setGame_name(steamGameName);
//					gameObj.setBatchFrame(batchFrame);
//					gameObj.setSteamGame(steamGame);
//					gameObj.setBuyGameAccountID(batchFrame.frame.buyGameAccountID);
//					gameObj.setBuyGameAccounts(batchFrame.frame.buyGameAccounts);
//					gameObj.setGameNum(Integer.parseInt(batchFrame.buyNumText.getText()));
//					gameObj.setFrame(frame);
//					gameObj.setBuySteamGameXpath(frame.buySteamGameXpath);
//        			gameObj.batchBuyMyself();
					
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
						balanceHasEnough=gameObj.batchBuyMyself();
	        			currentAcciunt++;
					}
					//����������˳�
					batchFrame.info_buy_process_Text.setText("����������˳���");
					gameObj.exitBrowAfterRunning();
  
        		}
        	}).start();	
		}
		
		if(e.getActionCommand() == "��������"){
			this.batchFrame.closeDriverTag = false;
			new Thread(new Runnable(){
				@Override
        		public void run(){
					String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
					String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
					String defaultEmail = frame.default_email_text_sure.getText();
					String steamGameName = frame.gamesComboBox.getSelectedItem().toString();
					String uniSteamGameName = frame.uniGamesComboBox.getSelectedItem().toString();
					
					SteamGame steamGame = frame.steamGames.get(steamGameName);
					SteamGame uniSteamGame = frame.steamGames.get(uniSteamGameName);
					
					GameObj gameObj = new GameObj(batchFrame);
					gameObj.setDefault_account(defaultAccountID);
					gameObj.setDefaule_password(defaultAccountPW);
					gameObj.setDefault_email(ToDBC.ToDBC(defaultEmail));
					gameObj.setGame_name(steamGameName);
					gameObj.setBatchFrame(batchFrame);
					gameObj.setSteamGame(steamGame);
					gameObj.setUniSteamGame(uniSteamGame);
					gameObj.setBuyGameAccountID(batchFrame.frame.buyGameAccountID);
					gameObj.setBuyGameAccounts(batchFrame.frame.buyGameAccounts);
					gameObj.setGameNum(Integer.parseInt(batchFrame.buyNumText.getText()));
					gameObj.setFrame(frame);
					gameObj.setBuySteamGameXpath(frame.buySteamGameXpath);
        			gameObj.jumpAreaBuy();
  
        		}
        	}).start();	
		}
		
		if(e.getActionCommand() == "ȡ��"){
			System.out.println(batchFrame.closeDriverTag);
			if(!batchFrame.closeDriverTag){
				batchFrame.closeDriverTag = true;
				batchFrame.info_buy_process_Text.setText("�����������ڹر�����������Եȣ�������");
			}else{
				this.batchFrame.dispose();
				this.frame.setEnabled(true);
			}
			
//			this.BpFrame.dispose();
		}
	}

}
