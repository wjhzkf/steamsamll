package json;

import java.util.ArrayList;
import java.util.Map;

import taobao.Trade;
import taobao.TradeFullInfo;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;


public class JsonParser {

	/**
	 * Reads a JSON-String, parses it into several Java-Maps with the quick-JSON parser.
	 * Returns the timestamp found in "productInfo".
	 * 
	 * @param input JSON as String
	 * @return timestamp as String
	 */
	public static TradeFullInfo getFullInfo(String input,TradeFullInfo tfi) {
		String json = input;
		JsonParserFactory factory = JsonParserFactory.getInstance();
		JSONParser parser = factory.newJsonParser();
		Map jsonData = parser.parseJson(json);

		Map fullinfo_get_response = (Map) (jsonData.get("trade_fullinfo_get_response"));
		Map trade = (Map) fullinfo_get_response.get("trade");
		String pay_time = (String)trade.get("pay_time");
		String receiver_address = (String)trade.get("receiver_address");
		String buyer_message = (String)trade.get("buyer_message");
		String payment = (String)trade.get("payment");
		String sellerMomo = (String)trade.get("seller_memo");
		tfi.setPay_time(pay_time);
		tfi.setPayment(payment);
		tfi.setReceiver_address(receiver_address);
		tfi.setBuyer_message(buyer_message);
		tfi.setSeller_memo(sellerMomo);
		
		return tfi;
	}

	public static Trade getTrade(String input,Trade trade) {
		String json = input;
		JsonParserFactory factory = JsonParserFactory.getInstance();
		JSONParser parser = factory.newJsonParser();
		Map jsonData = parser.parseJson(json);

		Map fullinfo_get_response = (Map) (jsonData.get("trade_get_response"));
		Map trade_map = (Map) fullinfo_get_response.get("trade");
		String buyer_nick = (String)trade_map.get("buyer_nick");
		String created = (String)trade_map.get("created");
		
		Map orders_map = (Map)(trade_map.get("orders"));
		ArrayList order_arr = (ArrayList)(orders_map.get("order"));
		System.out.println("size:"+order_arr.size()+order_arr);
		Map order_map = (Map)order_arr.get(0);
		String num=(String)order_map.get("num"); //int
//		System.out.println("~~num:"+num);
		String price = (String)order_map.get("price");  //double
		String status = (String)order_map.get("status"); 
		String title = (String)order_map.get("title");
		
		trade.setBuyer_nick(buyer_nick);
		trade.setNum(num);
		trade.setPrice(price);
		trade.setStatus(status);
		trade.setTitle(title);
		trade.setCreated(created);
		return trade;
	}

	public static ArrayList<String> getTradeSold(String input) {
		ArrayList<String> tid_arr = new ArrayList<String>();
		String json = input;
		JsonParserFactory factory = JsonParserFactory.getInstance();
		JSONParser parser = factory.newJsonParser();
		Map jsonData = parser.parseJson(json);

		Map fullinfo_get_response = (Map) (jsonData.get("trades_sold_get_response"));
		String has_next = (String)fullinfo_get_response.get("has_next");
		
		Map trades_map = (Map) fullinfo_get_response.get("trades");
		ArrayList trade_arr = (ArrayList) trades_map.get("trade");

	    for (int i=0; i<trade_arr.size();i++) {	       

	    	Map orders_map = (Map)trade_arr.get(i);
	    	String tid = (String)orders_map.get("tid");
	    	System.out.println("2:"+tid);
	    	tid_arr.add(tid);
	    }
		
		System.out.println("3"+trade_arr);
		tid_arr.add(has_next); //put the value has_next to the very end
		return tid_arr;
	}
	
	public String getSessionKey(String input){
		String json = input;
		JsonParserFactory factory = JsonParserFactory.getInstance();
		JSONParser parser = factory.newJsonParser();
		Map jsonData = parser.parseJson(json);

		String session_key = (String) (jsonData.get("access_token"));
		return session_key;
	}

	public String getShipping(String json){
		String is_success = "false";
		JsonParserFactory factory = JsonParserFactory.getInstance();
		JSONParser parser = factory.newJsonParser();
		Map jsonData = parser.parseJson(json);
		Map logistics_map = (Map)(jsonData.get("logistics_dummy_send_response"));
		Map shipping = (Map) (logistics_map.get("shipping"));
		is_success = (String)(shipping.get("is_success"));
		System.out.println("is_success:"+is_success);
		return is_success;
	}
}
