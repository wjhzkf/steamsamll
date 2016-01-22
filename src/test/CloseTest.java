package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloseTest {

	public static WebDriver driver;
	public static int i;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();	
		driver.manage().window().maximize();
		
		driver.get("http://www.baidu.com");
		i = 0;
		
		try{
//			WebElement a = driver.findElement(By.xpath("sdagas"));
			WebElement a = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("dsg")));
		}catch(Exception e){
			System.out.println("出错，看能否继续执行");
		}
		
		System.out.println("能继续执行");
	}

	public static void closeDriver(){
		
	}
	
}
