package script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import frame.AutoSellerUI;

public class SteamLog {
	private String message;
	private Date date;
	private AutoSellerUI frame;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public SteamLog(AutoSellerUI frame){
		this.frame = frame;
	}
	
	public AutoSellerUI getFrame() {
		return frame;
	}

	public void setFrame(AutoSellerUI frame) {
		this.frame = frame;
	}

	/*
	 * �����ڸ�ʽ���ĺ���
	 */
	public String formatDate(){
		Date preDate = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String formatDate = format.format(preDate);
		return formatDate;
	}
	/*
	 * ������־��������
	 */
	public void insertLog(){
		String dateNow = formatDate();
		
		//����frame����־������
		String frameLog = this.frame.steamLogTextArea.getText();
		
		File logFile = new File("steam.log");
		if(!logFile.exists()){
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				System.out.println("�ļ�����ʧ�ܣ�");
			}
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(logFile, true));
		} catch (IOException e) {
			System.out.println("�ļ���д�쳣��");
		}
		
		try {
			this.frame.steamLogTextArea.setText(frameLog+dateNow+"��"+this.message+"\n");
			bw.append(dateNow+"��"+this.message+"\r\n");
			bw.close();
		} catch (IOException e) {
			System.out.println("��־д��ʧ�ܣ�");
		}
	}
}
