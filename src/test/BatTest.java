package test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BatTest {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.baidu.com");
		
		String cmd = "cmd /c start "+"kill_chromedriver.bat";
		Runtime.getRuntime().exec(cmd);

	}

}
