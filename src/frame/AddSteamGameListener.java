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

public class AddSteamGameListener implements ActionListener,  ItemListener{
	private AddSteamGameUI addSteamGameframe;
//	private Adder frame;
	
    public AddSteamGameListener(){}
    
    public AddSteamGameListener(AddSteamGameUI addSteamGameframe)
    {
        this.addSteamGameframe = addSteamGameframe;
    }
    
	@Override
	public void itemStateChanged(ItemEvent e) {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "确认添加游戏"){
			addSteamGameframe.infoText.setText("！正在添加新游戏，请稍等！");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			String link = addSteamGameframe.gameLinkText.getText();
			String xpath = addSteamGameframe.gameXPathText.getText();
			String name = addSteamGameframe.gameNameText.getText();
			if("".equals(link)||link==null||"".equals(xpath)||xpath==null||"".equals(name)||name==null){
				addSteamGameframe.infoText.setText("！输入正确的游戏地址、xpath和游戏名称，不能为空！");
				return;
			}
			
			BufferedWriter bw = null;
			try{
				bw = new BufferedWriter(new FileWriter(new File("games\\"+name+".txt")));				
			}catch(Exception e1){
				e1.printStackTrace();
				addSteamGameframe.infoText.setText("！！文件创建出错，请重试！！");
				return;
			}
			
			try {
				bw.append(link+"\r\n"+xpath+"\r\n"+name+"\r\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				addSteamGameframe.infoText.setText("！！文件写入出错，请重试！！");
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
				addSteamGameframe.frame.setGames();
			} catch (JDOMException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				addSteamGameframe.infoText.setText("！添加游戏成功，刷新页面出错，请重新打开软件！");
			}
			
			addSteamGameframe.dispose();
			addSteamGameframe.frame.setEnabled(true);
			addSteamGameframe.frame.group.clearSelection();
			
		}
		
		if(e.getActionCommand() == "清空输入框"){
			addSteamGameframe.gameLinkText.setText("");
			addSteamGameframe.gameNameText.setText("");
			addSteamGameframe.gameXPathText.setText("");
		}
		
			
	}
}
