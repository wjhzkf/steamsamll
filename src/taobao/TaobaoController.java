package taobao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import json.JsonParser;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.LogisticsDummySendRequest;
import com.taobao.api.request.TradeMemoAddRequest;
import com.taobao.api.request.TradeMemoUpdateRequest;
import com.taobao.api.response.LogisticsDummySendResponse;
import com.taobao.api.response.TradeMemoAddResponse;
import com.taobao.api.response.TradeMemoUpdateResponse;

import frame.AutoSellerUI;

public class TaobaoController extends Thread{
/**
 * 1 get shouquan
 * 2 get session key
 * 3 use session key & tradesold to get tid_s
 * 4 use every tid & (trade||tradefullinfo) to get info
 */
	private static TaobaoController tbc= new TaobaoController();
	
	String testUrl = "http://gw.api.tbsandbox.com/router/rest";// 沙箱

	String url = "http://gw.api.taobao.com/router/rest";
	String appkey = "21784353"; // 在阿里软件平台注册的应用ID,请使用你自己的应用ID
	String appSecret = "6d5fb38a798aabce820bb84ab746c593"; // 注册的应用的密钥，请使用你自己应用的密钥
	String taobaoApiName = "taobao.trade.get"; // 交易类api
	String startTime_str;
	String endTime_str;
	private int columnrow = 0;
	
	public AutoSellerUI frame;

//	String sessionKey;
	static ArrayList<String> tids = null;
	static ShouquanController shouquancontroller = ShouquanController.getInstance();
	public TaobaoController(){}
	public TaobaoController(AutoSellerUI frame){
		this.frame = frame;
	}
	
	public void stopDPthread(){
		
	}
	public static TaobaoController getInstance(){
		if(tbc==null){
			tbc = new TaobaoController();
		}
		return tbc;
	}
	public String getTestUrl() {
		return testUrl;
	}
	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getTaobaoApiName() {
		return taobaoApiName;
	}
	public void setTaobaoApiName(String taobaoApiName) {
		this.taobaoApiName = taobaoApiName;
	}
	
	public String getStartTime_str() {
		return startTime_str;
	}

	public void setStartTime_str(String startTime_str) {
		this.startTime_str = startTime_str;
	}

	public String getEndTime_str() {
		return endTime_str;
	}

	public void setEndTime_str(String endTime_str) {
		this.endTime_str = endTime_str;
	}

	public ArrayList<String> control_get_tids(long page){
		
		//设置获取订单时间的区间，24小时内
		Date start_time = new Date(System.currentTimeMillis()-86400000);			//开始时间是24小时前
		Date end_Date = new Date(System.currentTimeMillis());						//结束时间是当前时间
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.startTime_str = format.format(start_time);
		this.endTime_str = format.format(end_Date);
		
		this.startTime_str = this.frame.startTimeField.getText();
		this.endTime_str = this.frame.endTimeField.getText();		
		
		System.out.println("start time string =" + this.startTime_str);
		System.out.println("end time string =" + this.endTime_str);
		
		ShouquanController sqc = ShouquanController.getInstance();
    	String sessionKey ="";
    	try {
			sessionKey = sqc.getSessionKeyfromFile();
			System.out.println(sessionKey);
			sqc.setSessionKey(sessionKey);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	try {
//    		tid_str = tbc.getTids();
    		tids = tbc.getTids(startTime_str,endTime_str,page);
    		System.out.println("tids:"+tids);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//    	startTime_str = endTime_str;
//    	endTime_str = increMin(3,endTime_str);
//    	System.out.println("start time"+startTime_str+"--new end time:"+endTime_str);
    	try { Thread.sleep ( 300 ) ; 
		} catch (InterruptedException ie){}
    	
    	return tids;
	}
	
	
	public String increMin(int min,String endTime_str){
		 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   

		 Date date = null;  
         try {  
             date = df.parse(endTime_str);  
         }catch (ParseException e) {  
             e.printStackTrace();  
         }  
         long date_long =  date.getTime() ;
	     
		 long endTime= date_long + min * 60 * 1000;
		 
		 try
		 {
			 Date d=new Date();   
			 String str = df.format(d);			 
		     Date d1 = df.parse(str);
		     Date d2 = df.parse(df.format(endTime));
		     long diff = d2.getTime() - d1.getTime();
		     while(diff>0){
		    	 try { Thread.sleep ( 9000 ) ; 
		 		} catch (InterruptedException ie){}
		    	 d=new Date();   
				 str = df.format(d);			 
		    	 d1 = df.parse(str);
			     diff = d2.getTime() - d1.getTime();
		     }
		 }
		 catch (Exception e)
		 {
		 }
		 
		 return df.format(endTime);  		 	
	}
	
	
	/*
	 * 刷新函数，用于显示订单
	 * 调用autoSell程序，在调用loopTid程序，loopTid程序要重新编写，因为在原loopTid程序中有自动发货的对象调用
	 * 下面函数一个是autoSell函数重写，一个是loopTid程序的重写，删除了自动发货的调用
	 * 
	 * 注意：每次点刷新都重新读取数据
	 * 所以开始都直接获取订单
	 * 获取完订单后输出到表格中
	 */
	public void newAutoSell(AutoSellerUI frame){
		long page = 1L;
		control_get_tids(page);
		
		if(tids!=null&&tids.size()>0){
			
			newLoopTid(frame);
		}else{
			System.out.println("没有查到相应订单，请稍后再刷新！");
		}
//		if(tids!=null&&tids.size()>1){
//			while(newLoopTid(frame)){
//				page++;
//				System.out.println("another page");
//	    		control_get_tids(page);	    		
//			}
//			if(!newLoopTid(frame)){
//				newAutoSell(frame);
//			}
//		}
//		else if(tids==null||tids.size()<=1){
//    		
//    		System.out.println("another cycle!!!!!!");
//    		control_get_tids(page);
//    		try { Thread.sleep ( 300 ) ; 
//			} catch (InterruptedException ie){}
//    		newAutoSell(frame);
//    	}
	}
	
	public boolean newLoopTid(AutoSellerUI frame){
		for(int i=0;i<tids.size()-1;i++){
			long tid = Long.parseLong((String)tids.get(i));
//			frame.auto_dia_tid_text.setText(tid+"");
//			frame.auto_buyer_trade_tradeInfo_text.setText(tid+"");
//			tids.remove(0);
			System.out.println("tid"+tid);
			Tid.getInstance().setTid(tid);
//			long tid = 667982417887966L;
        	TradeTable tt= tbc.getInfo(tid); 
        	if(tt==null){
        		continue;
        	}
//    		frame.setAdder(tt.getArr(), columnrow);   
        	
        	frame.setAdder(tt.getNewArr(), columnrow);
//        	frame.setAdder(tt.getNewArr(), columnrow);                         //调用新的函数
        	
    		System.out.println(columnrow+"::"+tt.getNewArr());
       	               	
		}
		if(tids.get(tids.size()-1).equals("true"))
			return true;
		return false;
	}
	
	
	public void autoSell(AutoSellerUI frame){
		long page = 1L;
		if(tids!=null&&tids.size()>1){
			while(loopTid(frame)){
				page++;
				System.out.println("another page");
	    		control_get_tids(page);	    		
			}
			if(!loopTid(frame)){
				autoSell(frame);
			}
		}
		else if(tids==null||tids.size()<=1){
    		
    		System.out.println("another cycle!!!!!!");
    		control_get_tids(page);
    		try { Thread.sleep ( 300 ) ; 
			} catch (InterruptedException ie){}
    		autoSell(frame);
    	}
	}
	
	public boolean loopTid(AutoSellerUI frame){
		for(int i=0;i<tids.size()-1;i++){
			long tid = Long.parseLong((String)tids.get(i));
//			frame.auto_dia_tid_text.setText(tid+"");
//			frame.auto_buyer_trade_tradeInfo_text.setText(tid+"");
//			tids.remove(0);
			System.out.println("tid"+tid);
			Tid.getInstance().setTid(tid);
//			long tid = 667982417887966L;
        	TradeTable tt= tbc.getInfo(tid); 
        	if(tt==null){
        		continue;
        	}
//    		frame.setAdder(tt.getArr(), columnrow);   
        	frame.setAdder(tt.getNewArr(), columnrow);                         //调用新的函数
        	
    		System.out.println(columnrow+"::"+tt.getNewArr());
    		//deal with every tid
        	try {
        		
//				try { Thread.sleep ( 300 ) ; 
//				} catch (InterruptedException ie){}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   
        	
//    		try { Thread.sleep ( 3000 ) ; 
//			} catch (InterruptedException ie){}
        	columnrow++;
        	if(columnrow==5){
        		columnrow = 0;
        	}          	               	
		}
		if(tids.get(tids.size()-1).equals("true"))
			return true;
		return false;
	}
	public TradeTable getInfo(long tid ){
		//3
		//4
		// 淘宝Open API平台即SIP平台的入口
		// String sipRequestUrl = "http://sip.alisoft.com/sip/rest";		
//		String sessionKey = "6202516574f79452cf4075b7ZZ70fe886203a845e08925d693314641";

		Trade trade = new Trade();
		GetTrade gettrade = new GetTrade();
		System.out.println();
	    trade  = gettrade.getInfo(tid,url, appkey, appSecret, shouquancontroller.getSessionKey(),trade);
	//use tfi and trade to make the display object
		
		TradeTable tt = new TradeTable();
		
		//测试TradeFullInfo
		TradeFullInfo tfi = new TradeFullInfo();
		GetTradeFullInfo gettfi = new GetTradeFullInfo();
		tfi = gettfi.getInfo(tid, url, appkey, appSecret, shouquancontroller.getSessionKey(), tfi);
		
		/*
		 * 调用新的TradeTable方法
		 * table属性：订单号，创建时间，商品名，买家旺旺，市收款，交易状态，收货地址等等
		 */
		tt.setTradeTable(String.valueOf(tid), trade.getCreated(), 
				trade.getTitle(), trade.getPrice(),
				trade.getNum(), trade.getPayment(), 
				trade.getBuyer_nick(), trade.getStatus(), 
				tfi.getBuyer_message(),tfi.getSeller_memo());
//				tfi.getReceiver_address());
		//trade.getBuyer_message();
		
//		tt.setNewTradeTable(trade.getCreated(), String.valueOf(tid), trade.getTitle(), trade.getBuyer_nick(), trade.getPrice(), trade.getStatus());
		
		System.out.println(tt.getBuyer_nick()+"!!"+tt.getBuyer_message()+"!!"+tt.getArr());
//		String add = tt.getReceiver_address();
//		System.out.println("add=:"+add);
//		String[] add_arr = add.split("r");
//		System.out.println("add_arr:"+add_arr[0]+"--"+add_arr[1]+"--"+add_arr[2]);
//		if(add_arr.length<2)
//			return null;
		
//		System.out.println("add_arr_index:"+add_arr[0]);
//		String buyer_name = add_arr[0].substring(add_arr[0].indexOf(":")+1,add_arr[0].lastIndexOf("\\"));
//		System.out.println("buyer_name:"+buyer_name);
//		String buyer_pw = add_arr[1].substring(add_arr[1].indexOf(":")+1,add_arr[1].lastIndexOf("\\"));
//		System.out.println("buyer_pw:"+buyer_pw);

		
		return tt;
	}

	//修改发货状态
	public boolean changeStatus(long tid) throws ApiException{
		//发货状态修改
		System.out.println(getUrl());
		System.out.println(getAppkey());
		System.out.println(getAppSecret());
		System.out.println(tid);
		TaobaoClient client=new DefaultTaobaoClient(getUrl(), getAppkey(), getAppSecret());
		LogisticsDummySendRequest req=new LogisticsDummySendRequest();
		req.setTid(tid);
		req.setFeature("identCode=tid1:123,456;machineCode=tid2:aaaa");
		req.setSellerIp("192.168.1.10");
		LogisticsDummySendResponse response = client.execute(req , shouquancontroller.getSessionKey());
		JsonParser jp = new JsonParser();
		String is_success = jp.getShipping(response.getBody());
		return is_success.equals("true");
	}
	
	//购买后添加卖家备注
	public void memoAdd(long tid) throws ApiException{
		System.out.println(getUrl());
		System.out.println(getAppkey());
		System.out.println(getAppSecret());
		System.out.println(tid);
		TaobaoClient client=new DefaultTaobaoClient(getUrl(), getAppkey(), getAppSecret());
		TradeMemoAddRequest req=new TradeMemoAddRequest();
		req.setTid(tid);
		req.setMemo("添加备注的测试！！！");
		req.setFlag(4L);
		System.out.println(shouquancontroller.getSessionKey());
		TradeMemoAddResponse response = client.execute(req , shouquancontroller.getSessionKey());
	}
	
	//更新卖家备注
	public void memoUpdate(long tid) throws ApiException{
		System.out.println(getUrl());
		System.out.println(getAppkey());
		System.out.println(getAppSecret());
		System.out.println(tid);
		TaobaoClient client=new DefaultTaobaoClient(getUrl(), getAppkey(), getAppSecret());
		TradeMemoUpdateRequest req=new TradeMemoUpdateRequest();
		req.setTid(tid);
		req.setMemo("添加备注的测试！！！");
		req.setFlag(4L);
		req.setReset(true);
		System.out.println(shouquancontroller.getSessionKey());
		TradeMemoUpdateResponse response = client.execute(req , shouquancontroller.getSessionKey());
	}
	
	public ArrayList getTids(String startTime_str,String endTime_str,long page){		
		//==================get trade id==========================
		GetTradeSold gts = new GetTradeSold();
		Date dateStartTime = null;
		Date dateEndTime = null;
		try {
			dateStartTime = SimpleDateFormat.getDateTimeInstance().parse(
					startTime_str);//"2014-05-24 09:00:00");
			dateEndTime = SimpleDateFormat.getDateTimeInstance().parse(
					endTime_str);//"2014-05-24 09:30:00");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		System.out.println(dateStartTime+"------"+dateEndTime);
		
		//last value of tid is "has_next";
		ShouquanController sqc = ShouquanController.getInstance();
    	String sessionKey =sqc.getSessionKey();
    	
		try {
			tids = gts.testTradeGet(tbc.getUrl(), tbc.getAppkey(), tbc.getAppSecret(), sessionKey,dateStartTime,dateEndTime,page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("tids in tbc:"+tids);	
		return tids;
	}
	public static void main(String[] args) throws ApiException{
		TaobaoController tbc = new TaobaoController();

		//tbc.changeStatus(697192343968110L);
	}
	
}
