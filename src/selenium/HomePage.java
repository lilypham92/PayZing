package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class HomePage {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_HomePageShow() {
		
		Boolean Logo = driver.findElement(By.xpath("//a[@class='logo']")).isDisplayed();
		Assert.assertTrue(Logo);
		}
		
	@Test
	public void TC_02_Search() throws InterruptedException {
		
		 driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Samsung");
		 
		 driver.findElement(By.xpath("//input[@id='search']")).sendKeys(Keys.RETURN);
		 Thread.sleep(500);
		 String result= driver.findElement(By.xpath("//div[@class='category-products']/div/div/div/p[@class='amount amount--no-pages']")).getText();		 
		 System.out.println("So ket qua tim thay"+result.replace(" Item(s)", ""));
		}

	@AfterClass
	public void afterClass() {
	driver.close();
	}
	

}
