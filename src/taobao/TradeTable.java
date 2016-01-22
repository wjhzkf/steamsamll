package taobao;

import java.util.ArrayList;

public class TradeTable {

	private String tid;
	private String created;
	private String title;
	private String price;
	private String num;
	private String payment;
	private String buyer_nick;
	private String status;
	private String receiver_address;
	private String buyer_message;
	private String seller_memo;
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getBuyer_nick() {
		return buyer_nick;
	}
	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getBuyer_message() {
		return buyer_message;
	}
	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}
	
	public String getSeller_memo() {
		return seller_memo;
	}
	public void setSeller_memo(String seller_memo) {
		this.seller_memo = seller_memo;
	}
	
	public void setTradeTable(String tid,String created,String title,
			String price,String num,String payment,String buyer_nick,
			String status,String buyer_message,String seller_memo){
		this.tid = tid;
		this.created = created;
		this.title = title;
		this.price = price;
		this.num = num;
		this.payment = payment;
		this.buyer_nick = buyer_nick;
		this.status = status;
		this.buyer_message = buyer_message;
		this.seller_memo = seller_memo;
	}
	
	public void setNewTradeTable(String created,String tid,String title,
			String buyer_nick,String payment,String status){
		this.tid = tid;
		this.created = created;
		this.title = title;
		this.payment = payment;
		this.buyer_nick = buyer_nick;
		this.status = status;	
	}
	
	/*
	 * 重新定义，存入信息表中
	 */
	public ArrayList<String> getNewArr(){
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(created);//0
		arr.add(tid);//1
		arr.add(title);//2
		arr.add(buyer_nick);//3
		arr.add(price);//4
		arr.add(status);//5
		arr.add(buyer_message);//6
		arr.add(num);//7
		arr.add(seller_memo);//8
		return arr;
	}
	
	public ArrayList getArr(){
		ArrayList arr = new ArrayList();
		arr.add(tid);//0
		arr.add(created);//1
		arr.add(title);//2
		arr.add(price);//3
		arr.add(num);//4
		arr.add(payment);//5
		arr.add(buyer_nick);//6
		arr.add(status);//7
		arr.add(receiver_address);//8
		return arr;
	}

}
