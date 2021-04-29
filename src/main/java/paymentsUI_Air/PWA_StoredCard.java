// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.By;
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
		refreshPage(driver);
		if(elementPresent_Time(driver, By.cssSelector("div.pwa-save-card > div.pwa-radio-toggle.pwa-radio-toggle__on > label.pwa-radio-toggle--btn"), 2)){
			Reporter.log("Save Card option is displayed for unsigned user");
			Assert.assertTrue(false);
		}
		driver.manage().addCookie(cookie_Parl_Wallet);
		refreshPage(driver);
		elementVisible(driver, By.cssSelector("rect"), 10);
		safeClick(driver, By.cssSelector("rect"));
		String saveCard = getText(driver, By.xpath("//div[3]/div/p"));
		String saveCVV = getText(driver, By.xpath("//div[3]/div/p[2]"));
		Assert.assertEquals(saveCard, "Save card for faster bookings");	
		Assert.assertEquals(saveCVV, "We never save your CVV");
		safeClick(driver, By.cssSelector("div.pwa-save-card > div.pwa-radio-toggle.pwa-radio-toggle__on > label.pwa-radio-toggle--btn"));
		Thread.sleep(2000);
		if(elementVisible(driver, By.cssSelector("div.pwa-save-card > div.pwa-radio-toggle.pwa-radio-toggle__on > label.pwa-radio-toggle--btn"),2)) {
			Reporter.log("Toggle button is not clicked");
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//div[2]/label"));
		if(elementNotVisible(driver, By.cssSelector("div.pwa-save-card > div.pwa-radio-toggle.pwa-radio-toggle__on > label.pwa-radio-toggle--btn"),2)) {
			Reporter.log("Toggle button is not clicked");
			Assert.assertTrue(false);
		}
		elementPresent_log(driver, By.cssSelector("div.pwa-save-card > div.pwa-radio-toggle.pwa-radio-toggle__on > label.pwa-radio-toggle--btn"), "save card button", 2);
		safeClick(driver, By.cssSelector("g > path"));
		String savedCardNumber = getText(driver, By.cssSelector("p.c-black.lh-title.fs-4.pb-1"));
		String savedCardName = getText(driver, By.cssSelector("span.c-gray.lh-title.fs-2.pt-1"));
		Assert.assertEquals(savedCardNumber, "5123 45XX XXXX 2346");	
		Assert.assertEquals(savedCardName, "credit");
		elementPresent_log(driver, By.cssSelector("div.cardFadeIn > img"), "card image", 1);
		elementPresent_log(driver, By.cssSelector("g > path"), "Radio button", 1);
		if(!driver.findElement(By.cssSelector("g > path")).isEnabled()) {
			Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//li[2]/div[2]"));
		Thread.sleep(2000);
		if(!driver.findElement(By.cssSelector("g > path")).isEnabled()) {
			Assert.assertTrue(false);
		}
		safeClick(driver, By.cssSelector("g > path"));

		Thread.sleep(2000);
		if(!driver.findElement(By.cssSelector("g > path")).isEnabled()) {
			Assert.assertTrue(false);
		}
		safeType(driver, By.name("cvv"), "123");
		
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
