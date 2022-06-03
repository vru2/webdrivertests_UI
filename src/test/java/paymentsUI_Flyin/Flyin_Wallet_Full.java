// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Flyin;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;
import test.java.paymentsUI_Air.PaymentUI_Common;

public class Flyin_Wallet_Full extends PaymentUI_Common{
	public RemoteWebDriver driver;
	Cookie cookie_Flyin_Wallet = new Cookie("ct-auth", "j91FkBMLRhPqpgDfYLUmrI6XaziY2CkwChZ%2Bg3UEqw295TKXv47euKF2UQ88CLDrD3habrO078UoXdzWM34lXZaLbE1jIpkEaANLn%2BHJadfc0Mq4AqwRb%2BdgMpwJwvjcM9bvFs4xqdc7XS%2BMreoH75N0PulbNaWT1V2MlKugfvXHXoFtCapSgXoj%2BAq5Ksn1");

	@Test
	public void FlyIN_Val() throws Exception {
		String PayUrl = getPayUI("AirSA", "FLYIN");
		driver.manage().deleteAllCookies();
		driver.get(PayUrl);
		driver.manage().addCookie(cookie_Flyin_Wallet);
		driver.get(PayUrl);
		elementPresent_log(driver, getObjectPayment("FlyIN_Logo"), "Flyin LOGO", 5);
		elementPresent_log(driver, getObjectPayment("PayUI_Cleartrip_Logo"), "Flyin LOGO", 5);
		driver.get("https://qa2.flyin.com/paymentservice/api/wallet?product=DOMESTIC-AIR&currency=SAR&tripRef=Q210219897402");
		String WalletAPI = getText(driver, By.xpath("//pre"));
		if(!(WalletAPI.contains("walletExists\":true")&&WalletAPI.contains("WALLET_FETCHED")&&WalletAPI.contains("Success"))) {
			Reporter.log("Wallet API"+WalletAPI);
			Assert.assertTrue(false);
		}
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
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
