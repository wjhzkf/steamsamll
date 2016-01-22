package frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import json.ToDBC;
import taobao.TaobaoController;

public class MyTableListener implements MouseListener {

	private AutoSellerUI frame;
    TaobaoController tbc =TaobaoController.getInstance();
    ToDBC getEmail = ToDBC.getInstance();
    
    public MyTableListener(AutoSellerUI frame){
    	this.frame = frame;
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(frame.table.getSelectedRow());
		this.frame.table_gameName_text.setText((String) frame.table.getValueAt(frame.table.getSelectedRow(), 2));
		this.frame.table_gameNum_text.setText((String) frame.table.getValueAt(frame.table.getSelectedRow(), 7));
		this.frame.table_buyer_id_text.setText((String) frame.table.getValueAt(frame.table.getSelectedRow(), 3));
		this.frame.table_buyer_message_text.setText((String) frame.table.getValueAt(frame.table.getSelectedRow(), 6));
		this.frame.table_seller_message_text.setText((String) frame.table.getValueAt(frame.table.getSelectedRow(), 8));
		
		String seller_message = (String) frame.table.getValueAt(frame.table.getSelectedRow(), 8);
		String buyer_message = (String) frame.table.getValueAt(frame.table.getSelectedRow(), 6);
		String email = "";
		if(!"".equals(buyer_message)&&null!=buyer_message){
			email = this.getEmail.ExEmail(this.getEmail.ToDBC(buyer_message));
			this.frame.table_buyer_email_text.setText(email);
		}else if(!"".equals(seller_message)&&null!=seller_message){
			email = this.getEmail.ExEmail(this.getEmail.ToDBC(seller_message));
			this.frame.table_buyer_email_text.setText(email);
		}else{
			this.frame.table_buyer_email_text.setText(email);
		}
		
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
