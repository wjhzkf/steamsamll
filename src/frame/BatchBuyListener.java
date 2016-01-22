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
		if(e.getActionCommand()=="确认并加入仓库"){
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
  
					//获取有多少个用户
					int accountNum=frame.accountsComboBox.getItemCount();
					//获取当前用户在第几个
					int currentAcciunt=frame.accountsComboBox.getSelectedIndex();
					//余额是否足够,true余额足够
					boolean balanceHasEnough=false;
					//游戏名
					String steamGameName = ((CheckValue)frame.gamesComboBox.getSelectedItem()).value;
					GameObj gameObj=null;
					//如果余额不够跳到下一个账户
					while (!balanceHasEnough&&currentAcciunt<accountNum) {
						//将当前账户设为选中
						frame.accountsComboBox.setSelectedIndex(currentAcciunt);
						batchFrame.setText();
						
						String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
						String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
						String defaultEmail = frame.default_email_text_sure.getText();
						
						
						 gameObj = new GameObj(batchFrame);
						
						//查询该账户需要购买多少个游戏
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
					//购买结束，退出
					batchFrame.info_buy_process_Text.setText("购买结束！退出！");
					gameObj.exitBrowAfterRunning();
        		}
        	}).start();	
		}
		
		if(e.getActionCommand() == "确认并购买"){
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
					
					//获取有多少个用户
					int accountNum=frame.accountsComboBox.getItemCount();
					//获取当前用户在第几个
					int currentAcciunt=frame.accountsComboBox.getSelectedIndex();
					//余额是否足够,true余额足够
					boolean balanceHasEnough=false;
					//游戏名
					String steamGameName = ((CheckValue)frame.gamesComboBox.getSelectedItem()).value;
					GameObj gameObj=null;
					//如果余额不够跳到下一个账户
					while (!balanceHasEnough&&currentAcciunt<accountNum) {
						//将当前账户设为选中
						frame.accountsComboBox.setSelectedIndex(currentAcciunt);
						batchFrame.setText();
						
						String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
						String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
						String defaultEmail = frame.default_email_text_sure.getText();
						
						
						 gameObj = new GameObj(batchFrame);
						
						//查询该账户需要购买多少个游戏
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
					//购买结束，退出
					batchFrame.info_buy_process_Text.setText("购买结束！退出！");
					gameObj.exitBrowAfterRunning();
  
        		}
        	}).start();	
		}
		
		if(e.getActionCommand() == "为自己购买"){
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
					
					//获取有多少个用户
					int accountNum=frame.accountsComboBox.getItemCount();
					//获取当前用户在第几个
					int currentAcciunt=frame.accountsComboBox.getSelectedIndex();
					//余额是否足够,true余额足够
					boolean balanceHasEnough=false;
					//游戏名
					String steamGameName = ((CheckValue)frame.gamesComboBox.getSelectedItem()).value;
					GameObj gameObj=null;
					//如果余额不够跳到下一个账户
					while (!balanceHasEnough&&currentAcciunt<accountNum) {
						//将当前账户设为选中
						frame.accountsComboBox.setSelectedIndex(currentAcciunt);
						batchFrame.setText();
						
						String defaultAccountID = frame.accountsComboBox.getSelectedItem().toString();
						String defaultAccountPW = frame.buyGameAccounts.get(defaultAccountID);
						String defaultEmail = frame.default_email_text_sure.getText();
						
						
						 gameObj = new GameObj(batchFrame);
						
						//查询该账户需要购买多少个游戏
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
					//购买结束，退出
					batchFrame.info_buy_process_Text.setText("购买结束！退出！");
					gameObj.exitBrowAfterRunning();
  
        		}
        	}).start();	
		}
		
		if(e.getActionCommand() == "跳区购买"){
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
		
		if(e.getActionCommand() == "取消"){
			System.out.println(batchFrame.closeDriverTag);
			if(!batchFrame.closeDriverTag){
				batchFrame.closeDriverTag = true;
				batchFrame.info_buy_process_Text.setText("！！！！正在关闭浏览器，请稍等！！！！");
			}else{
				this.batchFrame.dispose();
				this.frame.setEnabled(true);
			}
			
//			this.BpFrame.dispose();
		}
	}

}
