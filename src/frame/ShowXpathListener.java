package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.JDOMException;

import script.ToDBC;
import selenium.GameObj;
import selenium.TaobaoAuto;
import selenium.Wallet;
import taobao.ShouquanController;
import taobao.TaobaoController;
import taobao.Tid;
import bean.SteamGame;

import com.taobao.api.ApiException;

public class ShowXpathListener implements ActionListener,  ItemListener{
	private ShowXpathUI showXpathUI;
//	private Adder frame;
    TaobaoController tbc = TaobaoController.getInstance();
    private int columnrow = 0;
    
    public ShowXpathListener(){}
    
    public ShowXpathListener(ShowXpathUI showXpathUI)
    {
        this.showXpathUI = showXpathUI;
    }
    
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(showXpathUI.xpathComboBox.getSelectedItem()!=null){
			String xpathName = showXpathUI.xpathComboBox.getSelectedItem().toString();
			String xpath = null;
			if("SteamGameXpath：".equals(this.showXpathUI.infoLabel.getText())){
				xpath = showXpathUI.buySteamGameXpath.get(xpathName);
			}else if("WalletXpath：".equals(this.showXpathUI.infoLabel.getText())){
				xpath = showXpathUI.WalletXpath.get(xpathName);
			}		
			showXpathUI.xpathTextField.setText(xpath);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="点击获取淘宝授权"){
			
			new Thread(){
				public void run(){
					TaobaoAuto tbAuto = new TaobaoAuto();
					tbAuto.setAutoUrl("https://oauth.taobao.com/authorize?response_type=code&client_id=21784353&redirect_uri=urn:ietf:wg:oauth:2.0:oob&state=1212");
					tbAuto.getTaobaoAuto();
				}
			}.start();
								
		}
		
	}
	
}
