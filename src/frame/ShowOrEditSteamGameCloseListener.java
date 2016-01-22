package frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.JDOMException;

import bean.SteamGame;

public class ShowOrEditSteamGameCloseListener extends WindowAdapter {
	private ShowOrEditSteamGameUI showOrEditSteamGame;
	
	public ShowOrEditSteamGameCloseListener(){}
	
	public ShowOrEditSteamGameCloseListener(ShowOrEditSteamGameUI showOrEditSteamGame)
    {
        this.showOrEditSteamGame = showOrEditSteamGame;
    }
	
	public void windowClosing(WindowEvent e){
		showOrEditSteamGame.infoText.setText("�������޸���Ϸ�����Եȣ�");
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
			showOrEditSteamGame.infoText.setText("��������ȷ����Ϸ��ַ��xpath����Ϸ���ƣ�����Ϊ�գ�");
			return;
		}
		
		SteamGame steamGame = showOrEditSteamGame.steamGame;
		System.out.println(steamGame.getFileName());
		
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter(new File("games\\"+steamGame.getFileName())));				
		}catch(Exception e1){
			e1.printStackTrace();
			showOrEditSteamGame.infoText.setText("�����ļ����ҳ��������ԣ���");
			return;
		}
		
		try {
			bw.append(link+"\r\n"+xpath+"\r\n"+name+"\r\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			showOrEditSteamGame.infoText.setText("�����ļ�д����������ԣ���");
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
		http://store.steampowered.com/app/91342/
		try {
			showOrEditSteamGame.frame.setGames();
		} catch (JDOMException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			showOrEditSteamGame.infoText.setText("���޸���Ϸ�ɹ���ˢ��ҳ����������´������");
		}
		this.showOrEditSteamGame.dispose();
		this.showOrEditSteamGame.frame.setEnabled(true);
	}
}
