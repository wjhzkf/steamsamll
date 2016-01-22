package taobao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.taobao.api.internal.util.WebUtils;

import json.JsonParser;

// should be AuthorizeController

public class ShouquanController extends Thread{

	private static ShouquanController shouquancontroller = new ShouquanController();
	private ShouquanController(){
		
	}
	public static ShouquanController getInstance(){
		if(shouquancontroller==null){
			shouquancontroller = new ShouquanController();
		}
		return shouquancontroller;
	}
	String sessionKey = "6200725886354102840c4b1c9484287b3ZZfe0d1aeaacfa693314641";
	
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) throws IOException {
		this.sessionKey = sessionKey;		
	}
	
	public String getSessionKey(String shouquan){
		JsonParser jp = new JsonParser();	

//		String shouquan = "rrxNDSc4hSw4D96PgslWumil541381";
		String responseJson = null;
		try {
			responseJson = getSession(shouquan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		sessionKey = jp.getSessionKey(responseJson);
		System.out.println("sessionKey:"+sessionKey);
		return sessionKey;
	}
	

	String filepath = "session.txt";
	public String getSession(String shouquan) throws Exception{
		String appKey = "21784353"; // 在阿里软件平台注册的应用ID,请使用你自己的应用ID
		String appSecret = "6d5fb38a798aabce820bb84ab746c593"; // 注册的应用的密钥，请使用你自己应用的密钥	
		String redirect_uri = "www.d3buy.net";
		String tbPostSessionUrl = "https://oauth.taobao.com/token";
		
		String state = "1212";
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "authorization_code");
		param.put("code", shouquan);
		param.put("client_id", appKey);
		param.put("client_secret", appSecret);
		param.put("redirect_uri", redirect_uri);
		param.put("view", "web");
//		param.put("state", state);
		String responseJson=WebUtils.doPost(tbPostSessionUrl, param, 3000, 3000);
		System.out.println(responseJson);
		return responseJson;
	}
	
//	public String refreshSession() throws Exception{
//		String refresh_token = "6101a03690e1b87402826bcfacee496eba0259cd558918b693314641";
//		String refreshTokenUrl = "http://gw.api.taobao.com/router/rest";
//		String appKey = "21784353"; // 在阿里软件平台注册的应用ID,请使用你自己的应用ID
//		String appSecret = "6d5fb38a798aabce820bb84ab746c593"; // 注册的应用的密钥，请使用你自己应用的密钥	
//	
//		Hashtable<String, String> param = new Hashtable<String, String>();
//		param.put("grant_type", "refresh_token");
//		param.put("refresh_token", refresh_token);
//		param.put("client_id", appKey);
//		param.put("client_secret", appSecret);
//		param.put("view", "web");
//		String response = WebUtils.doGet(refreshTokenUrl, param);
//		System.out.println(response);
//		return response;
//	}
	
	public void setSessionKeyFile(String sessionKey)throws IOException{
	     File file=new File(filepath);
         if(!file.exists())
             file.createNewFile();
        	 
         FileOutputStream out=new FileOutputStream(file);        
          
         out.write(sessionKey.getBytes());
         out.close();
	    
	}
	
	public String getSessionKeyfromFile()	throws IOException{
	         File file=new File(filepath);
	         if(!file.exists()||file.isDirectory())
	             System.out.println("wrong file path");
	         BufferedReader br=new BufferedReader(new FileReader(file));	         
	         String sessionKey=br.readLine();         
	         return sessionKey;		 
	}
}
