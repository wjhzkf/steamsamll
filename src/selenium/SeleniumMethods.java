/*
 * Selenium���ú�����ȡ
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
	//��ȡ�������WebDriverʵ����WebDriverWaitʵ��
	private WebDriver driver;
	private WebDriverWait wait;
	
	//����WalletXpath�Ĺ�ϣ��
	public Map<String, String> WalletXpath;
	//����BuySteamGameXpath�Ĺ�ϣ��
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
	 * ��¼������ȡ
	 * �����������¼ҳ��ַ���û��������룬�Լ���¼ҳ�������xpath
	 */
	public String[] login(String loginPageLink, String account, String password, String loginIdField, String loginPwField, String loginButton){
		System.out.println("��ǰ�˻���"+account);

		this.driver.manage().deleteAllCookies();
		this.driver.get(loginPageLink);
		//�ж��Ƿ���ҵ���¼��ť��û�ҵ����׳��쳣������ִ�����沽�裬��¼������ֹͣ
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
			// ����Ҫclickable(element not visible ����) ��Ϊsend keys �Զ����� login_button to be clickable
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
		 * ��������Ϊ��½������Ҫһ��ʱ�䣬��½�ɹ�����Զ���ת��steam��ҳ��Ҫ�ǲ����õȴ�ʱ�䣬
		 * ֱ����ת������ҳ�棬��½����ɹ�
		 */
		//. ֹͣ����ִ��10��
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//����2. ��׽��½��ת�������˻��������׽���˻������Ϣ��������½�ɹ������element not found��������½ʧ��
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
	 * ��ӵ����ﳵ�ĺ���
	 */
	public boolean addToCart(String gameLink, String gameXpath){
		//��ת����Ӧ��Ϸ����
		String addCart_button_xpath = gameXpath;
		
		//�ҵ���Ӧ����Ϸ�󣬿��Ƿ���Ҫ���������ҳ�棬�ǵĻ�������
		String game_local_link = driver.getCurrentUrl();
		try{
			Select age_select = new Select(driver.findElement(By.xpath(this.buySteamGameXpath.get("ageSelect"))));	
			age_select.selectByValue("1980");
			WebElement enter_link = driver.findElement(By.xpath(this.buySteamGameXpath.get("ageEnterButton")));
			enter_link.click();
			game_local_link = driver.getCurrentUrl();
//			Thread.sleep(3000);
		}catch(Exception e){
			System.out.println("����������û��������дҪ�󣡣�����");
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
