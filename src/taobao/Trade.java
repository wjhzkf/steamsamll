package taobao;

public class Trade {

	private String buyer_nick="";		//����ǳ�
	private String buyer_message = "";	//��Ҹ����ҵ�����
	private String has_buyer_message = "";	//�ж��Ƿ����������
	private String buyer_email = "";	//�������
	private String receiver_address = "";//����ջ���ַ
	private String receiver_mobile = ""; //�ջ����ֻ�
	
	private String title = "";   		//��Ʒ����	
	private String num=""; 				//��Ʒ�������� 
	/*ȡֵ��Χ�������������,����һ��trade ��Ӧ���order��ʱ��һ������������Ӧ����Ӷ�����num=0��num��һ������Ʒ���������ԣ�һ�ʶ�����
	Ӧ����Ӷ�����ʱ���������ϵ�num�����塣*/
	private String num_iid = "";  		//��Ʒ���ֱ���Ϊ�̼����ã����Զ�Ӧsteam��Ψһapp��ţ������Զ�����steam��Ϸ��ַ
	/* http://store.steampowered.com/sub/46028/ �����Ϸ�����ֱ�����46028 */
	private String price = "";   		//double ��Ʒ�۸� (��ʵ��֧���۸�)
	private String status = "";  		//����״̬
	/*����״̬����ѡֵ: * TRADE_NO_CREATE_PAY(û�д���֧��������) * WAIT_BUYER_PAY(�ȴ���Ҹ���) * 
	 * SELLER_CONSIGNED_PART(���Ҳ��ַ���) * WAIT_SELLER_SEND_GOODS(�ȴ����ҷ���,��:����Ѹ���) * 
	 * WAIT_BUYER_CONFIRM_GOODS(�ȴ����ȷ���ջ�,��:�����ѷ���) * TRADE_BUYER_SIGNED(�����ǩ��,��������ר��) * 
	 * TRADE_FINISHED(���׳ɹ�) * TRADE_CLOSED(�����Ժ��û��˿�ɹ��������Զ��ر�) * 
	 * TRADE_CLOSED_BY_TAOBAO(������ǰ�����һ���������رս���) * PAY_PENDING(�������ÿ�֧������ȷ����) *
	 * WAIT_PRE_AUTH_CONFIRM(0Ԫ����Լ��)*/
	private String created = ""; 		//���״���ʱ��
	private String payment = "";		//���ʵ��֧�����
	private String pay_time = "";		//����ʱ��

	private String seller_memo = "";	//���ҶԸñʶ����ı�ע�����ڿͷ���ע�������ύ����ʱ��������ԣ�������trade_memo ������
	private String seller_flag = "";    //�����Ա����϶��������ұ�ע���ġ��졢�ơ��̡������� �ֱ��Ӧ 1��2��3��4��5
	private String trade_memo = "";		//���ױ�ע
	private	String seller_can_rate = "";//�����Ƿ���ԶԸñʶ�������
	
	
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
