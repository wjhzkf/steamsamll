package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class JUnitTest {

	private WebDriver driver;
	
	@Test
	public void propertyTest() throws FileNotFoundException, IOException {
		Properties pros = new Properties();
		pros.load(new FileInputStream("config/BuySteamGameXpath-hk.properties"));
		Iterator iter = pros.entrySet().iterator();
		while(iter.hasNext()){
			Entry entry = (Entry) iter.next();
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	}
	
	@Test
	public void balanceTest(){
		String balancePY = "18230,02 pуб.";
		float py = Float.parseFloat(balancePY.replaceAll(" ", "").replaceAll(",", ".").replaceAll("pуб.", ""));
		System.out.println(py);
		String balanceUS = "$50.18 USD";
		float us = Float.parseFloat(balanceUS.replaceAll(" ", "").replace("$", "").replace("USD", "").replaceAll(",", ""));
		System.out.println(us);
		String balanceBR = "R$ 15,99";
		float br = Float.parseFloat(balanceBR.replaceAll(" ", "").replace("R$", "").replaceAll(",", "."));
		System.out.println(br);
		String balanceJP = "¥ 5,509";
		float jp = Float.parseFloat(balanceJP.replaceAll(" ", "").replace("¥", "").replaceAll(",", ""));
		System.out.println(jp);
		String balanceID = "Rp 115 999";
		float id = Float.parseFloat(balanceID.replaceAll(" ", "").replace("Rp", "").replaceAll(",", ""));
		System.out.println(id);
		String balanceMY = "RM43.75";
		float my = Float.parseFloat(balanceMY.replaceAll(" ", "").replace("RM", "").replaceAll(",", ""));
		System.out.println(my);
	}
	
	@Test
	public void KeyPressTest(){
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://store.steampowered.com/login/");
		
		WebElement idField = driver.findElement(By.xpath("//*[@id='input_username']"));
		WebElement pwField = driver.findElement(By.xpath("//*[@id='input_password']"));
		idField.sendKeys("wangyong900930");
		pwField.sendKeys("wangyong900930");
//		WebElement submitButton = driver.findElement(By.xpath("//*[@id='login_btn_signin']/a"));
//		submitButton.click();
		Actions action=new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}
	
	@Test
	public void realChromeTest(){
		System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://store.steampowered.com/login/");
	}
	
	@Test
	public void openNewTabTest(){
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://store.steampowered.com/login/");
		
//		String selectLinkOpeninNewTab = Keys.chord(Keys.SHIFT,Keys.RETURN); 
//		driver.findElement(By.linkText("http://www.baidu.com")).sendKeys(selectLinkOpeninNewTab);
//		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
//		driver.findElement(By.linkText("http://www.baidu.com")).sendKeys(selectLinkOpeninNewTab);
		
//		driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL +"t");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("function createDoc(){var w = window.open(); w.document.open(); w.document.write('<h1>Hello World!</h1>'); w.document.close();}; createDoc();");
		
//		System.out.println(driver.getWindowHandle()+"\n");
		String start = driver.getWindowHandle();
		String end = "";
		Set<String> handles = driver.getWindowHandles();
		for(String handle : handles){
			if(!handle.equals(start)){
				end = handle;
			}
		}

		System.out.println(start+"--"+end);
		driver.switchTo().window(end);
		System.out.println(driver.getTitle());
		driver.get("http://www.baidu.com");
		
		driver.switchTo().window(start);
		driver.get("http://www.163.com");
		System.out.println("sdf");
	}
	


}
