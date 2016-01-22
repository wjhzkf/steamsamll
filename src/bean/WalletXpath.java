package bean;

public class WalletXpath {
	//登录页
	private String loginIdField;					//登录页面账户名框
	private String loginPwField;					//登录页面账户密码框
	private String loginButton;					//登录页面登录按钮
	//输入充值码页
	private String currentBalanceA;				//当前账户余额
	private String codeInputField;					//输入充值码的框
	private String codeContinueButton;				//验证充值码的按钮
	//出错信息显示的xpath
	private String errorDisplaySpan;				//购买出错提示块
	private String errorDisplayA;					//购买出错提示信息链接
	//汇率换算页面
	private String accountDiv;						//code充值的金额
	private String exchangeAccountDiv;				//换算后的金额
	private String nextContinueButton;				//换算页的继续按钮
}
