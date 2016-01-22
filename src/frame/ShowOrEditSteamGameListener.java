package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.JDOMException;

import bean.SteamGame;

public class ShowOrEditSteamGameListener implements ActionListener,  ItemListener{
	private ShowOrEditSteamGameUI showOrEditSteamGame;
//	private Adder frame;
	
    public ShowOrEditSteamGameListener(){}
    
    public ShowOrEditSteamGameListener(ShowOrEditSteamGameUI showOrEditSteamGame)
    {
        this.showOrEditSteamGame = showOrEditSteamGame;
    }
    
	@Override
	public void itemStateChanged(ItemEvent e) {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "确认修改游戏"){
			showOrEditSteamGame.infoText.setText("！正在修改游戏，请稍等！");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			String link = showOrEditSteamGame.gameLinkText.getText();
			String xpath = showOrEditSteamGame.gameXPathText.getText();
			String name = showOrEditSteamGame.gameNameText.getText();
			if("".equals(link)||link==null||"".equals(xpath)||xpath==null||"".equals(name)||name==null){
				showOrEditSteamGame.infoText.setText("！输入正确的游戏地址、xpath和游戏名称，不能为空！");
				return;
			}
			
			SteamGame steamGame = showOrEditSteamGame.steamGame;
			System.out.println(steamGame.getFileName());
			
			BufferedWriter bw = null;
			try{
				bw = new BufferedWriter(new FileWriter(new File("games\\"+steamGame.getFileName())));				
			}catch(Exception e1){
				e1.printStackTrace();
				showOrEditSteamGame.infoText.setText("！！文件查找出错，请重试！！");
				return;
			}
			
			try {
				bw.append(link+"\r\n"+xpath+"\r\n"+name+"\r\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				showOrEditSteamGame.infoText.setText("！！文件写入出错，请重试！！");
				e1.printStackTrace();
				return;
			}finally{
				try {
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
			}
			
			try {
				showOrEditSteamGame.frame.setGames();
			} catch (JDOMException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				showOrEditSteamGame.infoText.setText("！修改游戏成功，刷新页面出错，请重新打开软件！");
			}
			
			showOrEditSteamGame.infoText.setText("！！修改游戏成功，退出或继续修改！！");
			
		}
		
		if(e.getActionCommand() == "清空输入框"){
			showOrEditSteamGame.gameLinkText.setText("");
			showOrEditSteamGame.gameNameText.setText("");
			showOrEditSteamGame.gameXPathText.setText("");
		}
		
			
	}
}
