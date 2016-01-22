package taobao;

public class TradeFullInfo {
	private String payment = "";
	private String pay_time = "";
	private String receiver_address = "";
	private String buyer_message = "";
	private String seller_memo = "";			//Âô¼Ò±¸×¢
	
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
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
	

	
}
