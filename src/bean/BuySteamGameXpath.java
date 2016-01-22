/*
 * 购买Steam游戏过程中所有的Xpath
 */
package bean;

public class BuySteamGameXpath {
	//登录页
	private String loginIdField;					//登录页面账户名框
	private String loginPwField;					//登录页面账户密码框
	private String loginButton;					//登录页面登录按钮
//	private String gameXpath;						//具体游戏的Xpath不在这边重述
	//年龄确认
	private String ageSelect;						//年龄选择框
	private String ageEnterButton;					//选择年龄处的进入按钮
	//购物车
	private String cartAsPresent;					//购物车页面"作为礼物按钮"
	//购买页
	private String sendEmailField;					//电子邮件输入框
	private String EmailContinueButton;			//电子邮件页继续按钮
	private String presentUserField;				//接收人姓名输入框
	private String presentMsgField;				//礼物信息框
	private String mySignField;					//我的签名框
	private String presentContinueButton;			//礼物寄语继续按钮
	//购买确认页
	private String agreeRadioButton;				//同意条款的选择框
	private String agreeBuyButton;					//同意购买按钮
	//购买出错提醒
	private String paymentMethodDiv;				//遇到支付方式选择块即出错
	private String errorDisplaySpan;				//购买出错提示块
	private String errorDisplayA;					//购买出错提示信息链接
	//放入仓库
	private String addToWareRadioButton;			//把礼物放入仓库的选择按钮
	
}
