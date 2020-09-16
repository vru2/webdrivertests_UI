// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class StoredCard extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void Stored_Card() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		driver.manage().addCookie(cookie_Parl_Wallet);
		refreshPage(driver);
		String storedCard = getText(driver, By.xpath("//li/p"));
		if(!storedCard.equals("Stored card")) {
			Reporter.log("Stored Card tab text is not displayed");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Pay with your stored cards", 20);
		textPresent_Log(driver, "Enter CVV here", 1);
		textPresent_Log(driver, "You card details are stored as part of your Express Checkout profile.", 1);
		textPresent_Log(driver, "Continue with payment by simply entering CVV.", 1);		
		textPresent_Log(driver, "Includes a convenience fee of", 1);
		elementAssert(driver, getObjectPayment("PayUI_StoredCard_Image"), 1);
		//elementAssert(driver, getObjectPayment("PayUI_StoredCard_Complete_CSS"), 1);
		elementAssert(driver, getObjectPayment("PayUI_StoredCard_Number"), 1);
		elementAssert(driver, getObjectPayment("PayUI_StoredCard_Name"), 1);
		String StoredCard_Number= getText(driver, getObjectPayment("PayUI_StoredCard_Number"));
		String StoredCard_Name= getText(driver, getObjectPayment("PayUI_StoredCard_Name"));
		String Profile_EmailID= getText(driver, getObjectPayment("PayUI_Profile_EmailID"));		
		if(!StoredCard_Number.contains("340000XXXXXX009")) {
			Reporter.log("Stored card number is : "+StoredCard_Number);
			Assert.assertTrue(false);
		}if(!StoredCard_Name.contains("Amex")) {
			Reporter.log("Stored card name is : "+StoredCard_Name);
			Assert.assertTrue(false);
		}if(!Profile_EmailID.contains("ct_wallet_partial@cleartrip.com")) {
			Reporter.log("Profile_EmailID is : "+Profile_EmailID);
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		textPresent_Log(driver, "Please enter a valid cvv", 5);
		safeType(driver, getObjectPayment("PayUI_StoredCard_CVV"), "123");
		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Make Payment button is Clicked");
		payUI_BankPage(driver, "MASTER");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
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
