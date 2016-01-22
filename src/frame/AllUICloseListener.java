package frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * 打开小窗口以后，大窗口不允许点击
 */
public class AllUICloseListener extends WindowAdapter {
	public AutoSellerUI frame;
	
	public AllUICloseListener(AutoSellerUI frame){
		this.frame = frame;
	}
	
	public void windowClosing(WindowEvent e){
		this.frame.setEnabled(true);
		this.frame.group.clearSelection();
	}
}
