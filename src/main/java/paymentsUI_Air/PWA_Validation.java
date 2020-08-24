// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.RefreshPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PWA_Validation extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test (priority=1)
	public void PWA_Validate_pay_error() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType_PWA(driver, "DEBIT/CREDIT CARDS");
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Number"),  platform.value("MasterCard_Number"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please enter valid card details");	
		refreshPage(driver);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Number"),  platform.value("MasterCard_Number"));
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Expiry"), platform.value("MasterCard_EXP_PWA"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please enter valid card details");
		refreshPage(driver);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "NET BANKING");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn2"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please select your bank");	
		refreshPage(driver);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "WALLETS");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please select any wallet");	
		refreshPage(driver);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "UPI");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please select any UPI payment method");
		safeClick(driver, getObjectPayment("PWA_PaymentPage_SaveCard"));
	}

	@Test (priority=2)
	public void PWA_Validate_PriceValidation() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		textPresent_Log(driver, "Fare BreakUp", 5);
		textPresent_Log(driver, "Discounts", 5);
		textPresent_Log(driver, "Total", 5);
		textPresent_Log(driver, "You pay", 5);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));		
	}
	
	@Test (priority=3)
	public void PWA_Validate_ItineraryValidation() throws Exception {
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Itineray"),10);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_Itineray"));
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Itineray_Header"),10);
		String itineraryText=getText(driver, getObjectPayment("PWA_PaymentPage_Itineray_Header"));
		if(!itineraryText.contains("Trip summary")) {
			Reporter.log("Trip summary text is not displayed");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "FLIGHT ITINERARY", 1);
		textPresent_Log(driver, "TRAVELLERS", 1);
		textPresent_Log(driver, "John Miller", 1);
		String FlightDetails = getText(driver, getObjectPayment("PWA_PaymentPage_Itineray_FlightDetails"));
		if(!(FlightDetails.contains("1 stop")&&FlightDetails.contains("Non stop")&&FlightDetails.contains("BOM"))) {
			Reporter.log("Flight details text is not displayed");
			Assert.assertTrue(false);
		}		
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
