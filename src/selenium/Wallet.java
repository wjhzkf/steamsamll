package selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.jdom2.JDOMException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import script.SteamLog;
import frame.AutoSellerUI;

public class Wallet{
	private SeleniumMethods seleniumMethods;
	private String default_account = null;
	private String defaule_password = null;
	
	private String[] codes;
	
	//保存WalletXpath的哈希表
	public Map<String, String> WalletXpath;
	
	AutoSellerUI frame;
	//创建WebDriver实例
	WebDriver driver = null;
	WebDriverWait wait;
	//错误信息
	String error_info = "";
	//定义两个字符串，用于计算充值前后的差值
	String pre_account;
	String suf_account;
	
	//定义保存为txt的超长字符串
	String finalResult = "";
	
	//记录日志
	SteamLog log;
	String codeUSA;
	
	//汇率页面，continuebutton不可见时，是否要刷新页面的变量
	private boolean refresh = false;
	
	//账户
	public Map<String,String> accounts = new HashMap<String,String>();
	public List<String> accountIDs = new ArrayList<String>();
	
	public Wallet(AutoSellerUI frame){
		
		this.frame = frame;
		this.default_account = frame.codeDefaultAccountText.getText();
		this.defaule_password = frame.buyGameAccounts.get(this.default_account);
		String areaText = frame.codesTextArea.getText();
		this.codes = areaText.split("\n");
		this.frame.codesStatusTextArea.setText("");
		
		this.accounts = this.frame.accounts;
		this.accountIDs = this.frame.accountIDs;
		this.WalletXpath = this.frame.WalletXpath;
		
		// Define Chrome Option, create web Driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en");
		options.addExtensions(new File("driver/Block-image_v1.1.crx"), new File("driver/StopFlash-Flash-Blocker_v0.1.5.crx"));
		options.addExtensions(new File("driver/Block-Yourself-from-Analytics_v2.3.0.crx"));
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		this.driver = new ChromeDriver(options);
	    this.wait = new WebDriverWait(driver, 10);	   
		if(frame.showBrowserWhenRunning){
			this.driver.manage().window().setPosition(new Point(0,0));
			this.driver.manage().window().setSize(new Dimension(600, 400));
		}else{
			this.driver.manage().window().setPosition(new Point(-2000,0));
			this.driver.manage().window().setSize(new Dimension(600, 400));
		}
		
		//获取SeleniumMethods实例，并初始化driver和wait
		this.seleniumMethods = SeleniumMethods.getInstance();
		this.seleniumMethods.setDriver(this.driver);
		this.seleniumMethods.setWait(this.wait);
	}
	
	public Map<String, String> getWalletXpath() {
		return WalletXpath;
	}

	public void setWalletXpath(Map<String, String> walletXpath) {
		WalletXpath = walletXpath;
	}

	//调用是否关闭浏览器窗口的函数
	public void exitBrowAfterRunning(){
		if(frame.exitBrowserAfterRunning){	
			this.driver.quit();
		}
		try {
			Runtime.getRuntime().exec("cmd /c start /B kill_chromedriver.bat");
		}catch(Exception e){};
		return;
	}
	
	//计算前后账户余额的差值函数
	public int ChargePreSuf(String pre, String suf){
		System.out.println(pre+"--"+suf);
		String pre_num = pre.split(" ")[0].replaceAll(",", ".");
		String suf_num = suf.split(" ")[0].replaceAll(",", ".");
		int charge_num = (int) (Double.valueOf(suf_num)-Double.valueOf(pre_num));
		return charge_num;
	}
	
	//运行自动充值的程序
	public synchronized void autoStart() throws InterruptedException, JDOMException, IOException{
		this.log = new SteamLog(this.frame);
		this.frame.statusText.setText("！！！！正在启动浏览器，请耐心等待，请不要重复点击！！！！");
		this.frame.statusText.setText("！！！！！！！正在登录账户，请稍等！！！！！！！");
		String[] loginOrNot = {"false","0"};
		String account = this.default_account;
		String password = this.defaule_password;
		
		//调用自动登录函数，重试5次
		for(int i=0;i<5;i++){
			loginOrNot = this.seleniumMethods.login("https://store.steampowered.com/login/?cc=ru", account, password, this.WalletXpath.get("loginIdField"), this.WalletXpath.get("loginPwField"), this.WalletXpath.get("loginButton"));
			if(loginOrNot[0]=="true"){
				break;
			}else if(i==4){
				this.frame.statusText.setText("！！！登陆5次错误，请检查登录元素xpath后再试！！！");
				exitBrowAfterRunning();
				return;
			}else{
				this.frame.statusText.setText("！！！登录异常，正在重试！！！");
			}
		}
		
		//前后账户余额
		String pre_account = loginOrNot[1];
		String suf_account = null;

		for(int i=0;i<codes.length;i++){
			driver.get("https://store.steampowered.com/account/redeemwalletcode");
			
			int x = 0;
			boolean checkOver = false;
			String code = codes[i];
			while(x++<5){		
				//check steam code
				checkOver = batchCheck(code,"charge");
				System.out.println(checkOver);
				// 判断是否成功
				if("帐号已被禁用，请联系客服！".equals(this.error_info)){
					break;
				}
				if(x==5||(!checkOver&&this.refresh==false)){
					//充值码不正确，或者xpath确实有问题的情况
					//如果充值码不正确，再获取当前余额，计算余额差值
					this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+";余额"+pre_account+"\n");
					this.frame.statusText.setText("！！！！"+code+" 充值出错，等待下一个充值码！！！！");					
					//加入日志
					log.setMessage("充值失败！"+this.error_info+"充值账户为"+this.default_account+"，充值的code是"+code+"，充值前余额是"+this.pre_account+"，充值金额是"+this.codeUSA);
					log.insertLog();
					
					//不是最后continue的错误的话就退出循环
					this.refresh = false;
					break;
				}else if(checkOver){
					//check balance
					WebElement money;
					try{
						 money = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("currentBalanceA"))));				
					}catch (Exception getbalance){
						driver.navigate().refresh();
						try{
							money = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("currentBalanceA"))));
						}
						catch (Exception getbalance2){continue;}
					}
					suf_account = money.getText();
					int pre_suf = ChargePreSuf(pre_account,suf_account);

					if(pre_suf>2) {
						//前后余额有差别，充值码正确，退出循环
						this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+";充值金额"+pre_suf+";余额"+suf_account+"\n");
						this.frame.statusText.setText("！！！！"+code+" 充值成功，等待下一个充值码！！！！");					
						//加入日志
						log.setMessage("充值成功！充值账户为"+this.default_account+"，充值的code是"+code+"，充值前余额是"+pre_account+"，充值金额是"+this.codeUSA+"，充值后余额是"+suf_account);
						log.insertLog();
						this.refresh = false;
						// 赋值 用于下一次充值
						pre_account = suf_account;
						break;
					}
					else{
						//前后余额无差别，充值码失败，重试
						this.refresh = false;
						continue;
					}
				}else{
					//这种情况是!checkOver并且this.refresh==true，此时需要刷新页面，重新验证当前code
					//此时需要初始化this.refresh为false
					this.refresh = false;
					continue;
				}
			}
			if("帐号已被禁用，请联系客服！".equals(this.error_info)){
				//账号被封则跳出循环
				log.setMessage("Code充值失败"+this.error_info+"验证码为"+code+"，验证账户为"+accountIDs.get(i));
				log.insertLog();
				break;
			}
		}
		this.frame.statusText.setText("！！！！充值结束，请更换充值码或退出程序！！！！");
		exitBrowAfterRunning();
		return;
	}
	
	/*
	 * 新的Code验证函数
	 * 参数：Code字符串，充值或验证
	 */
	public boolean batchCheck(String code, String status) throws InterruptedException{
		
		WebElement codeInput = null;
		WebElement continueButton = null;
		
		try{
		codeInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("codeInputField"))));
		codeInput.clear();
		codeInput.sendKeys(code);
		}catch(Exception e){
			this.error_info = "！！！！Code输入框的Xpath出错，请重试！！！！";
			this.frame.statusText.setText(this.error_info);
			this.refresh = true;
			return false;
		}	
	
		try{
			continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("codeContinueButton"))));
			continueButton.click();
		}catch(Exception e){
			this.error_info = "继续按钮查找失败，请检查xpath或重试！";
			this.frame.statusText.setText(this.error_info);
			//刷新
			this.refresh = true;
			return false;
		}
				
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//先处理特殊的错误，被封号的错误
		try{	
//			WebElement error_link_a = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("errorDisplayA"))));
			// faster
			WebElement error_link_a = driver.findElement(By.xpath(this.WalletXpath.get("errorDisplayA")));
			this.error_info = "帐号已被禁用，请联系客服！";
			return false;
		}catch(Exception e){
			System.out.println("正常执行");
		}
		
		//查看其他错误消息
		try{
//			WebElement error_span = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("errorDisplaySpan"))));
			WebElement error_span = driver.findElement(By.xpath(this.WalletXpath.get("errorDisplaySpan")));			
			String error = error_span.getText();
			if(error!=null&!"".equals(error)){
				this.error_info = error;
				this.frame.statusText.setText(this.error_info);
				return false;
			}
		}catch(Exception e){
			System.out.println("正常执行");
		}
		
		//没有出错的话查看对应的汇率是否正确
		WebElement accountDiv = null;
		WebElement exchangeAccount = null;
		String account_rus = "99999";
		String account_usa = "99999";
		try{
			accountDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("accountDiv"))));
			exchangeAccount = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("exchangeAccountDiv"))));
			account_usa = accountDiv.getText();
				this.codeUSA = account_usa;
			account_rus = exchangeAccount.getText();
		}catch(Exception e){
			this.error_info = "汇率查看失败，请检查xpath或重试！";
			this.frame.statusText.setText(this.error_info);
			return false;
		}		


		//如果能够正确获取到汇率，则相应的error_info为美元
		this.error_info = account_usa+"---"+account_rus+"！";
		System.out.println("batchcheck"+this.error_info );
		//判断status,如status是'charge'则为充值继续执行，如果是'check'则为验证并返回
		if("check".equals(status)){		
			return true;
		}else{
			//自动充值继续执行
			WebElement nextContinueButton = null;
			try{
				nextContinueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("nextContinueButton"))));
			}catch(Exception e){
				System.out.println("waitbutton"+e);
				this.error_info = "继续按钮查找失败，请检查xpath或重试！";
				this.frame.statusText.setText("！！！继续按钮查找失败，请检查xpath或重试！！！");
				//需要刷新
				this.refresh = true;
				return false;
			}
			
			//不能点击的情况同样需要刷新
			try{
				nextContinueButton.click();
			}catch(Exception e){
				System.out.println("clickbutton"+e);
				this.error_info = "继续按钮查找失败，请检查xpath或重试！";
				this.frame.statusText.setText("！！！继续按钮查找失败，请检查xpath或重试！！！");
				//需要刷新
				this.refresh = true;
				return false;
			}		
			
			//检查是否充值成功
/*			
			WebElement chargeStatus = null;
			try{
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				chargeStatus = driver.findElement(By.id("//*[@id='redeem_wallet_success_button']"));
				System.out.println("redeem sucess"+chargeStatus);
			}catch(Exception e){
				System.out.println("redeem failed"+e);
				this.error_info = "充值失败";
				this.refresh = true;
				return false;
			}
*/
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.error_info = account_usa+" 充值成功！";
			return true;
		}
			
	}
	
	//批量验证充值码的函数
	public synchronized void batchStart() throws InterruptedException, JDOMException, IOException{
		this.log = new SteamLog(this.frame);
		this.frame.statusText.setText("！！！！正在启动浏览器，请耐心等待，请不要重复点击！！！！");
		
		//标记code
		int count = 0;
		int codeSize = codes.length;
		
		for(int i=0;i<accounts.size()&&count<codeSize;i++){
			
			String account = accountIDs.get(i);
			String password = accounts.get(accountIDs.get(i));
			String[] loginOrNot = {"false","0"};
			
			this.frame.statusText.setText("！！！！！！！正在切换账户，请稍等！！！！！！！");
			this.frame.codeDefaultAccountText.setText(account);
			//调用账户替换函数，重试5次
			for(int x=0;x<5;x++){
				loginOrNot = this.seleniumMethods.login("https://store.steampowered.com/login/?cc=ru", account, password, this.WalletXpath.get("loginIdField"), this.WalletXpath.get("loginPwField"), this.WalletXpath.get("loginButton"));;
				if(loginOrNot[0]=="true"){
					break;
				}else if(x==1){
					this.frame.statusText.setText("！！！登陆5次错误，请检查登录元素xpath后再试！！！");
					exitBrowAfterRunning();
					return;
				}else{
					this.frame.statusText.setText("！！！登录异常，正在重试！！！");
				}
			}
				
			for(int j=0;j<10&&count<codeSize;j++){
				String code = codes[count];			
				driver.get("https://store.steampowered.com/account/redeemwalletcode");
				if(batchCheck(code,"check")){
					//如果充值码正确, 添加到日志
					this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+"\n");
					log.setMessage("Code验证，"+this.error_info+"验证码为"+code+"，验证账户为"+accountIDs.get(i)+"验证账户余额为]"+loginOrNot[1]);
					log.insertLog();
					count++;
				}else{
					if("帐号已被禁用，请联系客服！".equals(this.error_info)){
						//账号被封则跳出循环，登陆另一个账户
						this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+"\n");
						log.setMessage("Code验证，"+this.error_info+"验证码为"+code+"，验证账户为"+accountIDs.get(i)+"验证账户余额为]"+loginOrNot[1]);
						log.insertLog();
						break;
					}else{
						//如果充值码不正确,添加到日志
						this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+"\n");
						log.setMessage("Code验证，"+this.error_info+"验证码为"+code+"，验证账户为"+accountIDs.get(i)+"验证账户余额为]"+loginOrNot[1]);
						log.insertLog();
						this.frame.statusText.setText("！！充值码  "+code+" 有误！！");
						count++;
					}
					
				}
			}
		}
		this.frame.statusText.setText("！！！！检查结束，请跟换充值码或退出程序！！！！");
		System.out.println("充值码全部检查成功");
		exitBrowAfterRunning();
		return;
	}
	
}
