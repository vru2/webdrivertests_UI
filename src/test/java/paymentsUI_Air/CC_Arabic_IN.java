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

public class CC_Arabic_IN extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void ArabicIN() throws Exception {
		String PayUrl = getPayUI("AirAEAR", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		String Arabic= getText(driver, By.cssSelector("p.payment-container-header"));
		
		if(textPresent(driver, "Enter your credit card details.", 2)) {
			Reporter.log("Enter your credit card details. : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Credit card no.", 2)) {
			Reporter.log("Credit card no. : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Expiry Date", 2)) {
			Reporter.log("Expiry Date : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Card Holder", 2)) {
			Reporter.log("Card Holder : text is displayed in English");
			Assert.assertTrue(false);
		}if(textPresent(driver, "CVV", 2)) {
			Reporter.log("CVV : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Flight Itinerary", 2)) {
			Reporter.log("Flight Itinerary : text is displayed in English");
			Assert.assertTrue(false);
		}if(textPresent(driver, "You pay", 2)) {
			Reporter.log("You pay : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Travellers", 2)) {
			Reporter.log("Travellers : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Debit Card", 2)) {
			Reporter.log("Debit Card : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Credit Card", 2)) {
			Reporter.log("Credit Card : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Net Banking", 2)) {
			Reporter.log("Net Banking : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Wallet", 2)) {
			Reporter.log("Wallet : text is displayed in English");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "UPI", 2)) {
			Reporter.log("UPI : text is displayed in English");
			Assert.assertTrue(false);
		}
		
		
		
		
		//p.c-red.fs-1.col-offset-6.pl-1   (Credit Card Error)
		
		/*payUI_Select_PaymentType(driver, "CC");
		payUI_Enter_PaymentDetails(driver, "CC", "MASTER");
		payUI_Mock_ConfirmationPage(driver, PayUrl);*/
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