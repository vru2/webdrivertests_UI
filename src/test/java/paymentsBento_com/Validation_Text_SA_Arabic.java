// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsBento_com;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Validation_Text_SA_Arabic extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation_Text() throws Exception {
		get_Bento_Url(driver, "AirAEAR", "AE");

		elementPresent_log(driver, getObjectPayment("Bento_Pay_Button"), "MakePayment Button", 5);
		textNotPresent_Log(driver, "Pay to complete your booking", 1);
		textNotPresent_Log(driver, "Enter card details", 1);
		textNotPresent_Log(driver, "Card number", 1);
		textNotPresent_Log(driver, "Expiry date", 1);
		textNotPresent_Log(driver, "Card holder Name", 1);
		//textNotPresent_Log(driver, "CVV", 1);
		textNotPresent_Log(driver, "Name as on card", 1);
		//textNotPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare, the", 1);
		textNotPresent_Log(driver, "Total, inclusive of all taxes", 1);
		textNotPresent_Log(driver, "Enter card number", 1);
		
		//paypal
		safeClick(driver, By.xpath("//nav/div[3]"));
		textNotPresent_Log(driver, "Pay to complete your booking", 1);
		textNotPresent_Log(driver, "Pay using PayPal", 1);

		//ADCB
		safeClick(driver, By.xpath("//div[5]/p"));
		textNotPresent_Log(driver, "Pay to complete your booking", 1);
		textNotPresent_Log(driver, "Enter ADCB card details", 1);
		textNotPresent_Log(driver, "ADCB card number", 1);
		textNotPresent_Log(driver, "Expiry date", 1);
		textNotPresent_Log(driver, "Card holder Name", 1);
		//textNotPresent_Log(driver, "CVV", 1);
		

		textNotPresent_Log(driver, "Booking summary", 1);
		textNotPresent_Log(driver, "You pay", 1);
		textNotPresent_Log(driver, "Traveller", 1);
		textNotPresent_Log(driver, "Debit Card", 1);
		textNotPresent_Log(driver, "Credit Card", 1);
		textNotPresent_Log(driver, "Net Banking", 1);
		textNotPresent_Log(driver, "Wallet", 1);
		
			
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