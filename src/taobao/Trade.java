package taobao;

public class Trade {

	private String buyer_nick="";		//买家昵称
	private String buyer_message = "";	//买家给卖家的留言
	private String has_buyer_message = "";	//判断是否有买家留言
	private String buyer_email = "";	//买家邮箱
	private String receiver_address = "";//买家收货地址
	private String receiver_mobile = ""; //收货人手机
	
	private String title = "";   		//商品名称	
	private String num=""; 				//商品购买数量 
	/*取值范围：大于零的整数,对于一个trade 对应多个order的时候（一笔主订单，对应多笔子订单，num=0，num是一个跟商品关联的属性，一笔订单对
	应多比子订单的时候，主订单上的num无意义。*/
	private String num_iid = "";  		//商品数字编码为商家设置，可以对应steam的唯一app编号，用于自动生成steam游戏地址
	/* http://store.steampowered.com/sub/46028/ 则该游戏的数字编码是46028 */
	private String price = "";   		//double 商品价格 (非实际支付价格)
	private String status = "";  		//交易状态
	/*交易状态。可选值: * TRADE_NO_CREATE_PAY(没有创建支付宝交易) * WAIT_BUYER_PAY(等待买家付款) * 
	 * SELLER_CONSIGNED_PART(卖家部分发货) * WAIT_SELLER_SEND_GOODS(等待卖家发货,即:买家已付款) * 
	 * WAIT_BUYER_CONFIRM_GOODS(等待买家确认收货,即:卖家已发货) * TRADE_BUYER_SIGNED(买家已签收,货到付款专用) * 
	 * TRADE_FINISHED(交易成功) * TRADE_CLOSED(付款以后用户退款成功，交易自动关闭) * 
	 * TRADE_CLOSED_BY_TAOBAO(付款以前，卖家或买家主动关闭交易) * PAY_PENDING(国际信用卡支付付款确认中) *
	 * WAIT_PRE_AUTH_CONFIRM(0元购合约中)*/
	private String created = ""; 		//交易创建时间
	private String payment = "";		//买家实际支付金额
	private String pay_time = "";		//付款时间

	private String seller_memo = "";	//卖家对该笔订单的备注，用于客服备注，或者提交发货时候程序留言，查找与trade_memo 的区别
	private String seller_flag = "";    //卖家淘宝网上订单的卖家备注旗帜。红、黄、绿、蓝、紫 分别对应 1、2、3、4、5
	private String trade_memo = "";		//交易备注
	private	String seller_can_rate = "";//卖家是否可以对该笔订单评价
	
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getBuyer_nick() {
		return buyer_nick;
	}
	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBuyer_message() {
		return buyer_message;
	}
	public void setBuyer_message(String buyer_message) {
		this.buyer_message = buyer_message;
	}
	public String getNum_iid() {
		return num_iid;
	}
	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	public String getSeller_memo() {
		return seller_memo;
	}
	public void setSeller_memo(String seller_memo) {
		this.seller_memo = seller_memo;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getHas_buyer_message() {
		return has_buyer_message;
	}
	public void setHas_buyer_message(String has_buyer_message) {
		this.has_buyer_message = has_buyer_message;
	}
	public String getSeller_can_rate() {
		return seller_can_rate;
	}
	public void setSeller_can_rate(String seller_can_rate) {
		this.seller_can_rate = seller_can_rate;
	}
	public String getTrade_memo() {
		return trade_memo;
	}
	public void setTrade_memo(String trade_memo) {
		this.trade_memo = trade_memo;
	}
	public String getReceiver_address() {
		return receiver_address;
	}
	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}
	public String getReceiver_mobile() {
		return receiver_mobile;
	}
	public void setReceiver_mobile(String receiver_mobile) {
		this.receiver_mobile = receiver_mobile;
	}
	public String getSeller_flag() {
		return seller_flag;
	}
	public void setSeller_flag(String seller_flag) {
		this.seller_flag = seller_flag;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}

}
