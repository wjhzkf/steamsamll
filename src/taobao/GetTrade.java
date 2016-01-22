package taobao;

import json.JsonParser;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TradeGetRequest;
import com.taobao.api.response.TradeGetResponse;

public class GetTrade {
	//orders.status,
	public Trade getInfo(long tid,String url, String appkey, String secret,String sessionKey,Trade trade){
    	TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
    	TradeGetRequest req=new TradeGetRequest();
    	req.setFields("orders.title,orders.price,orders.num,orders.status,created,buyer_nick, buyer_message");
   // 	req.setTid(647717624038695L);
    	req.setTid(tid);
    	try {
			TradeGetResponse response = client.execute(req , sessionKey);
			JsonParser jp = new JsonParser();
			trade = jp.getTrade(response.getBody(), trade);
			System.out.println("buyer_nick:"+trade.getBuyer_nick()+"//num:"+trade.getNum()
					+"//price:"+trade.getPrice()+"//status:"+trade.getStatus()
					+"//title:"+trade.getTitle()+"//created:"+trade.getCreated()+"//buyer_message"+trade.getBuyer_message());
//			System.out.println(response.getBody());
			return trade ; 
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
}
