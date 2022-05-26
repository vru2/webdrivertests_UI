// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsUI_Air;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_StoredCard extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_PhonePeI() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		System.out.println(PayUrl);
		refreshPage(driver);
		if(elementPresent_Time(driver, By.cssSelector("div.pwa-save-card > div.pwa-radio-toggle.pwa-radio-toggle__on > label.pwa-radio-toggle--btn"), 2)){
			Reporter.log("Save Card option is displayed for unsigned user");
			Assert.assertTrue(false);
		}
		driver.manage().addCookie(cookie_Parl_Wallet);
		refreshPage(driver);
		elementVisible(driver, By.cssSelector("//li/div/div/p"), 10);
		//safeClick(driver, By.cssSelector("//li/div/div/p"));
		WebElement ele1 = driver.findElement(By.xpath("//div[6]/li/div[2]/div/div[2]"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",ele1);
		Thread.sleep(1000);
		elementPresent_log(driver,By.cssSelector("#amex > #Shape"),"American Express Logo",2);
		elementPresent_log(driver,By.cssSelector("li.Datalist__item.RadioList__item.bb.h-60.flex-row-reverse.flex.flex-top.flex-between.py-2.px-2.is-active > div > div.pt-2 > svg.mr-2"),"Check Box",2);
		safeClick(driver,By.xpath("//div[6]/li/div[2]/div/div[2]"));
		elementPresent_log(driver,By.id("CVV_4143"),"Amex CVV Box",2);
		safeClick(driver,By.id("CVV_4143"));
		safeType(driver,By.id("CVV_4143"),"1234");
		safeClick(driver,By.xpath("//button/p"));		
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getMobileDriver(driver);
	}

	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	  
	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
	
}
