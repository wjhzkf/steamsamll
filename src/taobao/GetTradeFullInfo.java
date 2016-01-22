package taobao;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import json.JsonParser;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.response.TradeFullinfoGetResponse;

public class GetTradeFullInfo {

	/**
	 * 调用淘宝的taobao.trade.get demo， 运行该示例时，你需要将appId,appSecret,tid
	 * 这三个参数替换为你自己的，就可以运行该demo
	 * 
	 * 在第一次运行时 会提醒你登录淘宝进行授权， 授权成功后 再次运行该demo就可以获取指定交易的信息 呵呵
	 * 
	 * 这只是一个简单的demo，只是为初学者提供方便 有了这个原型 大家就可以各显自己的神通了
	 */
	public static void main(String[] args) {

		// 淘宝Open API平台即SIP平台的入口
		// String sipRequestUrl = "http://sip.alisoft.com/sip/rest";
		String testUrl = "http://gw.api.tbsandbox.com/router/rest";// 沙箱
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "21784353"; // 在阿里软件平台注册的应用ID,请使用你自己的应用ID
		String appSecret = "6d5fb38a798aabce820bb84ab746c593"; // 注册的应用的密钥，请使用你自己应用的密钥
		String taobaoApiName = "taobao.trade.get"; // 交易类api
//		String sessionKey = "6102703c6641bc2f8197e4cfb289b71bb22005ec7bdb38a693314641";// sessionId是isv应用自己生成的字符串
		String sessionKey = "6202019573e1e2cfe4776a39b49ZZ4b33fa937bcb296bac693314641";
//		9Y3BWX8jumkDg1z37W3VO6MU127319
		// 确保每个使用该应用的用户各自的sessionId各不相同
		long tid = 657510980513853L;
		TradeFullInfo tfi = new TradeFullInfo();
		GetTradeFullInfo demo = new GetTradeFullInfo();
		demo.getInfo(tid,url, appkey, appSecret, sessionKey,tfi);

		Trade trade = new Trade();
		GetTrade gettrade = new GetTrade();
		gettrade.getInfo(tid,url, appkey, appSecret, sessionKey,trade);
	}

	/**
	 * taobao api fullinfo.get
	 */
	public TradeFullInfo getInfo(long tid, String url, String appkey, String secret,
			String sessionKey,TradeFullInfo tfi) {

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		TradeFullinfoGetRequest req = new TradeFullinfoGetRequest();
		req.setFields("payment,receiver_address,pay_time,buyer_message,seller_memo");
		// req.setTid(647717624038695L);
		// req.setTid(647888999969426L);
		// req.setTid(648715922136090L);
//		req.setTid(648763854899253L);
//		req.setTid(657510980513853L);
		req.setTid(tid);
		// req.setTid(646450272941731L);
		try {
			TradeFullinfoGetResponse response = client.execute(req, sessionKey);
			System.out.println("sellernick:" + response.getBody());
		
			JsonParser jp = new JsonParser();
			tfi = jp.getFullInfo(response.getBody(),tfi);
			System.out.println("received payment:" + tfi.getPayment()+"//receiver_address:"+tfi.getReceiver_address()+"//pay_time:"+tfi.getPay_time());
			return tfi;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}