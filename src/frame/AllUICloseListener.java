package frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * ��С�����Ժ󣬴󴰿ڲ�������
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
