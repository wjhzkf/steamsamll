/*
 * Selenium常用函数提取
 */
package selenium;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumMethods {
	//获取调用类的WebDriver实例和WebDriverWait实例
	private WebDriver driver;
	private WebDriverWait wait;
	
	//保存WalletXpath的哈希表
	public Map<String, String> WalletXpath;
	//保存BuySteamGameXpath的哈希表
	public Map<String, String> buySteamGameXpath;
	
	private static SeleniumMethods seleniumMethods;
	private SeleniumMethods(){}

	public static SeleniumMethods getInstance(){
		if(seleniumMethods==null){
			return seleniumMethods = new SeleniumMethods();
		}else{
			return seleniumMethods;
		}
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

	public Map<String, String> getWalletXpath() {
		return WalletXpath;
	}

	public void setWalletXpath(Map<String, String> walletXpath) {
		WalletXpath = walletXpath;
	}

	public Map<String, String> getBuySteamGameXpath() {
		return buySteamGameXpath;
	}

	public void setBuySteamGameXpath(Map<String, String> buySteamGameXpath) {
		this.buySteamGameXpath = buySteamGameXpath;
	}

	/*
	 * 登录函数提取
	 * 传入参数：登录页地址，用户名，密码，以及登录页面的三个xpath
	 */
	public String[] login(String loginPageLink, String account, String password, String loginIdField, String loginPwField, String loginButton){
		System.out.println("当前账户："+account);

		this.driver.manage().deleteAllCookies();
		this.driver.get(loginPageLink);
		//判断是否查找到登录按钮，没找到则抛出异常，继续执行下面步骤，登录错误则停止
		WebElement login_userName = null;
		WebElement login_password = null;
		WebElement login_button = null; 
		WebElement account_balance = null;
		String account_balance_value = null;
		String login_status[] = new String[2];
		int ii = 0;
		login_status[0] = "false";
		login_status[1] = account_balance_value;
		
		try{
			login_userName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loginIdField)));
			login_password = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loginPwField)));
			login_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loginButton)));
			// 不需要clickable(element not visible 错误) 因为send keys 自动激活 login_button to be clickable
			//login_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loginButton)));
		}catch(Exception e){
/*			if(ii<5)
			{login(this.account, this.password, String loginIdField, String loginPwField, String loginButton);
			}
			}*/
			return login_status;
		}
		login_userName.clear();
		login_password.clear();
		login_userName.sendKeys(account);
		login_password.sendKeys(password);	
		login_button.click();
		
		/*
		 * 这里是因为登陆过程需要一定时间，登陆成功后会自动跳转到steam主页，要是不设置等待时间，
		 * 直接跳转到购买页面，登陆不会成功
		 */
		//. 停止程序执行10秒
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//方案2. 捕捉登陆跳转后界面的账户余额，如果捕捉到账户余额信息，表明登陆成功，如果element not found，表明登陆失败
		try{
			account_balance = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header_wallet_balance")));
			account_balance_value = account_balance.getText();
			System.out.println("balance = "+account_balance_value);
		}catch(Exception e1){
			return login_status;
		}
		login_status[0] = "true";
		login_status[1] = account_balance_value;
		return login_status;
	}
	
	/*
	 * 添加到购物车的函数
	 */
	public boolean addToCart(String gameLink, String gameXpath){
		//跳转到对应游戏界面
		String addCart_button_xpath = gameXpath;
		
		//找到对应的游戏后，看是否是要输入年龄的页面，是的话则输入
		String game_local_link = driver.getCurrentUrl();
		try{
			Select age_select = new Select(driver.findElement(By.xpath(this.buySteamGameXpath.get("ageSelect"))));	
			age_select.selectByValue("1980");
			WebElement enter_link = driver.findElement(By.xpath(this.buySteamGameXpath.get("ageEnterButton")));
			enter_link.click();
			game_local_link = driver.getCurrentUrl();
//			Thread.sleep(3000);
		}catch(Exception e){
			System.out.println("！！！！！没有生日填写要求！！！！");
		}
		System.out.println(game_local_link);
		
		WebElement addToCart_button = null;
		try{
			addToCart_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(addCart_button_xpath)));
		}catch(Exception e){
			return false;
		}
		
		try{
			addToCart_button.click();
		}catch(Exception e){
			return false;
		}
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return true;
	}
	

}
