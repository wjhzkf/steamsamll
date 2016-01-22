package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import script.ToDBC;
import selenium.GameObj;
import bean.SteamGame;

public class WalletAlertListener implements ActionListener {

	private static final int DISPOSE_ON_CLOSE = 0;
	public WalletAlertUI walletAlertFrame;
	GameObj gameObj;
	
	public WalletAlertListener(WalletAlertUI walletAlertFrame){
		this.walletAlertFrame = walletAlertFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "È¡Ïû"){
			this.walletAlertFrame.dispose();
		}
	}

}
