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
		if(e.getActionCommand() == "ȷ���޸���Ϸ"){
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
			
			try {
				showOrEditSteamGame.frame.setGames();
			} catch (JDOMException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				showOrEditSteamGame.infoText.setText("���޸���Ϸ�ɹ���ˢ��ҳ����������´������");
			}
			
			showOrEditSteamGame.infoText.setText("�����޸���Ϸ�ɹ����˳�������޸ģ���");
			
		}
		
		if(e.getActionCommand() == "��������"){
			showOrEditSteamGame.gameLinkText.setText("");
			showOrEditSteamGame.gameNameText.setText("");
			showOrEditSteamGame.gameXPathText.setText("");
		}
		
			
	}
}
