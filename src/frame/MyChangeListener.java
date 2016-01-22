package frame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Tab�����Ĵ�����
 */
public class MyChangeListener implements ChangeListener {

	public AutoSellerUI frame;
	public static int currentSelectedID = 0;
	public MyChangeListener(AutoSellerUI frame){
		this.frame = frame;
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		if(this.frame.tabbedPane.getSelectedIndex()!=1){
			System.out.println("�޸��˻�");
			
			//�Ƚ��޸ĺ���˻�������������ļ��������¼���
			try {
				updateBuyGameAccounts();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//������������ݣ��ٶ����˻���Ϣ
			clearAccounts();
			this.frame.getDefaultAccount();
			System.out.println("�����˻�"+this.frame.buyGameAccountID.size());
		}
	}
	
	/*
	 * �޸��˻�ʱ�Զ����µĺ���
	 */
	public void updateBuyGameAccounts() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("config\\Accounts_Purchase.txt")));
		
		String default_id;
		String default_pw;
		default_id = this.frame.default_id_text_01.getText();
		default_pw = this.frame.default_pw_text_01.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_02.getText();
		default_pw = this.frame.default_pw_text_02.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_03.getText();
		default_pw = this.frame.default_pw_text_03.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_04.getText();
		default_pw = this.frame.default_pw_text_04.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_05.getText();
		default_pw = this.frame.default_pw_text_05.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_06.getText();
		default_pw = this.frame.default_pw_text_06.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_07.getText();
		default_pw = this.frame.default_pw_text_07.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_08.getText();
		default_pw = this.frame.default_pw_text_08.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_09.getText();
		default_pw = this.frame.default_pw_text_09.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
		default_id = this.frame.default_id_text_10.getText();
		default_pw = this.frame.default_pw_text_10.getText();
		if(accountNull(default_id, default_pw)){
			bw.append(default_id+"-"+default_pw+"\r\n");
		}
			
		bw.close();	
	}
	
	//�ж�������˻��������Ƿ�Ϊ���ַ���
	public boolean accountNull(String default_id, String default_pw){
		String newId = default_id.replaceAll(" ", "");
		String newPw = default_pw.replaceAll(" ", "");
		if(newId!=null&&!"".equals(newId)&&newPw!=null&&!"".equals(newPw)){
			return true;
		}else{
			return false;
		}
	}
	
	//����˻��������ı��������
	public void clearAccounts(){
		this.frame.default_id_text_01.setText("");
		this.frame.default_pw_text_01.setText("");
		this.frame.default_id_text_02.setText("");
		this.frame.default_pw_text_02.setText("");
		this.frame.default_id_text_03.setText("");
		this.frame.default_pw_text_03.setText("");
		this.frame.default_id_text_04.setText("");
		this.frame.default_pw_text_04.setText("");
		this.frame.default_id_text_05.setText("");
		this.frame.default_pw_text_05.setText("");
		this.frame.default_id_text_06.setText("");
		this.frame.default_pw_text_06.setText("");
		this.frame.default_id_text_07.setText("");
		this.frame.default_pw_text_07.setText("");
		this.frame.default_id_text_08.setText("");
		this.frame.default_pw_text_08.setText("");
		this.frame.default_id_text_09.setText("");
		this.frame.default_pw_text_09.setText("");
		this.frame.default_id_text_10.setText("");
		this.frame.default_pw_text_10.setText("");		
	}
}

