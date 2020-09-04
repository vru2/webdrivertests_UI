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

public class CC_Arabic_AE extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void Arabic_AE() throws Exception {
		String PayUrl = getPayUI("AirAEAR", "AE");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		//String Arabic= getText(driver, By.cssSelector("p.payment-container-header"));
		elementPresent_log(driver, getObjectPayment("PayUI_Make_Payment_Btn"), "MakePayment Button", 5);
		
		if(textPresent(driver, "Enter your credit card details", 1)) {
			Reporter.log("Enter your credit card details. : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Credit card no", 1)) {
			Reporter.log("Credit card no. : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Expiry Date", 1)) {
			Reporter.log("Expiry Date : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Card Holder", 1)) {
			Reporter.log("Card Holder : text is displayed in English");
			Assert.assertTrue(false);
		}if(textPresent(driver, "CVV", 1)) {
			Reporter.log("CVV : text is displayed in English");
			//Assert.assertTrue(false);
		}
		if(textPresent(driver, "Flight Itinerary", 1)) {
			Reporter.log("Flight Itinerary : text is displayed in English");
			Assert.assertTrue(false);
		}if(textPresent(driver, "You pay", 1)) {
			Reporter.log("You pay : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Travellers", 1)) {
			Reporter.log("Travellers : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Debit Card", 1)) {
			Reporter.log("Debit Card : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Credit Card", 1)) {
			Reporter.log("Credit Card : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Net Banking", 1)) {
			Reporter.log("Net Banking : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Wallet", 1)) {
			Reporter.log("Wallet : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "UPI", 1)) {
			Reporter.log("UPI : text is displayed in English");
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
