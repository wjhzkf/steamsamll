/**
 * 
 */
package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

/**
 * @author wjh
 *
 */
public class MyComboBox extends JComboBox implements ActionListener {
	public ActionListener boxListener;
	public MyComboBox() {
		addItem(new CheckValue(false, "Select All"));
		boxListener=new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				itemSelected();
			}
		};
		this.addActionListener(boxListener);
	}

	//移除所有元素
	public void removeAllBox() {
		this.removeActionListener(boxListener);
		this.removeAllItems();
		addItem(new CheckValue(false, "Select All"));
	}
	private void itemSelected() {
		if (getSelectedItem() instanceof CheckValue) {
			if (getSelectedIndex() == 0) {
				selectedAllItem();
			} else {
				CheckValue jcb = (CheckValue) getSelectedItem();
				jcb.bolValue = (!jcb.bolValue);
				setSelectedIndex(getSelectedIndex());
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					/* 选中后依然保持当前弹出状态 */
					showPopup();
				}
			});
		}
	}

	private void selectedAllItem() {
		boolean bl = false;
		for (int i = 0; i < getItemCount(); i++) {
			CheckValue jcb = (CheckValue) getItemAt(i);
			if (i == 0) {
				bl = !jcb.bolValue;
			}
			jcb.bolValue = (bl);
		}
		setSelectedIndex(0);
	}

	/* 获取选取的对象 */
	public Vector getComboVc() {
		Vector vc = new Vector();
		for (int i = 1; i < getItemCount(); i++) {
			CheckValue jcb = (CheckValue) getItemAt(i);
			if (jcb.bolValue) {
				vc.add(jcb.value);
			}
		}
		return vc;
	}
}