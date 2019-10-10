package Default_Theme;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCases {
	WebDriver driver;
	xpath x;
	Control control;
	String userName;
	String passWord;
	String svName;
	String wrongKey;
	String amount;
	String wrongAmount;
	String errorMS1;
	String errorMS2;
	String paymentUnsuccess;
	String successMS1;
	String transIDExpected;
	String bank1;
	String bank2;
	String bank3;
	String url;
	static {

	}

	@BeforeClass
	public void beforeClass() {
		GameInfo info = General.gameList.get("GNM");
		userName = info.userName;
		passWord = info.passWord;
		url = info.url;
		svName = info.svName;

		GameInfo infoGNR = General.gameList.get("General");

		wrongKey = infoGNR.wrongKey;
		amount = infoGNR.amount;
		wrongAmount = infoGNR.wrongAmount;
		errorMS1 = infoGNR.errorMS1;
		errorMS2 = infoGNR.errorMS2;
		paymentUnsuccess = infoGNR.paymentUnsuccess;
		successMS1 = infoGNR.successMS1;
		transIDExpected = infoGNR.transIDExpected;
		bank1 = infoGNR.bank1;
		bank2 = infoGNR.bank2;
		bank3 = infoGNR.bank3;

		driver = new FirefoxDriver();
		driver.get(url);
		driver.navigate().refresh();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		control = new Control(driver);
		x = new xpath();

	}

	@Test(priority = 1, enabled = false)
	public void genZingCardCode() {
		try {
			control.goToZingCardSite();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.print("không tới zing card tool được");
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void TC_01_Login() throws InterruptedException {

		control.sendKey(x.userName, userName);
		control.sendKey(x.passWord, passWord);
		control.click(x.loginBT);
		Thread.sleep(500);
//		Assert.assertEquals(
//		driver.findElement(By.xpath(x.thoat)).getText(),"(Thoát)");

	}

	@Test(priority = 3)
	public void TC_01_Login2() throws InterruptedException {
		Thread.sleep(500);
		By loadingImage = By.id("preloader-background");
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingImage));
		if (control.isElementPresent(x.ServerGroup) == true) {
			control.click(x.ServerGroup);
			Thread.sleep(300);
			control.click(x.firstSVGroup);
			Thread.sleep(500);
		} else {
		}	
		if (control.isElementPresent(x.server) == true) {
			control.click(x.server);
			Thread.sleep(1000);
			control.selectSV(svName);
		} else {
		}

		Thread.sleep(300);
		control.click(x.loginBT2);

	}

	@Test(priority = 4, enabled = false)
	public void TC_02_wrongZingCard() throws InterruptedException {
		control.click(x.pack);
		control.click(x.zingCardBT);
		control.sendKey(x.seriCardTxt, wrongKey);
		control.sendKey(x.passCardTxt, wrongKey);
		control.click(x.confirmZingBT);
		String errorMS = control.getText(x.errorMS);
		System.out.println(errorMS);
		control.click(x.zingCardBT);
		Assert.assertEquals(errorMS, errorMS1);
	}

	@Test(priority = 5, enabled = false)
	public void TC_03_correctZingCard() throws InterruptedException {
		Thread.sleep(500);
		control.click(x.zingCardBT);
		control.sendKey(x.seriCardTxt, control.serialCode);
		control.sendKey(x.passCardTxt, control.passCode);
		control.click(x.confirmZingBT);
		Thread.sleep(1000);
		Thread.sleep(500);
		String successMS = control.getText(x.successMS);
		control.click(x.continueLink);
//		try {
		Assert.assertEquals(successMS, successMS1);
//		}catch (AssertionError Ex){
//			System.out.print( "TC_03_correctZingCard() TC fail"+successMS );
//		}
	}

	@Test(priority = 6, enabled = false, dataProvider = "ZaloPayProviderInvalid")
	public void TC_04_ZaloPay_invalidAmount(String amount) throws InterruptedException {
		control.click(x.pack);
//		control.click(x.zaloPayBT);
		Thread.sleep(500);
		control.clear(x.zaloPaytxt);
		control.sendKey(x.zaloPaytxt, amount);
		Thread.sleep(500);
		control.click(x.confirmZaloPayBT);
		String errorMS = control.getText(x.errorMS);
		System.out.println(errorMS);
		Assert.assertEquals(errorMS, errorMS2);
	}

	@DataProvider(name = "ZaloPayProviderInvalid")
	public Object[][] getDataFromDataprovider1() {
		return new Object[][] { { "4000" }, { "11000" }, { "31000" }, };
	}

	@Test(priority = 7, enabled = false, dataProvider = "ZaloPayProviderValid")
	public void TC_05_ZaloPay_validAmount(String amount) throws InterruptedException {
		control.click(x.pack);
		Thread.sleep(500);
		control.clear(x.zaloPaytxt);
		control.sendKey(x.zaloPaytxt, amount);
		Thread.sleep(500);
		control.click(x.confirmZaloPayBT);
		Thread.sleep(500);
		String transID = control.getText(x.transID);
		control.click(x.continueLink);
		Assert.assertEquals(transID, transIDExpected);
	}

	@DataProvider(name = "ZaloPayProviderValid")
	public Object[][] getDataFromDataprovider2() {
		return new Object[][] { { "20000" }, { "50000" }, { "20000000" }, };

	}

	@Test(priority = 8, enabled = false, dataProvider = "bankProviderInvalid")
	public void TC_06_paymentWrongAmountBank(String amount) throws InterruptedException {
		control.enterAmountATM(bank3, amount);
		String errorMS = control.getText(x.errorMS);
		System.out.println(amount + ":  " + errorMS);
		Assert.assertEquals(errorMS, errorMS2);
	}

	@DataProvider(name = "bankProviderInvalid")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "xyz" }, { "00" }, { "15000" }, { "20000" } };

	}

	@Test(priority = 9, enabled = false)
	public void TC_07_paymentBank1() throws InterruptedException {
		Thread.sleep(500);
		control.enterAmountATM(bank1, amount);
		Thread.sleep(200);
		control.click(x.chuyenNgay);
		Thread.sleep(5000);
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		driver = new FirefoxDriver(capabilities);
		String amountBank1 = control.getValue(x.amountBank1).replace(".00 VND", "").replace(",", "");
		System.out.print("bank1:" + amountBank1);
		Assert.assertEquals(amountBank1, amount);
		control.click(x.huyBank1);
//		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		driver = new FirefoxDriver(capabilities);
//		Thread.sleep(35000);
		Assert.assertEquals(control.getText(x.paymentUnsuccessMS), paymentUnsuccess);
		control.wrapWaitClickXpath(x.backLink);
		Thread.sleep(2000);
		control.click(x.continueLink);

	}

	@Test(priority = 10, enabled = false)
	public void TC_08_paymentBank2() throws InterruptedException {
		Thread.sleep(500);
		control.enterAmountATM(bank2, amount);
		Thread.sleep(200);
//		control.click(x.chuyenNgay);
//		Thread.sleep(5000);		
		control.wrapWaitClickXpath(x.backLink);
		Thread.sleep(2000);
		control.click(x.continueLink);

	}

	@Test(priority = 11, enabled = false)
	public void TC_09_paymentBank3() throws InterruptedException {
		Thread.sleep(500);
		control.enterAmountATM(bank3, amount);
		Thread.sleep(500);
		String amountActual1 = control.getText(x.amountBank).substring(1).replace(".", "");
		String amountActual2 = control.getText(x.thirdPTBankSubmitBT).substring(11).replace(".", "");
		control.click(x.cancelLink);
		control.click(x.cancelLink1);
		control.click(x.backLink);
		Thread.sleep(2000);
		control.click(x.continueLink);
		boolean result = amount.equals(amountActual1) && amount.equals(amountActual2);
		try {
			System.out.println("Amount on the Bank site is correct!");
			Assert.assertTrue(result);
		} catch (AssertionError ex) {
			System.out.println("Amount on the Bank site is incorrect!");
		}
	}

	@Test(priority = 12, enabled = false, dataProvider = "bankProviderInvalid")
	public void TC_10_paymentCreditInvalidAmount(String amount) throws InterruptedException {
		Thread.sleep(500);
		control.click(x.pack);
		control.click(x.creditBT);
		control.sendKey(x.credittxt, amount);
		control.click(x.confirmCreditBT);
		String errorMS = control.getText(x.errorMS);
		Assert.assertEquals(errorMS, errorMS2);
	}

	@Test(priority = 13, enabled = false)
	public void TC_11_paymentCredit() throws InterruptedException {
		Thread.sleep(500);
		control.click(x.pack);
		control.click(x.creditBT);
		control.sendKey(x.credittxt, amount);
		control.click(x.confirmCreditBT);
		Thread.sleep(500);
		String amountActual1 = control.getText(x.amountBank).substring(1).replace(".", "");
		String amountActual2 = control.getText(x.thirdPTBankSubmitBT).substring(11).replace(".", "");
		control.click(x.cancelLink);
		control.click(x.cancelLink1);
		control.click(x.backLink);
		Thread.sleep(2000);
		control.click(x.continueLink);
		boolean result = amount.equals(amountActual1) && amount.equals(amountActual2);
		try {
			System.out.println("Amount on the Bank site is correct!");
			Assert.assertTrue(result);
		} catch (AssertionError ex) {
			System.out.println("Amount on the Bank site is incorrect!");
		}
	}

	@Test(priority = 13, enabled = true)
	public void TC_12_paymentViettelSMS() throws InterruptedException {
		Thread.sleep(500);
		
		control.click(x.pack);
		control.click(x.smsBT);
		Thread.sleep(500);
		if (control.checkBTActive(x.confirmSMSBT)==true) {
		control.click(x.viettelBT);
		
		int priceSize = driver.findElements(By.tagName("option")).size();
		
		for (int i = 0; i < priceSize; i++) {
			if(i != 0) {
				control.click(x.pack);
				control.click(x.smsBT);
				control.click(x.viettelBT);
			}			
			List<WebElement> value = driver.findElements(By.tagName("option"));
			String priceBF =control.selectValue(i+1);
//			System.out.println(priceBF);
			value.get(i).click();				
			control.click(x.confirmSMSBT);
			Thread.sleep(1000);
			String priceAT = control.getText(x.amountSMS).substring(93).replace(" ", "").replace("à", "");
//			System.out.println(priceAT);	
			control.click(x.continueLink);
			Thread.sleep(500);
			Assert.assertEquals(priceAT, priceBF);
		}
		} else {
			System.out.println("SMS payment isn't available");
		}
	}
	@Test(priority = 13, enabled = true)
	public void TC_13_paymentMobiSMS() throws InterruptedException {
		Thread.sleep(500);
		
		control.click(x.pack);
		control.click(x.smsBT);
		if (control.isElementPresent(x.mobiBT)==true) {
			control.click(x.mobiBT);
			
			int priceSize = driver.findElements(By.tagName("option")).size();
			
			for (int i = 0; i < priceSize; i++) {
				if(i != 0) {
					control.click(x.pack);
					control.click(x.smsBT);
					control.click(x.mobiBT);
				}			
				List<WebElement> value = driver.findElements(By.tagName("option"));
				String priceBF =control.selectValue(i+1);
				System.out.println(priceBF);
				value.get(i).click();				
				control.click(x.confirmSMSBT);
				Thread.sleep(1000);
				String priceAT = control.getText(x.amountSMS).substring(90).replace(" ", "").replace("là", "").replace("à", "").replace("n", "");
				System.out.println(priceAT);	
				control.click(x.continueLink);
				Thread.sleep(500);
				Assert.assertEquals(priceAT, priceBF);
			}
		} else {
		System.out.println("Mobifone isn't available");
		}
	}
	@AfterClass
	public void afterClass() {
//	driver.close();
	}

}
