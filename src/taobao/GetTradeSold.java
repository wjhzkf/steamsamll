/*
 * ��ȡָ��ʱ���ڵ����ж���
 * ���Կ��Ʋ�����ȡ�Ѹ��δ����������ȵĶ���
 */

package taobao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import json.JsonParser;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.response.TradesSoldGetResponse;

public class GetTradeSold {
	
	// ����Ĳ����˺� appkey �� secret
	
	// protected static long tid = 2014051011001001640068414713L;
	// 9223372036854775807
	// protected static String sandboxSecret = " "; // ɳ�价�������µ� AppSecret

	public static ArrayList<String> testTradeGet(String url, String appkey, String secret,
			String sessionKey,Date dateStartTime,Date dateEndTime,long page) throws ApiException, ParseException {

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		TradesSoldGetRequest req = new TradesSoldGetRequest();
		// req.setFields("total_fee");

		
		req.setFields("tid,orders.status");
		
		req.setType("fixed");
		req.setStartCreated(dateStartTime);
		req.setEndCreated(dateEndTime);
		req.setStatus("WAIT_SELLER_SEND_GOODS");			//δ����
//		req.setStatus("WAIT_BUYER_CONFIRM_GOODS");
		
		req.setPageNo(page);	// ��ǰҳ��.ֻ�ܻ�ȡ1-499ҳ����. 
		req.setPageSize(40L);	// ÿҳ���ؽ����,Ĭ����40��.���ÿҳ100 
		req.setUseHasNext(true);
		TradesSoldGetResponse response = client.execute(req, sessionKey);
		
		JsonParser jp = new JsonParser();
		ArrayList<String> tid_arr = jp.getTradeSold(response.getBody());
		System.out.println("response");
		System.out.println("response body:"+response.getBody());
		
		return tid_arr;
	}

/*	public static void main(String[] args) throws ParseException {
		String url = "http://gw.api.taobao.com/router/rest";
		String appkey = "21784353"; // �ڰ������ƽ̨ע���Ӧ��ID,��ʹ�����Լ���Ӧ��ID
		String appSecret = "6d5fb38a798aabce820bb84ab746c593"; // ע���Ӧ�õ���Կ����ʹ�����Լ�Ӧ�õ���Կ
//		String taobaoApiName = "taobao.trade.get"; // ������api
//		String sessionKey = "OAg7Jrm8mtINZyrfXPmkuOaa123568";
		String sessionKey = "6202516574f79452cf4075b7ZZ70fe886203a845e08925d693314641";// sessionId��isvӦ���Լ����ɵ��ַ���
		GetTradeSold gts = new GetTradeSold();
		Date dateStartTime = SimpleDateFormat.getDateTimeInstance().parse(
		"2014-07-05 10:24:56.000");
		Date dateEndTime = SimpleDateFormat.getDateTimeInstance().parse(
		"2014-07-06 10:24:56.000");
		
		try {
			gts.testTradeGet(url, appkey, appSecret, sessionKey,dateStartTime,dateEndTime,1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
