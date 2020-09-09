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

public class Validate_Text_Messages2 extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test(priority=1)
	public void Validate_Text_CC() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType(driver, "DC");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));	
		textPresent_Log(driver, "Enter your debit card details", 2);
		textPresent_Log(driver, "Please enter a valid card number", 1);		
		textPresent_Log(driver, "Please enter a valid expiry month", 1);		
		textPresent_Log(driver, "Please enter a valid expiry year", 1);		
		textPresent_Log(driver, "Please enter a valid cvv", 1);
		textPresent_Log(driver, "Includes a convenience fee of", 1);
		payUI_Error_Validation(driver, getObjectPayment("PaymentPage_Error_Banner"), "Enter valid debits card number");
		
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
