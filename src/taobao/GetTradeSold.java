/*
 * 获取指定时间内的所有订单
 * 可以控制参数获取已付款，未付款，待发货等的订单
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
	
	// 申请的测试账号 appkey 及 secret
	
	// protected static long tid = 2014051011001001640068414713L;
	// 9223372036854775807
	// protected static String sandboxSecret = " "; // 沙箱环境测试下的 AppSecret

	public static ArrayList<String> testTradeGet(String url, String appkey, String secret,
			String sessionKey,Date dateStartTime,Date dateEndTime,long page) throws ApiException, ParseException {

		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		TradesSoldGetRequest req = new TradesSoldGetRequest();
		// req.setFields("total_fee");

		
		req.setFields("tid,orders.status");
		
		req.setType("fixed");
		req.setStartCreated(dateStartTime);
		req.setEndCreated(dateEndTime);
		req.setStatus("WAIT_SELLER_SEND_GOODS");			//未发货
//		req.setStatus("WAIT_BUYER_CONFIRM_GOODS");
		
		req.setPageNo(page);	// 当前页数.只能获取1-499页数据. 
		req.setPageSize(40L);	// 每页返回结果数,默认是40条.最大每页100 
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
		String appkey = "21784353"; // 在阿里软件平台注册的应用ID,请使用你自己的应用ID
		String appSecret = "6d5fb38a798aabce820bb84ab746c593"; // 注册的应用的密钥，请使用你自己应用的密钥
//		String taobaoApiName = "taobao.trade.get"; // 交易类api
//		String sessionKey = "OAg7Jrm8mtINZyrfXPmkuOaa123568";
		String sessionKey = "6202516574f79452cf4075b7ZZ70fe886203a845e08925d693314641";// sessionId是isv应用自己生成的字符串
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
