package selenium;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TaobaoAuto {
	private String autoUrl;
	private WebDriver driver;
	private WebDriverWait wait;
	
	public String getAutoUrl() {
		return autoUrl;
	}
	public void setAutoUrl(String autoUrl) {
		this.autoUrl = autoUrl;
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
	
	public TaobaoAuto(){}
	
	/*
	 * 启动浏览器的函数
	 */
	public void startBrowser(){
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
	    this.wait = new WebDriverWait(driver, 10);
	}
	
	/*
	 * 获取淘宝授权主函数
	 */
	public void getTaobaoAuto(){
		startBrowser();
		this.driver.get(this.autoUrl);
		return;
	}
}
