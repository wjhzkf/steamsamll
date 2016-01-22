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
		if(e.getActionCommand() == "ȷ�������Ϸ"){
			addSteamGameframe.infoText.setText("�������������Ϸ�����Եȣ�");
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
				addSteamGameframe.infoText.setText("��������ȷ����Ϸ��ַ��xpath����Ϸ���ƣ�����Ϊ�գ�");
				return;
			}
			
			BufferedWriter bw = null;
			try{
				bw = new BufferedWriter(new FileWriter(new File("games\\"+name+".txt")));				
			}catch(Exception e1){
				e1.printStackTrace();
				addSteamGameframe.infoText.setText("�����ļ��������������ԣ���");
				return;
			}
			
			try {
				bw.append(link+"\r\n"+xpath+"\r\n"+name+"\r\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				addSteamGameframe.infoText.setText("�����ļ�д����������ԣ���");
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
				addSteamGameframe.infoText.setText("�������Ϸ�ɹ���ˢ��ҳ����������´������");
			}
			
			addSteamGameframe.dispose();
			addSteamGameframe.frame.setEnabled(true);
			addSteamGameframe.frame.group.clearSelection();
			
		}
		
		if(e.getActionCommand() == "��������"){
			addSteamGameframe.gameLinkText.setText("");
			addSteamGameframe.gameNameText.setText("");
			addSteamGameframe.gameXPathText.setText("");
		}
		
			
	}
}
