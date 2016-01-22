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
	 * �����Ա���taobao.trade.get demo�� ���и�ʾ��ʱ������Ҫ��appId,appSecret,tid
	 * �����������滻Ϊ���Լ��ģ��Ϳ������и�demo
	 * 
	 * �ڵ�һ������ʱ ���������¼�Ա�������Ȩ�� ��Ȩ�ɹ��� �ٴ����и�demo�Ϳ��Ի�ȡָ�����׵���Ϣ �Ǻ�
	 * 
	 * ��ֻ��һ���򵥵�demo��ֻ��Ϊ��ѧ���ṩ���� �������ԭ�� ��ҾͿ��Ը����Լ�����ͨ��
	 */
	public static void main(String[] args) {

		// �Ա�Open APIƽ̨��SIPƽ̨�����
		// String sipRequestUrl = "http://sip.alisoft.com/sip/rest";
		String testUrl = "http://gw.api.tbsandbox.com/router/rest";// ɳ��
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "21784353"; // �ڰ������ƽ̨ע���Ӧ��ID,��ʹ�����Լ���Ӧ��ID
		String appSecret = "6d5fb38a798aabce820bb84ab746c593"; // ע���Ӧ�õ���Կ����ʹ�����Լ�Ӧ�õ���Կ
		String taobaoApiName = "taobao.trade.get"; // ������api
//		String sessionKey = "6102703c6641bc2f8197e4cfb289b71bb22005ec7bdb38a693314641";// sessionId��isvӦ���Լ����ɵ��ַ���
		String sessionKey = "6202019573e1e2cfe4776a39b49ZZ4b33fa937bcb296bac693314641";
//		9Y3BWX8jumkDg1z37W3VO6MU127319
		// ȷ��ÿ��ʹ�ø�Ӧ�õ��û����Ե�sessionId������ͬ
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