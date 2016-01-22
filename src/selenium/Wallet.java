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
	
	//����WalletXpath�Ĺ�ϣ��
	public Map<String, String> WalletXpath;
	
	AutoSellerUI frame;
	//����WebDriverʵ��
	WebDriver driver = null;
	WebDriverWait wait;
	//������Ϣ
	String error_info = "";
	//���������ַ��������ڼ����ֵǰ��Ĳ�ֵ
	String pre_account;
	String suf_account;
	
	//���屣��Ϊtxt�ĳ����ַ���
	String finalResult = "";
	
	//��¼��־
	SteamLog log;
	String codeUSA;
	
	//����ҳ�棬continuebutton���ɼ�ʱ���Ƿ�Ҫˢ��ҳ��ı���
	private boolean refresh = false;
	
	//�˻�
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
		
		//��ȡSeleniumMethodsʵ��������ʼ��driver��wait
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

	//�����Ƿ�ر���������ڵĺ���
	public void exitBrowAfterRunning(){
		if(frame.exitBrowserAfterRunning){	
			this.driver.quit();
		}
		try {
			Runtime.getRuntime().exec("cmd /c start /B kill_chromedriver.bat");
		}catch(Exception e){};
		return;
	}
	
	//����ǰ���˻����Ĳ�ֵ����
	public int ChargePreSuf(String pre, String suf){
		System.out.println(pre+"--"+suf);
		String pre_num = pre.split(" ")[0].replaceAll(",", ".");
		String suf_num = suf.split(" ")[0].replaceAll(",", ".");
		int charge_num = (int) (Double.valueOf(suf_num)-Double.valueOf(pre_num));
		return charge_num;
	}
	
	//�����Զ���ֵ�ĳ���
	public synchronized void autoStart() throws InterruptedException, JDOMException, IOException{
		this.log = new SteamLog(this.frame);
		this.frame.statusText.setText("����������������������������ĵȴ����벻Ҫ�ظ������������");
		this.frame.statusText.setText("�����������������ڵ�¼�˻������Եȣ�������������");
		String[] loginOrNot = {"false","0"};
		String account = this.default_account;
		String password = this.defaule_password;
		
		//�����Զ���¼����������5��
		for(int i=0;i<5;i++){
			loginOrNot = this.seleniumMethods.login("https://store.steampowered.com/login/?cc=ru", account, password, this.WalletXpath.get("loginIdField"), this.WalletXpath.get("loginPwField"), this.WalletXpath.get("loginButton"));
			if(loginOrNot[0]=="true"){
				break;
			}else if(i==4){
				this.frame.statusText.setText("��������½5�δ��������¼Ԫ��xpath�����ԣ�����");
				exitBrowAfterRunning();
				return;
			}else{
				this.frame.statusText.setText("��������¼�쳣���������ԣ�����");
			}
		}
		
		//ǰ���˻����
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
				// �ж��Ƿ�ɹ�
				if("�ʺ��ѱ����ã�����ϵ�ͷ���".equals(this.error_info)){
					break;
				}
				if(x==5||(!checkOver&&this.refresh==false)){
					//��ֵ�벻��ȷ������xpathȷʵ����������
					//�����ֵ�벻��ȷ���ٻ�ȡ��ǰ����������ֵ
					this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+";���"+pre_account+"\n");
					this.frame.statusText.setText("��������"+code+" ��ֵ�����ȴ���һ����ֵ�룡������");					
					//������־
					log.setMessage("��ֵʧ�ܣ�"+this.error_info+"��ֵ�˻�Ϊ"+this.default_account+"����ֵ��code��"+code+"����ֵǰ�����"+this.pre_account+"����ֵ�����"+this.codeUSA);
					log.insertLog();
					
					//�������continue�Ĵ���Ļ����˳�ѭ��
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
						//ǰ������в�𣬳�ֵ����ȷ���˳�ѭ��
						this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+";��ֵ���"+pre_suf+";���"+suf_account+"\n");
						this.frame.statusText.setText("��������"+code+" ��ֵ�ɹ����ȴ���һ����ֵ�룡������");					
						//������־
						log.setMessage("��ֵ�ɹ�����ֵ�˻�Ϊ"+this.default_account+"����ֵ��code��"+code+"����ֵǰ�����"+pre_account+"����ֵ�����"+this.codeUSA+"����ֵ�������"+suf_account);
						log.insertLog();
						this.refresh = false;
						// ��ֵ ������һ�γ�ֵ
						pre_account = suf_account;
						break;
					}
					else{
						//ǰ������޲�𣬳�ֵ��ʧ�ܣ�����
						this.refresh = false;
						continue;
					}
				}else{
					//���������!checkOver����this.refresh==true����ʱ��Ҫˢ��ҳ�棬������֤��ǰcode
					//��ʱ��Ҫ��ʼ��this.refreshΪfalse
					this.refresh = false;
					continue;
				}
			}
			if("�ʺ��ѱ����ã�����ϵ�ͷ���".equals(this.error_info)){
				//�˺ű���������ѭ��
				log.setMessage("Code��ֵʧ��"+this.error_info+"��֤��Ϊ"+code+"����֤�˻�Ϊ"+accountIDs.get(i));
				log.insertLog();
				break;
			}
		}
		this.frame.statusText.setText("����������ֵ�������������ֵ����˳����򣡣�����");
		exitBrowAfterRunning();
		return;
	}
	
	/*
	 * �µ�Code��֤����
	 * ������Code�ַ�������ֵ����֤
	 */
	public boolean batchCheck(String code, String status) throws InterruptedException{
		
		WebElement codeInput = null;
		WebElement continueButton = null;
		
		try{
		codeInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("codeInputField"))));
		codeInput.clear();
		codeInput.sendKeys(code);
		}catch(Exception e){
			this.error_info = "��������Code������Xpath���������ԣ�������";
			this.frame.statusText.setText(this.error_info);
			this.refresh = true;
			return false;
		}	
	
		try{
			continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("codeContinueButton"))));
			continueButton.click();
		}catch(Exception e){
			this.error_info = "������ť����ʧ�ܣ�����xpath�����ԣ�";
			this.frame.statusText.setText(this.error_info);
			//ˢ��
			this.refresh = true;
			return false;
		}
				
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//�ȴ�������Ĵ��󣬱���ŵĴ���
		try{	
//			WebElement error_link_a = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("errorDisplayA"))));
			// faster
			WebElement error_link_a = driver.findElement(By.xpath(this.WalletXpath.get("errorDisplayA")));
			this.error_info = "�ʺ��ѱ����ã�����ϵ�ͷ���";
			return false;
		}catch(Exception e){
			System.out.println("����ִ��");
		}
		
		//�鿴����������Ϣ
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
			System.out.println("����ִ��");
		}
		
		//û�г���Ļ��鿴��Ӧ�Ļ����Ƿ���ȷ
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
			this.error_info = "���ʲ鿴ʧ�ܣ�����xpath�����ԣ�";
			this.frame.statusText.setText(this.error_info);
			return false;
		}		


		//����ܹ���ȷ��ȡ�����ʣ�����Ӧ��error_infoΪ��Ԫ
		this.error_info = account_usa+"---"+account_rus+"��";
		System.out.println("batchcheck"+this.error_info );
		//�ж�status,��status��'charge'��Ϊ��ֵ����ִ�У������'check'��Ϊ��֤������
		if("check".equals(status)){		
			return true;
		}else{
			//�Զ���ֵ����ִ��
			WebElement nextContinueButton = null;
			try{
				nextContinueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(this.WalletXpath.get("nextContinueButton"))));
			}catch(Exception e){
				System.out.println("waitbutton"+e);
				this.error_info = "������ť����ʧ�ܣ�����xpath�����ԣ�";
				this.frame.statusText.setText("������������ť����ʧ�ܣ�����xpath�����ԣ�����");
				//��Ҫˢ��
				this.refresh = true;
				return false;
			}
			
			//���ܵ�������ͬ����Ҫˢ��
			try{
				nextContinueButton.click();
			}catch(Exception e){
				System.out.println("clickbutton"+e);
				this.error_info = "������ť����ʧ�ܣ�����xpath�����ԣ�";
				this.frame.statusText.setText("������������ť����ʧ�ܣ�����xpath�����ԣ�����");
				//��Ҫˢ��
				this.refresh = true;
				return false;
			}		
			
			//����Ƿ��ֵ�ɹ�
/*			
			WebElement chargeStatus = null;
			try{
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				chargeStatus = driver.findElement(By.id("//*[@id='redeem_wallet_success_button']"));
				System.out.println("redeem sucess"+chargeStatus);
			}catch(Exception e){
				System.out.println("redeem failed"+e);
				this.error_info = "��ֵʧ��";
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
			
			this.error_info = account_usa+" ��ֵ�ɹ���";
			return true;
		}
			
	}
	
	//������֤��ֵ��ĺ���
	public synchronized void batchStart() throws InterruptedException, JDOMException, IOException{
		this.log = new SteamLog(this.frame);
		this.frame.statusText.setText("����������������������������ĵȴ����벻Ҫ�ظ������������");
		
		//���code
		int count = 0;
		int codeSize = codes.length;
		
		for(int i=0;i<accounts.size()&&count<codeSize;i++){
			
			String account = accountIDs.get(i);
			String password = accounts.get(accountIDs.get(i));
			String[] loginOrNot = {"false","0"};
			
			this.frame.statusText.setText("�������������������л��˻������Եȣ�������������");
			this.frame.codeDefaultAccountText.setText(account);
			//�����˻��滻����������5��
			for(int x=0;x<5;x++){
				loginOrNot = this.seleniumMethods.login("https://store.steampowered.com/login/?cc=ru", account, password, this.WalletXpath.get("loginIdField"), this.WalletXpath.get("loginPwField"), this.WalletXpath.get("loginButton"));;
				if(loginOrNot[0]=="true"){
					break;
				}else if(x==1){
					this.frame.statusText.setText("��������½5�δ��������¼Ԫ��xpath�����ԣ�����");
					exitBrowAfterRunning();
					return;
				}else{
					this.frame.statusText.setText("��������¼�쳣���������ԣ�����");
				}
			}
				
			for(int j=0;j<10&&count<codeSize;j++){
				String code = codes[count];			
				driver.get("https://store.steampowered.com/account/redeemwalletcode");
				if(batchCheck(code,"check")){
					//�����ֵ����ȷ, ��ӵ���־
					this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+"\n");
					log.setMessage("Code��֤��"+this.error_info+"��֤��Ϊ"+code+"����֤�˻�Ϊ"+accountIDs.get(i)+"��֤�˻����Ϊ]"+loginOrNot[1]);
					log.insertLog();
					count++;
				}else{
					if("�ʺ��ѱ����ã�����ϵ�ͷ���".equals(this.error_info)){
						//�˺ű���������ѭ������½��һ���˻�
						this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+"\n");
						log.setMessage("Code��֤��"+this.error_info+"��֤��Ϊ"+code+"����֤�˻�Ϊ"+accountIDs.get(i)+"��֤�˻����Ϊ]"+loginOrNot[1]);
						log.insertLog();
						break;
					}else{
						//�����ֵ�벻��ȷ,��ӵ���־
						this.frame.codesStatusTextArea.setText(this.frame.codesStatusTextArea.getText()+code+";"+this.error_info+"\n");
						log.setMessage("Code��֤��"+this.error_info+"��֤��Ϊ"+code+"����֤�˻�Ϊ"+accountIDs.get(i)+"��֤�˻����Ϊ]"+loginOrNot[1]);
						log.insertLog();
						this.frame.statusText.setText("������ֵ��  "+code+" ���󣡣�");
						count++;
					}
					
				}
			}
		}
		this.frame.statusText.setText("�����������������������ֵ����˳����򣡣�����");
		System.out.println("��ֵ��ȫ�����ɹ�");
		exitBrowAfterRunning();
		return;
	}
	
}
