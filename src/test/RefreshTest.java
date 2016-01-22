package test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RefreshTest {

	public static void main(String[] args) {
		WebDriver driver;
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("driver/Block-image_v1.1.crx"), new File("driver/StopFlash-Flash-Blocker_v0.1.5.crx"));
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver(options);	
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();

		driver.get("https://store.steampowered.com/login/?cc=ru");
		
		WebElement login_userName = driver.findElement(By.xpath("//*[@id='input_username']"));//*[@id="input_username"]
		WebElement login_password = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='input_password']")));//*[@id="input_password"]
		WebElement login_button = (new WebDriverWait(driver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login_btn_signin']/a")));//*[@id="login_btn_signin"]/a
		
		login_userName.clear();
		login_password.clear();
		login_userName.sendKeys("wangyong900930");
		login_password.sendKeys("wangyong900930");	
		login_button.click();
		
		WebElement balance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='header_wallet_balance']")));
		System.out.println(balance.getText());
		
		driver.get("http://store.steampowered.com/app/265330/");
		WebElement addCartButton = driver.findElement(By.xpath("//*[@id='game_area_purchase']/div[1]/div/div[2]/div/div[2]/a"));
		addCartButton.click();
		
		driver.navigate().refresh();
		
	}

}
