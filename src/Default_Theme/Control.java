package Default_Theme;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
//import com.sun.org.apache.bcel.internal.generic.Select;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Control {
	WebDriver driver;
	public WebDriverWait wait;
	xpath x;

	String serialCode;
	String passCode;

	String userName = "giinboo1";
	String passWord = "Aa123456!";

	public Control(WebDriver driver) {
		this.driver = driver;

		this.x = new xpath();
	}
	public void find(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
		}catch(Exception Ex){
			
		}
	}
	public void goToZingCardSite() throws InterruptedException {
		// to open new tab in existing window
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		// Open URL In 2nd tab.
		driver.get("https://sandbox.mresource.pay.zing.vn/card/zing");
		this.click(x.runBT);
		Thread.sleep(500);
		serialCode = this.getText(x.seriCode);
		passCode = this.getText(x.passCode);

		System.out.println("seri  " + serialCode);
		System.out.println("Pass  " + passCode);
		// Call switchToTab() method to switch to 1st tab
		switchToTab();

		// Call switchToTab() method to switch to 2nd tab.
//		  switchToTab();

	}

	public void switchToTab() {
		// Switching between tabs using CTRL + tab keys.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		// Switch to current selected tab's content.
		driver.switchTo().defaultContent();
	}

	public void click(String xpath) {
		try {
		driver.findElement(By.xpath(xpath)).click();
	} catch(Exception Ex){
		
	}
	}
		
//	}

	public void clickID(String id) {
		try {
		driver.findElement(By.id(id));
	}catch(Exception Ex){
		System.out.print("click not found");
	}
	}
	public void sendKey(String xpath, String key) {
		try {
		driver.findElement(By.xpath(xpath)).clear();
		driver.findElement(By.xpath(xpath)).sendKeys(key);
	}catch(Exception Ex){
		System.out.print("sendkey not found");
	}
	}

	public String getText(String xpath) {
		try {
		String text = driver.findElement(By.xpath(xpath)).getText();
		return text;
		} 
		catch (Exception Ex) {
			System.out.print("get text not found");
		return null;
		}
		
	}

	public void clear(String xpath) {
		try {
		driver.findElement(By.xpath(xpath)).clear();
		}catch(Exception Ex) {
			System.out.print("clear not found");
		}
	}

	public void isDisplay(String xpath) {
		try {
		driver.findElement(By.xpath(xpath)).isDisplayed();
		}catch(Exception Ex) {
			System.out.print("isdisplay not found");
		}
	}
	public boolean isElementPresent(String xpath) {
		  try {
		    driver.findElement(By.xpath(xpath));
		    return true;
		  }
		catch (org.openqa.selenium.NoSuchElementException e) {
		    return false;
		  }
		}
	public void clickRandomCombobox(String xpath) {
		List<WebElement> selects = driver.findElements(By.xpath(xpath));
		Random rand = new Random();
		System.out.println("click bank");
		int list = rand.nextInt(selects.size());
		selects.get(list).click();
		System.out.println("click bank 2");
	}
	public void clickBank(String bankName) {
		try {
		driver.findElement(By.xpath(x.bankStart+bankName+x.bankEnd)).click();
		}catch(Exception Ex) {
			System.out.print("click Bank not found");
		}

	}
	public void enterAmountATM(String bankName,String amount) throws InterruptedException {
		Thread.sleep(500);
		click(x.pack);
		Thread.sleep(500);
		click(x.atmBT);
		clickBank(bankName);	
		sendKey(x.banktxt, amount);
		click(x.confirmBankBT);
		
	}
	public void wrapWaitClickXpath(String xpath) {
	try{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	} catch(Exception Ex) {
		System.out.print(xpath + "wrapWaitClickXpath not found");
	}
	}
	public String getValue(String xpath) {
		try {
		String text = driver.findElement(By.xpath(xpath)).getAttribute("value");
		return text;
		} 
		catch (Exception Ex) {
			System.out.print("get text not found");
		return null;
		}
		
	}
	public void selectSV(String svName) {
		try {
		driver.findElement(By.xpath(x.StartSV+svName+x.endSV)).click();	
		}
		catch(Exception Ex) {
			System.out.print("Select SV not found");
			
		}
	}
	public String selectValue ( int i) {
//		WebElement dropdown = driver.findElement(By.xpath(xpath));
//		Select select = new Select(dropdown);
		String price = driver.findElement(By.xpath("//option["+i+"]")).getAttribute("value");
		
		return price;
	}
	public boolean checkBTActive(String xpath) {
		
		 if("true".equals(driver.findElement(By.xpath(xpath)).getAttribute("disabled"))) {
		   // disabled
			 System.out.println("Button doesn'tworks");
			 return false;
		 } else {
		   // enabled
			 System.out.println("Button works");
			 return true;
		 }
	}
}
