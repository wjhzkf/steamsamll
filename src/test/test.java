package test;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class test {

	public static void main(String[] args) throws IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();	
		driver.manage().window().maximize();
//		driver.manage().window().setSize(new Dimension(600,400));
//		driver.manage().window().setPosition(new Point(0,0));
		
		//���õȴ�ʱ��
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://store.steampowered.com/login/?cc=ru");
		
//		WebElement login_userName = driver.findElement(By.xpath("//*[@id='input_username']"));//*[@id="input_username"]
//		WebElement login_password = driver.findElement(By.xpath("//*[@id='input_password']"));//*[@id="input_password"]
//		WebElement login_button = driver.findElement(By.xpath("//*[@id='login_btn_signin']/a"));//*[@id="login_btn_signin"]/a
		WebElement login_userName = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='input_username']")));
		WebElement login_password = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='input_password']")));//*[@id="input_password"]
		WebElement login_button = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login_btn_signin']/a")));//*[@id="login_btn_signin"]/a
		
		login_userName.clear();
		login_password.clear();
		login_userName.sendKeys("wangyong900930");
		login_password.sendKeys("wangyong900930");	
		login_button.click();
	
		System.out.println(driver.getCurrentUrl());
		//*[@id="cart_link"]
		WebElement cart = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='cart_link']")));
		cart.click();
	}
	
}
