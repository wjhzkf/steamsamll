/*
 * Steam游戏发送到收货邮箱的类
 */
package selenium;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import script.SteamLog;
import bean.Game;
import bean.SteamGame;
import frame.AutoSellerUI;
import frame.BatchBuyUI;
import frame.CheckValue;
import frame.WalletAlertUI;

public class GameObj{
	private SeleniumMethods seleniumMethods;
	private BatchBuyUI batchFrame;
	private AutoSellerUI frame;
	private Game game;
	private SteamGame steamGame;
	private SteamGame uniSteamGame;//万能游戏
	private String default_account = null;
	private String defaule_password = null;
	private String default_email = null;
	private String game_name = null;
	private String driver_link = null;
	
	//账户购买游戏
	public Map<String,SteamGame> buySteamGame=null;
	
	//是否刷新的变量
	private boolean refresh = false;
	
	//记录循环购买游戏中更换账户的变量，如果当前账户余额不足或当前账户购买不成功，则使用下一个账号
	private static boolean nextBuyGameAccount = false;
	
	//记录当前账户的余额
	private String currentBalanceStr;
	private String finalBalanceStr;
	
	//记录浏览器的句柄，用于跳区购买游戏
	private String handleStart = "";
	private String handleEnd = "";
	/*
	 * 购买steam游戏需要保存的账户和密码，以及购买游戏的个数
	 */
	public Map<String, String> buyGameAccounts;
	public List<String> buyGameAccountID;
	public int gameNum;
	
	//保存BuySteamGameXpath的哈希表
	public Map<String, String> buySteamGameXpath;	
		
	//日志
	SteamLog log;
	
	//创建WebDriver实例
	WebDriver driver;
	WebDriverWait wait;
	
	//Selenium模拟键盘
	Actions action;
	
	public GameObj(){}
	
	public GameObj(BatchBuyUI batchFrame){
		buySteamGame = new HashMap<String, SteamGame>();
		//加载驱动
		batchFrame.info_buy_process_Text.setText("！！！正在启动浏览器，请不要重复点击！！！");
		this.frame = batchFrame.frame;
		
		// Define Chrome Option, create web Driver
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("driver/Block-image_v1.1.crx"), new File("driver/StopFlash-Flash-Blocker_v0.1.5.crx"));
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		this.driver = new ChromeDriver(options);
	    this.wait = new WebDriverWait(driver, 15);
	    this.action = new Actions(driver);
		if(frame.showBrowserWhenRunning){
			this.driver.manage().window().setPosition(new Point(0,0));
			this.driver.manage().window().setSize(new Dimension(600, 400));
		}else{
			this.driver.manage().window().setPosition(new Point(-5000,0));
			this.driver.manage().window().setSize(new Dimension(600, 400));
		}
		
		//获取SeleniumMethods实例，并初始化driver和wait
		this.seleniumMethods = SeleniumMethods.getInstance();
		this.seleniumMethods.setDriver(this.driver);
		this.seleniumMethods.setWait(this.wait);
	}

	public String getDefault_account() {
		return default_account;
	}

	public void setDefault_account(String default_account) {
		this.default_account = default_account;
	}

	public String getDefaule_password() {
		return defaule_password;
	}

	public void setDefaule_password(String defaule_password) {
		this.defaule_password = defaule_password;
	}

	public String getDefault_email() {
		return default_email;
	}

	public void setDefault_email(String default_email) {
		this.default_email = default_email;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	};

	
	public String getDriver_link() {
		return driver_link;
	}

	public void setDriver_link(String driver_link) {
		this.driver_link = driver_link;
	}
	
	public BatchBuyUI getBatchFrame() {
		return batchFrame;
	}

	public void setBatchFrame(BatchBuyUI batchFrame) {
		this.batchFrame = batchFrame;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public SteamGame getSteamGame() {
		return steamGame;
	}

	public void setSteamGame(SteamGame steamGame) {
		this.steamGame = steamGame;
	}

	public Map<String, String> getBuyGameAccounts() {
		return buyGameAccounts;
	}

	public void setBuyGameAccounts(Map<String, String> buyGameAccounts) {
		this.buyGameAccounts = buyGameAccounts;
	}

	public List<String> getBuyGameAccountID() {
		return buyGameAccountID;
	}

	public void setBuyGameAccountID(List<String> buyGameAccountID) {
		this.buyGameAccountID = buyGameAccountID;
	}

	public int getGameNum() {
		return gameNum;
	}

	public void setGameNum(int gameNum) {
		this.gameNum = gameNum;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	public AutoSellerUI getFrame() {
		return frame;
	}

	public void setFrame(AutoSellerUI frame) {
		this.frame = frame;
	}

	public SteamLog getLog() {
		return log;
	}

	public void setLog(SteamLog log) {
		this.log = log;
	}
	
	
	public Map<String, String> getBuySteamGameXpath() {
		return buySteamGameXpath;
	}

	public void setBuySteamGameXpath(Map<String, String> buySteamGameXpath) {
		this.buySteamGameXpath = buySteamGameXpath;
	}

	public SteamGame getUniSteamGame() {
		return uniSteamGame;
	}

	public void setUniSteamGame(SteamGame uniSteamGame) {
		this.uniSteamGame = uniSteamGame;
	}

	/*
	 * 调用是否关闭浏览器窗口的函数
	 */
	public void exitBrowAfterRunning(){
		if(frame.exitBrowserAfterRunning){	
			this.driver.quit();
		}
		try {
			Runtime.getRuntime().exec("cmd /c start /B kill_chromedriver.bat");
		}catch(Exception e){};
		return;
	}
	
		/*
	 * 取消程序的监听函数，如果
	 */
	public boolean quitCharge(){
		if(batchFrame.closeDriverTag){
			this.driver.quit();
			batchFrame.info_buy_process_Text.setText("！！！成功退出充值，请关闭窗口！！！");
			try {
				Runtime.getRuntime().exec("cmd /c start /B kill_chromedriver.bat");
			}catch(Exception e){};
			return true;
		}else{
			return false;
		}
	}
	
	/*
	* 获取当前账户余额的函数
	 */
	public boolean getCurrentBalance(){
		batchFrame.info_buy_process_Text.setText("！！！正在查询当前账户余额，请稍等！！！");
		WebElement currentBalanceLink = null;
		try{
			currentBalanceLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("currentBalanceA"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！！账户余额查找失败，请检查xpath或重试！！！");
			return false;
		}
		
		this.currentBalanceStr = currentBalanceLink.getText();
		batchFrame.info_default_account_text.setText(this.default_account+"---账户余额"+this.currentBalanceStr);
		return true;
	}
	
	/*
	 * 判断账户余额的程序
	 */
	public boolean checkBalance(){
		batchFrame.info_buy_process_Text.setText("！！！正在检查余额，请稍等！！！");
//		WebElement currentBalanceLink = null;
		WebElement cartPriceDiv = null;
		try{
//			currentBalanceLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("currentBalanceA"))));
			cartPriceDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("cartPriceDiv"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！！账户余额或购物车小计查找失败，请检查xpath或重试！！！");
			this.refresh = true;
			return false;
		}
		
		currentBalanceStr =((JavascriptExecutor)driver).executeScript("var gift=document.getElementById('header_wallet_balance');"
							+ "return gift.textContent;").toString();
		String cartPriceStr = cartPriceDiv.getText();

		System.out.println(currentBalanceStr+"---"+cartPriceStr);
		float currentBalance = balanceFormat(this.buySteamGameXpath.get("country"), currentBalanceStr);
		float cartPrice = balanceFormat(this.buySteamGameXpath.get("country"), cartPriceStr);
		System.out.println(currentBalance+"---"+cartPrice);
		
		finalBalanceStr = Float.toString(currentBalance-cartPrice);
		if(currentBalance<cartPrice){
			this.refresh = false;
			this.nextBuyGameAccount = true;
			batchFrame.info_buy_process_Text.setText("！！！账户余额不够，请充值！！！");
			
			//调用自动充值UI
//			WalletAlertUI walletAlertFrame = new WalletAlertUI();
//			walletAlertFrame.setVisible(true);
//			walletAlertFrame.warningField.setText("账户"+this.default_account+"余额不足!");
			
			return false;
		}
		else{
			batchFrame.info_buy_process_Text.setText("！！！账户余额充足，继续购买！！！");
			return true;
			
		}
		
		
	}
	
	/*
	 * 针对不同国家地区处理账户余额
	 */
	public float balanceFormat(String country, String balanceStr){
		float balance = 0;
		String bs="";
		switch(country){
		case "ru": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replaceAll(",", ".").replaceAll("pуб.", "")).equals("")?"0.0":bs); break;
		case "us": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replace("$", "").replace("USD", "").replaceAll(",", "")).equals("")?"0.0":bs); break;
		case "hk": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replace("$", "").replace("USD", "").replaceAll(",", "")).equals("")?"0.0":bs); break;
		case "cn": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replace("¥", "").replace("USD", "").replaceAll(",", "")).equals("")?"0.0":bs); break;
		case "ua": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replace("$", "").replace("USD", "").replaceAll(",", "")).equals("")?"0.0":bs); break;
		case "br": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replace("R$", "").replaceAll(",", ".")).equals("")?"0.0":bs); break;
		case "jp": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replace("¥", "").replaceAll(",", "")).equals("")?"0.0":bs); break;
		case "id": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replace("Rp", "").replaceAll(",", "")).equals("")?"0.0":bs); break;
		case "my": balance = Float.parseFloat((bs=balanceStr.replaceAll(" ", "").replace("RM", "").replaceAll(",", "")).equals("")?"0.0":bs); break;
		}
		return balance;
	}
	
	/*
	 * 针对不同国家地区处理游戏链接
	 */
	public String changeGameLink(String country, String gameLink){
		String newSteamGameLink = gameLink;
		switch(country){
		case "ru": newSteamGameLink = gameLink.concat("?cc=ru"); break;
		case "us": newSteamGameLink = gameLink.concat("?cc=us"); break;
		case "hk": newSteamGameLink = gameLink.concat("?cc=hk"); break;
		case "cn": newSteamGameLink = gameLink.concat("?cc=cn"); break;
		case "ua": newSteamGameLink = gameLink.concat("?cc=ua"); break;
		case "br": newSteamGameLink = gameLink.concat("?cc=br"); break;
		case "jp": newSteamGameLink = gameLink.concat("?cc=jp"); break;
		case "id": newSteamGameLink = gameLink.concat("?cc=id"); break;
		case "my": newSteamGameLink = gameLink.concat("?cc=my"); break;
		}
		System.out.println("真实购买游戏的链接："+newSteamGameLink);
		return newSteamGameLink;
	}
	
	/*
	 * 加入到仓库的主函数
	 */
	public boolean addToWare(){
		//转到赠送礼物界面，点击“把礼物存放在我的仓库里”
		WebElement addToWareRadioButton = null;
		try{
			addToWareRadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("addToWareRadioButton"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！收货邮箱文本框的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		if(!addToWareRadioButton.isSelected()){
			addToWareRadioButton.click();
		}
		
		//输入完邮箱以后，点击确认继续
//		WebElement continue_button = null;
//		try{
//			continue_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("EmailContinueButton"))));
//		}catch(Exception e){
//			batchFrame.info_buy_process_Text.setText("！！收货邮箱处继续按钮的xpath出错，请更新程序！！");
//			this.refresh = true;
//			return false;
//		}
//		if(quitCharge()){return false;};
//		
//		try{
//			continue_button.click();			
//		}catch(Exception e){
//			this.refresh = true;
//			return false;
//		}
		
		//调用enter键
		this.action.sendKeys(Keys.ENTER).perform();
		
		//页面跳转，需要等待
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//确认购买页面，点击同意条款框，以及继续按钮
		WebElement accept_box = null;
		try{
			accept_box = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("agreeRadioButton"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！同意条款选项的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		if(!accept_box.isSelected()){
			try{
				accept_box.click();			
			}catch(Exception e){
				this.refresh = true;
				return false;
			}
		}
		
		WebElement jump_to_WebMoney = null;
		try{		
			jump_to_WebMoney = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("agreeBuyButton"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！同意条款出购买按钮的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		
		try{
			jump_to_WebMoney.click();			
		}catch(Exception e){
			this.refresh = true;
			return false;
		}
		
		return true;
		
	}
	
	/*
	 * purchase as a gift函数
	 */
	public boolean purchaseAsGift(){
		//检查账户余额是否正确，不正确的话则返回错误
		if(!checkBalance()){
			System.out.println("账户余额不足");
			return false;
		}
		
		//转到购物车界面，点击以礼物的方式发给好友
		WebElement send_gift_button = null;
		try{
//			send_gift_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("cartAsPresentButton"))));
			String openGifts="var gift=document.getElementById('btn_purchase_self').parentElement.children[0];"
							 +"gift.click();";
			((JavascriptExecutor)driver).executeScript(openGifts);
	
		}catch(Exception e){
			this.refresh = true;
			System.out.println("错误的地方1~~"+this.buySteamGameXpath.get("cartAsPresentButton"));
			return false;
		}


		return true;
	}
	
	/*
	 * 用于跳区购买游戏的purchase as a gift函数
	 */
	public boolean jumpAreaPurchaseAsGift(){
		//转到购物车界面，点击以礼物的方式发给好友
		WebElement send_gift_button = null;
		try{
			send_gift_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("cartAsPresentButton"))));
		}catch(Exception e){
			this.refresh = true;
			System.out.println("错误的地方1~~"+this.buySteamGameXpath.get("cartAsPresentButton"));
			return false;
		}
		//不需要检查账户余额是否正确
//		if(!checkBalance()){
//			System.out.println("错误的地方2~~");
//			return false;
//		}
//		
		try{
			send_gift_button.click();			
		}catch(Exception e){
			System.out.println("错误的地方3~~");
			this.refresh = true;
			return false;
		}
		return true;
	}
	
	/*
	 * purchase for myself函数
	 */
	public boolean purchaseForMyself(){
		//转到购物车界面，点击为自己购买按钮
		WebElement buyForMyselfButton = null;
		try{
			buyForMyselfButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("buyMyselfButton"))));
		}catch(Exception e){
			this.refresh = true;
			System.out.println("错误的地方1~~"+this.buySteamGameXpath.get("buyMyselfButton"));
			return false;
		}
		//检查账户余额是否正确，不正确的话则返回错误
		if(!checkBalance()){
			System.out.println("错误的地方2~~");
			return false;
		}
		
		try{
			buyForMyselfButton.click();			
		}catch(Exception e){
			System.out.println("错误的地方3~~");
			this.refresh = true;
			return false;
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//确认购买页面，点击同意条款框，以及继续按钮
		WebElement accept_box = null;
		try{
			accept_box = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("agreeRadioButton"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！同意条款选项的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		if(!accept_box.isSelected()){
			try{
				accept_box.click();			
			}catch(Exception e){
				this.refresh = true;
				return false;
			}
		}
		
		WebElement jump_to_WebMoney = null;
		try{		
			jump_to_WebMoney = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("agreeBuyButton"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！同意条款出购买按钮的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		
		try{
			jump_to_WebMoney.click();			
		}catch(Exception e){
			this.refresh = true;
			return false;
		}
		
		return true;
	}
	
	/*
	 * 用电子邮件发送礼物的主函数
	 */
	public boolean sendPresent(){
		if(quitCharge()){return false;};
		
		//转到赠送礼物界面，填写相关收货邮箱信息
		WebElement buyer_email = null;
		try{
			buyer_email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("sendEmailField"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！收货邮箱文本框的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		buyer_email.sendKeys(this.default_email);
		
		//输入完邮箱以后，点击确认继续
//		WebElement continue_button = null;
//		try{
//			continue_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("EmailContinueButton"))));
//		}catch(Exception e){
//			batchFrame.info_buy_process_Text.setText("！！收货邮箱处继续按钮的xpath出错，请更新程序！！");
//			this.refresh = true;
//			return false;
//		}
//		if(quitCharge()){return false;};
//		
//		continue_button.click();
		
		//调用enter键
		this.action.sendKeys(Keys.ENTER).perform();
		/*
		try{
			continue_button.click();
		}catch(Exception e){
			this.refresh = true;
			return false;
		}*/
		
		//这里的下一步需要等待几秒，设置等待时间
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		if(quitCharge()){return false;};
		
		WebElement buyer_name = null;
		WebElement gift_info = null;
		WebElement my_sign = null;
		
		try{
			buyer_name = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("presentUserField"))));
			gift_info = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("presentMsgField"))));
			my_sign = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("mySignField"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！填写留言信息处的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		buyer_name.sendKeys("H");
		gift_info.sendKeys("Thanks! Please provide us positive feedback!");
		my_sign.sendKeys("xiaoxigame");
		
//		WebElement continue_button2 = null;
//		try{
//			continue_button2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("presentContinueButton"))));			
//		}catch(Exception e){
//			batchFrame.info_buy_process_Text.setText("！！填写留言信息处继续按钮的xpath出错，请更新程序！！");
//			this.refresh = true;
//			return false;
//		}
//		if(quitCharge()){return false;};
//		try{
//			continue_button2.click();
//		}catch(Exception e){
//			this.refresh = true;
//			return false;
//		}	
		
		//调用enter键
		this.action.sendKeys(Keys.ENTER).perform();
		
		batchFrame.info_buy_process_Text.setText("！！正在跳转到最后的游戏购买页面！！");
		
		//页面跳转，需要等待
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(quitCharge()){return false;};
		
		//确认购买页面，点击同意条款框，以及继续按钮
		WebElement accept_box = null;
		try{
			accept_box = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("agreeRadioButton"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！同意条款选项的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		if(!accept_box.isSelected()){
			try{
				accept_box.click();		
			}catch(Exception e){
				this.refresh = true;
				return false;
			}		
		}
		
		WebElement jump_to_WebMoney = null;
		try{		
			jump_to_WebMoney = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("agreeBuyButton"))));
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！同意条款出购买按钮的xpath出错，请更新程序！！");
			this.refresh = true;
			return false;
		}
		if(quitCharge()){return false;};
		
		try{
			jump_to_WebMoney.click();			
		}catch(Exception e){
			this.refresh = true;
			return false;
		}
		return true;
		
	}
	
	/*
	 * Steam游戏发送到邮箱的最后一步，点击同意条框，点击继续即可
	 */
	public boolean confirmPage(){
		
		//此处正在确认购买，跳转到购买成功的页面
		batchFrame.info_buy_process_Text.setText("！！正在跳转到购买成功页面，请稍等！！");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(quitCharge()){return false;};
		try{
			WebElement printReceipt = driver.findElement(By.xpath("//*[@id='receipt_area']/a"));
			printReceipt.click();
		}catch(Exception failed){
			//查看error_display下的a标签是否存在，如果存在则出错
			WebElement errorDisplaySpan = null;								//a标签的父标签，判断xpath是否出错
			try{
				errorDisplaySpan = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("errorDisplaySpan"))));
			}catch(Exception e){
				batchFrame.info_buy_process_Text.setText("！！！充值出错提醒元素的xpath出错，请重试！！！");
				return false;
			}
			//捕获特殊异常，如果页面是需要选择支付方式，则出错
			WebElement paymentMethodDIV = null;
			try{
				paymentMethodDIV = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("paymentMethodDiv"))));
				//能找到支付方式的div块，说明发生错误，则返回false
			}catch(Exception e){
				batchFrame.info_buy_process_Text.setText("！！账户禁用或余额不足！！");
				return false;
			}
			
		WebElement errorDisplayA = null;
		try{
			errorDisplayA = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.buySteamGameXpath.get("errorDisplayA"))));
			//特殊错误，记录标志，使用下一个账户
			this.nextBuyGameAccount = true;
			batchFrame.info_buy_process_Text.setText("！！！最后充值发生错误，正在重试！！！");
		}catch(Exception e){
			batchFrame.info_buy_process_Text.setText("！！！最后充值发生错误，请重试！！！");
			return true;
		}
		
		return true;}
		return true;	
	}
	
	/*
	 * 自动购买游戏的主要步骤
	 */
	public synchronized boolean buyRun(){
		this.log = new SteamLog(this.frame);
		this.seleniumMethods.setBuySteamGameXpath(this.buySteamGameXpath);
		//共有所烧个购买游戏
		int selectgameNum=this.buySteamGame.size();
		int gameNumTemp=0;
		//循环判断是否登录成功，不成功，则结束程序 
		// 这个循环应该放到 login 函数里面
		String[] loginOrNot = new String[2];
		for(int i=0;i<5;i++){
			String account = this.default_account;
			String password = this.defaule_password;
			loginOrNot = this.seleniumMethods.login(this.buySteamGameXpath.get("loginPageLink"), account, password, this.buySteamGameXpath.get("loginIdField"), this.buySteamGameXpath.get("loginPwField"), this.buySteamGameXpath.get("loginButton"));
			if(loginOrNot[0]=="true"){
				break;
			}else if(i==4){
				batchFrame.info_buy_process_Text.setText("！！！登陆5次错误，请检查登录元素xpath后再试！！！");
				//记录日志
				log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
				log.insertLog();
//				exitBrowAfterRunning();
				return false;
			}else{
				batchFrame.info_buy_process_Text.setText("！！！登陆异常，正在重试！！！");
			}
		}
		batchFrame.info_buy_process_Text.setText("！！！正在登陆中！！！");
		
		//查看当前账户余额
		this.currentBalanceStr = loginOrNot[1];
		System.out.println("当前余额："+this.currentBalanceStr);
		
		Iterator iterator=this.buySteamGame.keySet().iterator();
		//购买游戏
		while (iterator.hasNext()) {
			for(int n=0;n<this.gameNum;n++){
					String gamenameTemp=iterator.next().toString();
					//设置当前steamgame
					this.setSteamGame(this.buySteamGame.get(gamenameTemp));
					this.setGame_name(gamenameTemp);
					//设置显示游戏名
					CheckValue value = null;
					for (int m = 0; m < frame.gamesComboBox.getItemCount(); m++) {
						value=(CheckValue) frame.gamesComboBox.getItemAt(m);
						if (this.game_name.equals(value.value)) {
							frame.gamesComboBox.setSelectedIndex(m);
							value.bolValue=true;
						}
					}
		      	batchFrame.setText();
					
				
			    if(quitCharge()){return true;};
			    batchFrame.hasBuyNumText.setText("已经购买"+gameNumTemp+"个游戏，正在购买第"+(gameNumTemp+1)+"个游戏！");
				
				//跳转到游戏界面，查找加入购物车的按钮，循环查找
				if(quitCharge()){return true;};
				batchFrame.info_buy_process_Text.setText("！！！跳转到对应游戏界面并加入购物车！！！");
				driver.get(this.steamGame.getLink());
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean addToCartOrNot = this.seleniumMethods.addToCart(this.steamGame.getLink(), this.steamGame.getXpath());
					if(addToCartOrNot){
						break;
					}else if(i==4){
						batchFrame.info_buy_process_Text.setText("！！查找购物车按钮5次出错，请更新游戏xpath！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
		//				exitBrowAfterRunning();
						return false;
					}else{
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}	
				
				if(quitCharge()){return true;};
				
				//调用purchase as a gift函数，循环5次
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean purchaseOrNot = purchaseAsGift();
					if(purchaseOrNot){
						this.refresh = false;
						break;
					}else if(i==4||(!purchaseOrNot&&this.refresh==false)){
						batchFrame.info_buy_process_Text.setText("！！查找购物车按钮5次出错或者账户余额不足，请更新游戏xpath或充值！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
		//				exitBrowAfterRunning();
						this.refresh = false;
						return false;
					}else if(!purchaseOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
				//调用发送礼物的函数，循环5次
				batchFrame.info_buy_process_Text.setText("！！！正在发送礼物，请稍等！！！");
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean sendPresentOrNot = sendPresent();
					if(sendPresentOrNot){
						this.refresh = false;
						break;
					}else if(i==4||(!sendPresentOrNot&&this.refresh==false)){
						batchFrame.info_buy_process_Text.setText("！！发送礼物过程中出现xpath错误问题，请检查后重试！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
		//				exitBrowAfterRunning();
						this.refresh = false;
						return false;
					}else if(!sendPresentOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！发送礼物失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
				if(quitCharge()){return true;};
				//跳转到支付确认页面，选中同意条款，点击确认购买
				batchFrame.info_buy_process_Text.setText("！！！最终的确认页面，请等待处理！！！");
				if(!confirmPage()){
					//记录日志
					log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
					log.insertLog();
		//			exitBrowAfterRunning();
					return false;
				}
				
		
				batchFrame.hasBuyNumText.setText("已经购买"+(gameNumTemp+1)+"个游戏！");
		//		batchFrame.info_buy_process_Text.setText("！！！购买成功，请退出！！！");	
				//说明该游戏已经购买
				((CheckValue)frame.gamesComboBox.getItemAt(gameNumTemp)).bolValue=false;
				gameNumTemp++;
				//日志
				log.setMessage("购买成功！"+this.game_name+"，购买账户为"+this.default_account+"，购买后余额为"+this.finalBalanceStr+"，发送邮箱为"+this.default_email);
				log.insertLog();
		//		batchFrame.closeDriverTag = true;
		//		exitBrowAfterRunning();
				}
		}
		//判断该用户是否全部购买
		if (gameNumTemp==selectgameNum) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * 循环账户购买多个游戏
	 */
	public synchronized void batchBuy(){
		this.log = new SteamLog(this.frame);
		this.seleniumMethods.setBuySteamGameXpath(this.buySteamGameXpath);
		
		if(quitCharge()){return;};
		batchFrame.info_buy_process_Text.setText("！！！正在登录中！！！");
		
		//初始化i记录购买游戏的个数
		int i = 0;
		//记录账户的个数
		for(int j=0;j<this.buyGameAccountID.size()&&i<this.gameNum;j++){
			this.nextBuyGameAccount = false;
			
			//登录对应账户
			String account = this.buyGameAccountID.get(j);
			this.default_account = account;
			String password = this.buyGameAccounts.get(account);
			this.defaule_password = password;	
			batchFrame.info_default_account_text.setText(account);
			
			String[] loginOrNot = new String[2];
			for(int x=0;x<5;x++){
				loginOrNot = this.seleniumMethods.login(this.buySteamGameXpath.get("loginPageLink"), account, password, this.buySteamGameXpath.get("loginIdField"), this.buySteamGameXpath.get("loginPwField"), this.buySteamGameXpath.get("loginButton"));
				if(loginOrNot[0] == "true"){
					batchFrame.info_buy_process_Text.setText("账户更换成功，即将购买游戏");
					break;
				}else if(x==4){
					batchFrame.info_buy_process_Text.setText("！！！登陆5次错误，请检查登录元素xpath后再试！！！");
					//记录日志
					log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
					log.insertLog();
					exitBrowAfterRunning();
					return;
				}else{
					batchFrame.info_buy_process_Text.setText("！！！登陆异常，正在重试！！！");
				}
			}
			
			//查看当前账户余额
			this.currentBalanceStr = loginOrNot[1];
			
			
			//m记录5次循环
			for(int m=0;m<5&&i<this.gameNum;m++){
				batchFrame.hasBuyNumText.setText("已经购买"+i+"个游戏，正在购买第"+(i+1)+"个游戏！");			
				
				//跳转到游戏界面，查找加入购物车的按钮，循环查找
				if(quitCharge()){return;};
				batchFrame.info_buy_process_Text.setText("！！！跳转到对应游戏界面并加入购物车！！！");
				driver.get(this.steamGame.getLink());
				System.out.println("这里跳转了！！！！"+this.steamGame.getLink());
				for(int x=0;x<5;x++){
					if(quitCharge()){return;};
					boolean addToCartOrNot = this.seleniumMethods.addToCart(this.steamGame.getLink(), this.steamGame.getXpath());
					
					System.out.println("这里是不是true~！！！！"+addToCartOrNot);
					if(addToCartOrNot){
						break;
					}else if(x==4){
						batchFrame.info_buy_process_Text.setText("！！查找购物车按钮5次出错，请更新游戏xpath！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
						exitBrowAfterRunning();
						return;
					}else{
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
		
				if(quitCharge()){return;};
				//调用purchase as a gift函数，循环5次
				for(int x=0;x<5;x++){
					if(quitCharge()){return;};
					boolean purchaseOrNot = purchaseAsGift();
					System.out.println("这里的purchase~~~"+purchaseOrNot);
					
					if(purchaseOrNot){
						this.refresh = false;
						break;
					}else if(x==4){
						batchFrame.info_buy_process_Text.setText("！！查找以礼物发送按钮5次出错，请更新游戏xpath！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
						exitBrowAfterRunning();
						this.refresh = false;
						return;
					}else if(!purchaseOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！查找以礼物发送按钮失败，正在重试！！");
						driver.navigate().refresh();
					}else if(!purchaseOrNot&&this.refresh==false){
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();				
						break;
						//如果是由于账户余额不足造成购买失败，则使用下一个账户，如果是其他错误造成，则终止程序
//						if(this.nextBuyGameAccount){
//							batchFrame.info_buy_process_Text.setText("！！！当前账户余额不足，正在更换下一个账户！！！");
//							break;
//						}
//						exitBrowAfterRunning();
//						return;
					}
				}
				//如果是由于账户余额不足造成购买失败，则使用下一个账户，如果是其他错误造成，则终止程序
				if(this.nextBuyGameAccount){
					batchFrame.info_buy_process_Text.setText("！！！当前账户余额不足，正在更换下一个账户！！！");
					continue;
				}
				//调用发送礼物的函数，循环5次
				batchFrame.info_buy_process_Text.setText("！！！正在发送礼物，请稍等！！！");
				for(int x=0;x<5;x++){
					if(quitCharge()){return;};
					boolean sendPresentOrNot = sendPresent();
					if(sendPresentOrNot){
						this.refresh = false;
						break;
					}else if(x==4||(!sendPresentOrNot&&this.refresh==false)){
						batchFrame.info_buy_process_Text.setText("！！发送礼物过程中出现xpath错误问题，请检查后重试！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
						exitBrowAfterRunning();
						this.refresh = false;
						return;
					}else if(!sendPresentOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！发送礼物失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
				if(quitCharge()){return;};
				//跳转到支付确认页面，选中同意条款，点击确认购买
				batchFrame.info_buy_process_Text.setText("！！！最终的确认页面，请等待处理！！！");
				if(!confirmPage()){
					//首先记录日志，此次购买失败，其次要判断是否是由于账户问题造成购买失败
					log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
					log.insertLog();
					
					//如果是由于账户问题造成购买失败，则使用下一个账户，如果是其他错误造成，则终止程序
					if(this.nextBuyGameAccount){
						batchFrame.info_buy_process_Text.setText("！！！当前账户充值有问题，正在更换下一个账户！！！");
						break;
					}
					exitBrowAfterRunning();
					return;
				}
//				
				//日志
				log.setMessage("购买成功！"+this.game_name+"，购买账户为"+account+"，购买后余额为"+this.finalBalanceStr+"，发送邮箱为"+this.default_email);
				log.insertLog();
				batchFrame.info_buy_process_Text.setText("账户"+account+"购买一个游戏成功，还剩"+(this.gameNum-i-1)+"个游戏");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}
		//如果账户用完，则退出，剩下的游戏不再继续购买，并且记录购买个数
			
		batchFrame.hasBuyNumText.setText("已经购买"+i+"个游戏！");
		batchFrame.info_buy_process_Text.setText(i+"个游戏购买成功，还剩"+(this.gameNum-i)+"个游戏未购买！");
		batchFrame.closeDriverTag = true;
		exitBrowAfterRunning();
		return;
		
	}

	/*
	 * 批量加入仓库的函数
	 */
	public synchronized boolean batchBuyToWare(){
		this.log = new SteamLog(this.frame);
		this.seleniumMethods.setBuySteamGameXpath(this.buySteamGameXpath);
		//共有所烧个购买游戏
		int selectgameNum=this.buySteamGame.size();
		int gameNumTemp=0;
		//循环判断是否登录成功，不成功，则结束程序 
		// 这个循环应该放到 login 函数里面
		String[] loginOrNot = new String[2];
		for(int i=0;i<5;i++){
			String account = this.default_account;
			String password = this.defaule_password;
			loginOrNot = this.seleniumMethods.login(this.buySteamGameXpath.get("loginPageLink"), account, password, this.buySteamGameXpath.get("loginIdField"), this.buySteamGameXpath.get("loginPwField"), this.buySteamGameXpath.get("loginButton"));
			if(loginOrNot[0]=="true"){
				break;
			}else if(i==4){
				batchFrame.info_buy_process_Text.setText("！！！登陆5次错误，请检查登录元素xpath后再试！！！");
				//记录日志
				log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
				log.insertLog();
//				exitBrowAfterRunning();
				return false;
			}else{
				batchFrame.info_buy_process_Text.setText("！！！登陆异常，正在重试！！！");
			}
		}
		batchFrame.info_buy_process_Text.setText("！！！正在登陆中！！！");
		
		//查看当前账户余额
		this.currentBalanceStr = loginOrNot[1];
		System.out.println("当前余额："+this.currentBalanceStr);
		
		Iterator iterator=this.buySteamGame.keySet().iterator();
		//购买游戏
		while (iterator.hasNext()) {
			for(int n=0;n<this.gameNum;n++){
					String gamenameTemp=iterator.next().toString();
					//设置当前steamgame
					this.setSteamGame(this.buySteamGame.get(gamenameTemp));
					this.setGame_name(gamenameTemp);
					//设置显示游戏名
					CheckValue value;
					for (int m = 0; m < frame.gamesComboBox.getItemCount(); m++) {
						value=(CheckValue) frame.gamesComboBox.getItemAt(m);
						if (this.game_name.equals(value.value)) {
							frame.gamesComboBox.setSelectedIndex(m);
							value.bolValue=true;
						}
					}
					batchFrame.setText();
					
				
			    if(quitCharge()){return true;};
			    batchFrame.hasBuyNumText.setText("已经购买"+gameNumTemp+"个游戏，正在购买第"+(gameNumTemp+1)+"个游戏！");
				
				//跳转到游戏界面，查找加入购物车的按钮，循环查找
				if(quitCharge()){return true;};
				batchFrame.info_buy_process_Text.setText("！！！跳转到对应游戏界面并加入购物车！！！");
				driver.get(this.steamGame.getLink());
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean addToCartOrNot = this.seleniumMethods.addToCart(this.steamGame.getLink(), this.steamGame.getXpath());
					if(addToCartOrNot){
						break;
					}else if(i==4){
						batchFrame.info_buy_process_Text.setText("！！查找购物车按钮5次出错，请更新游戏xpath！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
		//				exitBrowAfterRunning();
						return false;
					}else{
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}	
				
				if(quitCharge()){return true;};
				
				//调用purchase as a gift函数，循环5次
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean purchaseOrNot = purchaseAsGift();
					if(purchaseOrNot){
						this.refresh = false;
						break;
					}else if(i==4||(!purchaseOrNot&&this.refresh==false)){
						batchFrame.info_buy_process_Text.setText("！！查找购物车按钮5次出错或者账户余额不足，请更新游戏xpath或充值！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
		//				exitBrowAfterRunning();
						this.refresh = false;
						return false;
					}else if(!purchaseOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
				//调用发送礼物的函数，循环5次
				batchFrame.info_buy_process_Text.setText("！！！正在加入仓库，请稍等！！！");
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean sendPresentOrNot = addToWare();
					if(sendPresentOrNot){
						this.refresh = false;
						break;
					}else if(i==4||(!sendPresentOrNot&&this.refresh==false)){
						batchFrame.info_buy_process_Text.setText("！！发送礼物过程中出现xpath错误问题，请检查后重试！！");
						//记录日志
						log.setMessage("加入仓库失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr);
						log.insertLog();
		//				exitBrowAfterRunning();
						this.refresh = false;
						return false;
					}else if(!sendPresentOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！加入仓库失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
				if(quitCharge()){return true;};
				//跳转到支付确认页面，选中同意条款，点击确认购买
				batchFrame.info_buy_process_Text.setText("！！！最终的确认页面，请等待处理！！！");
				if(!confirmPage()){
					//记录日志
					log.setMessage("加入仓库失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr);
					log.insertLog();
		//			exitBrowAfterRunning();
					return false;
				}
				
		
				batchFrame.hasBuyNumText.setText("已经加入"+(gameNumTemp+1)+"个游戏！");
		//		batchFrame.info_buy_process_Text.setText("！！！购买成功，请退出！！！");	
				//说明该游戏已经购买
				((CheckValue)frame.gamesComboBox.getItemAt(gameNumTemp)).bolValue=false;
				gameNumTemp++;
				//日志
				log.setMessage("加入仓库成功！"+this.game_name+"，购买账户为"+this.default_account+"，购买后余额为"+this.finalBalanceStr);
				log.insertLog();
		//		batchFrame.closeDriverTag = true;
		//		exitBrowAfterRunning();
				}
		}
		//判断该用户是否全部购买
		if (gameNumTemp==selectgameNum) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 * 为自己购买一个或多个游戏的函数
	 */
	public synchronized boolean batchBuyMyself(){
		this.log = new SteamLog(this.frame);
		this.seleniumMethods.setBuySteamGameXpath(this.buySteamGameXpath);
		//共有所烧个购买游戏
		int selectgameNum=this.buySteamGame.size();
		int gameNumTemp=0;
		//循环判断是否登录成功，不成功，则结束程序 
		// 这个循环应该放到 login 函数里面
		String[] loginOrNot = new String[2];
		for(int i=0;i<5;i++){
			String account = this.default_account;
			String password = this.defaule_password;
			loginOrNot = this.seleniumMethods.login(this.buySteamGameXpath.get("loginPageLink"), account, password, this.buySteamGameXpath.get("loginIdField"), this.buySteamGameXpath.get("loginPwField"), this.buySteamGameXpath.get("loginButton"));
			if(loginOrNot[0]=="true"){
				break;
			}else if(i==4){
				batchFrame.info_buy_process_Text.setText("！！！登陆5次错误，请检查登录元素xpath后再试！！！");
				//记录日志
				log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
				log.insertLog();
//				exitBrowAfterRunning();
				return false;
			}else{
				batchFrame.info_buy_process_Text.setText("！！！登陆异常，正在重试！！！");
			}
		}
		batchFrame.info_buy_process_Text.setText("！！！正在登陆中！！！");
		
		//查看当前账户余额
		this.currentBalanceStr = loginOrNot[1];
		System.out.println("当前余额："+this.currentBalanceStr);
		
		Iterator iterator=this.buySteamGame.keySet().iterator();
		//购买游戏
		while (iterator.hasNext()) {
			for(int n=0;n<this.gameNum;n++){
					String gamenameTemp=iterator.next().toString();
					//设置当前steamgame
					this.setSteamGame(this.buySteamGame.get(gamenameTemp));
					this.setGame_name(gamenameTemp);
					//设置显示游戏名
					CheckValue value = null;
					for (int m = 0; m < frame.gamesComboBox.getItemCount(); m++) {
						value=(CheckValue) frame.gamesComboBox.getItemAt(m);
						if (this.game_name.equals(value.value)) {
							frame.gamesComboBox.setSelectedIndex(m);
							value.bolValue=true;
						}
					}
		      	batchFrame.setText();
					
				
			    if(quitCharge()){return true;};
			    batchFrame.hasBuyNumText.setText("已经购买"+gameNumTemp+"个游戏，正在购买第"+(gameNumTemp+1)+"个游戏！");
				
				//跳转到游戏界面，查找加入购物车的按钮，循环查找
				if(quitCharge()){return true;};
				batchFrame.info_buy_process_Text.setText("！！！跳转到对应游戏界面并加入购物车！！！");
				driver.get(this.steamGame.getLink());
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean addToCartOrNot = this.seleniumMethods.addToCart(this.steamGame.getLink(), this.steamGame.getXpath());
					if(addToCartOrNot){
						break;
					}else if(i==4){
						batchFrame.info_buy_process_Text.setText("！！查找购物车按钮5次出错，请更新游戏xpath！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
		//				exitBrowAfterRunning();
						return false;
					}else{
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}	
				
				if(quitCharge()){return true;};
				
				//调用purchase as a gift函数，循环5次
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean purchaseOrNot = purchaseAsGift();
					if(purchaseOrNot){
						this.refresh = false;
						break;
					}else if(i==4||(!purchaseOrNot&&this.refresh==false)){
						batchFrame.info_buy_process_Text.setText("！！查找购物车按钮5次出错或者账户余额不足，请更新游戏xpath或充值！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
		//				exitBrowAfterRunning();
						this.refresh = false;
						return false;
					}else if(!purchaseOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
				//调用发送礼物的函数，循环5次
				batchFrame.info_buy_process_Text.setText("！！！为自己购买，请稍等！！！");
				for(int i=0;i<5;i++){
					if(quitCharge()){return true;};
					boolean sendPresentOrNot = purchaseForMyself();
					if(sendPresentOrNot){
						this.refresh = false;
						break;
					}else if(i==4||(!sendPresentOrNot&&this.refresh==false)){
						batchFrame.info_buy_process_Text.setText("！！发送礼物过程中出现xpath错误问题，请检查后重试！！");
						//记录日志
						log.setMessage("为自己购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr);
						log.insertLog();
		//				exitBrowAfterRunning();
						this.refresh = false;
						return false;
					}else if(!sendPresentOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！为自己购买失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
				if(quitCharge()){return true;};
				//跳转到支付确认页面，选中同意条款，点击确认购买
				batchFrame.info_buy_process_Text.setText("！！！最终的确认页面，请等待处理！！！");
				if(!confirmPage()){
					//记录日志
					log.setMessage("为自己购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr);
					log.insertLog();
		//			exitBrowAfterRunning();
					return false;
				}
				
		
				batchFrame.hasBuyNumText.setText("已经购买"+(gameNumTemp+1)+"个游戏！");
		//		batchFrame.info_buy_process_Text.setText("！！！购买成功，请退出！！！");	
				//说明该游戏已经购买
				((CheckValue)frame.gamesComboBox.getItemAt(gameNumTemp)).bolValue=false;
				gameNumTemp++;
				//日志
				log.setMessage("购买成功！"+this.game_name+"，购买账户为"+this.default_account+"，购买后余额为"+this.finalBalanceStr);
				log.insertLog();
		//		batchFrame.closeDriverTag = true;
		//		exitBrowAfterRunning();
				}
		}
		//判断该用户是否全部购买
		if (gameNumTemp==selectgameNum) {
			return true;
		}else {
			return false;
		}
	}
	
	/*
	 *	跳区购买一个或多个游戏
	 */
	public void jumpAreaBuy(){
		this.log = new SteamLog(this.frame);
		this.seleniumMethods.setBuySteamGameXpath(this.buySteamGameXpath);
		
		if(quitCharge()){return;};
		batchFrame.info_buy_process_Text.setText("！！！正在登录中！！！");
		
		//初始化i记录购买游戏的个数
		int i = 0;
		//记录账户的个数
		for(int j=0;j<this.buyGameAccountID.size()&&i<this.gameNum;j++){
			this.nextBuyGameAccount = false;
			
			String account = this.default_account;
			String password = this.defaule_password;
			//如果购买个数为1，则使用默认的账户，否则登陆循环账户
			if(this.gameNum>1){
				//登录对应账户
				account = this.buyGameAccountID.get(j);
				this.default_account = account;
				password = this.buyGameAccounts.get(account);
				this.defaule_password = password;				
			}else{
				
			}
			batchFrame.info_default_account_text.setText(account);
			
			String[] loginOrNot = new String[2];
			for(int x=0;x<5;x++){
				loginOrNot = this.seleniumMethods.login("https://store.steampowered.com/login/?cc=ru", account, password, this.buySteamGameXpath.get("loginIdField"), this.buySteamGameXpath.get("loginPwField"), this.buySteamGameXpath.get("loginButton"));
				if(loginOrNot[0] == "true"){
					batchFrame.info_buy_process_Text.setText("账户更换成功，即将购买游戏");
					break;
				}else if(x==4){
					batchFrame.info_buy_process_Text.setText("！！！登陆5次错误，请检查登录元素xpath后再试！！！");
					//记录日志
					log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
					log.insertLog();
					exitBrowAfterRunning();
					return;
				}else{
					batchFrame.info_buy_process_Text.setText("！！！登陆异常，正在重试！！！");
				}
			}
			
			//查看当前账户余额
			this.currentBalanceStr = loginOrNot[1];
			
			
			//m记录5次循环
			for(int m=0;m<5&&i<this.gameNum;m++){
				//此处需要注意，避免只购买一个的情况下购买失败时i变量未变，而不停地循环账户造成的错误
				if(this.gameNum==1){
					this.gameNum = 0;
				}
				
				batchFrame.hasBuyNumText.setText("已经购买"+i+"个游戏，正在购买第"+(i+1)+"个游戏！");			
				
				//跳转到万能游戏界面，查找加入购物车的按钮，循环查找
				if(quitCharge()){return;};
				batchFrame.info_buy_process_Text.setText("！！！跳转到万能游戏界面并加入购物车！！！");
				if(""!=this.handleStart){
					System.out.println(this.handleStart);
					driver.switchTo().window(this.handleStart);
				}
				System.out.println("万能游戏网址："+this.uniSteamGame.getLink()+"这边能输出"+i);
				driver.get(this.uniSteamGame.getLink());
				System.out.println("这里跳转了！！！！"+this.uniSteamGame.getLink());
				for(int x=0;x<5;x++){
					if(quitCharge()){return;};
					boolean addToCartOrNot = this.seleniumMethods.addToCart(this.uniSteamGame.getLink(), this.uniSteamGame.getXpath());
					
					System.out.println("这里是不是true~！！！！"+addToCartOrNot);
					if(addToCartOrNot){
						break;
					}else if(x==4){
						batchFrame.info_buy_process_Text.setText("！！查找万能游戏购物车按钮5次出错，请更新游戏xpath！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
						exitBrowAfterRunning();
						return;
					}else{
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
		
				if(quitCharge()){return;};
				//调用purchase as a gift函数，循环5次
				for(int x=0;x<5;x++){
					if(quitCharge()){return;};
					boolean purchaseOrNot = jumpAreaPurchaseAsGift();
					System.out.println("这里的purchase~~~"+purchaseOrNot);
					
					if(purchaseOrNot){
						this.refresh = false;
						break;
					}else if(x==4){
						batchFrame.info_buy_process_Text.setText("！！查找以礼物发送按钮5次出错，请更新游戏xpath！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
						exitBrowAfterRunning();
						this.refresh = false;
						return;
					}else if(!purchaseOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！查找以礼物发送按钮失败，正在重试！！");
						driver.navigate().refresh();
					}else if(!purchaseOrNot&&this.refresh==false){
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();				
						
						//如果是由于账户余额不足造成购买失败，则使用下一个账户，如果是其他错误造成，则终止程序
						if(this.nextBuyGameAccount){
							batchFrame.info_buy_process_Text.setText("！！！当前账户余额不足，正在更换下一个账户！！！");
							break;
						}
						exitBrowAfterRunning();
						return;
					}
				}
				
				/*
				 * !!!Important!!!
				 * 打开一个新的标签，转到真正需要购买游戏的页面
				 */
				if("".equals(this.handleEnd)&&"".equals(this.handleStart)){
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("function createDoc(){var w = window.open(); w.document.open(); w.document.write('<h1>Hello World!</h1>'); w.document.close();}; createDoc();");
					
					this.handleStart = driver.getWindowHandle();//获取万能页面的句柄
					System.out.println("当前页面句柄："+this.handleStart);
					Set<String> handles = driver.getWindowHandles();
					for(String handle : handles){
						if(!handle.equals(this.handleStart)){
							this.handleEnd = handle;//获取真正需要购买的游戏页面的句柄
						}
					}
				}
				
				driver.switchTo().window(this.handleEnd);//将焦点移到真正购买的游戏页面
				
				//跳转到真正购买的游戏界面，查找加入购物车的按钮，循环查找
				if(quitCharge()){return;};
				batchFrame.info_buy_process_Text.setText("！！！跳转到游戏界面并加入购物车！！！");
				
				String newSteamGameLink = changeGameLink(this.buySteamGameXpath.get("country"), this.steamGame.getLink());
				driver.get(newSteamGameLink);
				System.out.println("这里跳转了！！！！"+this.steamGame.getLink());
				for(int x=0;x<5;x++){
					if(quitCharge()){return;};
					
					boolean addToCartOrNot = this.seleniumMethods.addToCart(newSteamGameLink, this.steamGame.getXpath());
					
					System.out.println("这里是不是true~！！！！"+addToCartOrNot);
					if(addToCartOrNot){
						break;
					}else if(x==4){
						batchFrame.info_buy_process_Text.setText("！！查找游戏购物车按钮5次出错，请更新游戏xpath！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
						exitBrowAfterRunning();
						return;
					}else{
						batchFrame.info_buy_process_Text.setText("！！加入购物车失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				//此时需要移除购物车中的万能游戏，有待扩展。。循环五次执行
				//由于remove的xpath会变，所以使用获取所有的remove，然后定位第二个的方法
				List removeAS = null;
				try{
					removeAS = driver.findElements(By.className("remove_link"));
				}catch(Exception e){
					System.out.println(e);
					return;
				}
				String removeLinkText = this.frame.removeLinkTextField.getText();
				int removeLink = Integer.parseInt(removeLinkText);
				System.out.println("删除的位置是："+removeLink);
				WebElement removeA = (WebElement) removeAS.get(removeLink);
				removeA.click();
				
//				WebElement removeA = null;
//				try{
//					driver.findElement(By.xpath("//*[@id='cart_row_602689728667236399']/div/div[1]/a")).click();//*[@id="cart_row_602689728667236399"]/div/div[1]/a
//				}catch(Exception e){
//					System.out.println(e);
//				}
//				removeA.click();
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				//跳转到http://store.steampowered.com/cart/?cc=ru看按钮是否是disable的
				driver.get("http://store.steampowered.com/cart/?cc=ru");
				try{
					driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/span[1]"));					
				}catch(Exception e){
					//如果没有找到span元素，则说明按钮是可以点击的，能找到说明按钮已经disable
					System.out.println("作为礼物购买的按钮还能点击，请重试！");
					return;
				}
				
				System.out.println("跳转到刚开始的页面句柄："+this.handleStart);
				//重新将driver句柄定位到万能游戏的页面
				driver.switchTo().window(this.handleStart);
//				driver.navigate().refresh();
				System.out.println(driver.getTitle());
				
				//调用发送礼物的函数，循环5次
				batchFrame.info_buy_process_Text.setText("！！！正在发送礼物，请稍等！！！");
				for(int x=0;x<5;x++){
					if(quitCharge()){return;};
					boolean sendPresentOrNot = sendPresent();
					if(sendPresentOrNot){
						this.refresh = false;
						break;
					}else if(x==4||(!sendPresentOrNot&&this.refresh==false)){
						batchFrame.info_buy_process_Text.setText("！！发送礼物过程中出现xpath错误问题，请检查后重试！！");
						//记录日志
						log.setMessage("购买失败！"+this.game_name+"，购买账户为"+this.default_account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
						log.insertLog();
						exitBrowAfterRunning();
						this.refresh = false;
						return;
					}else if(!sendPresentOrNot&&this.refresh==true){
						batchFrame.info_buy_process_Text.setText("！！发送礼物失败，正在重试！！");
						driver.navigate().refresh();
					}
				}
				
//				if(quitCharge()){return;};
//				//跳转到支付确认页面，选中同意条款，点击确认购买
//				batchFrame.info_buy_process_Text.setText("！！！最终的确认页面，请等待处理！！！");
//				if(!confirmPage()){
//					//首先记录日志，此次购买失败，其次要判断是否是由于账户问题造成购买失败
//					log.setMessage("购买失败！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
//					log.insertLog();
//					
//					//如果是由于账户问题造成购买失败，则使用下一个账户，如果是其他错误造成，则终止程序
//					if(this.nextBuyGameAccount){
//						batchFrame.info_buy_process_Text.setText("！！！当前账户充值有问题，正在更换下一个账户！！！");
//						break;
//					}
//					exitBrowAfterRunning();
//					return;
//				}
//				
				//日志
				log.setMessage("购买成功！"+this.game_name+"，购买账户为"+account+"，账户余额为"+this.currentBalanceStr+"，发送邮箱为"+this.default_email);
				log.insertLog();
				batchFrame.info_buy_process_Text.setText("账户"+account+"购买一个游戏成功，还剩"+(this.gameNum-i-1)+"个游戏");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
		}
		//如果账户用完，则退出，剩下的游戏不再继续购买，并且记录购买个数
			
		batchFrame.hasBuyNumText.setText("已经购买"+i+"个游戏！");
		batchFrame.info_buy_process_Text.setText(i+"个游戏购买成功，还剩"+(this.gameNum-i)+"个游戏未购买！");
		batchFrame.closeDriverTag = true;
		exitBrowAfterRunning();
		return;
		
	}

	
}
