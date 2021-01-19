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

public class PWA_Validation extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test (priority=1)
	public void PWA_Validate_pay_error() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);

		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 20);
		//textPresent_Log(driver, "Select payment option", 1);
		payUI_Select_PaymentType_PWA(driver, "DEBIT/CREDIT CARDS");
		//safeClick(driver, getObjectPayment("PWA_PaymentPage_SaveCard"));
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Number"),  platform.value("MasterCard_Number"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please enter valid card details");	
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 5);
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Number"),  platform.value("MasterCard_Number"));
		safeType(driver, getObjectPayment("PWA_PaymentPage_CC_Expiry"), platform.value("MasterCard_EXP_PWA"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please enter valid card details");
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 5);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "NET BANKING");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn2"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please select your bank");	
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 5);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "WALLETS");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please select any wallet");	
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), 5);
		safeClickList(driver, getObjectPayment("PWA_PaymentPage_Pay_Tabs"), "UPI");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		payUI_Error_Validation_PWA(driver, getObjectPayment("PWA_Error_ValidCard"), getObjectPayment("PWA_Error_PopUp_Screen"), "Please enter a valid UPI ID");
		
	}

	@Test (priority=2)
	public void PWA_Validate_PriceValidation() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_Icon"));
		elementPresent(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));
		textPresent_Log(driver, "Fare breakUp", 5);
		//textPresent_Log(driver, "Discounts", 5);
		textPresent_Log(driver, "Convenience Fee", 1);	
		textPresent_Log(driver, "Flexifly", 1);	
		textPresent_Log(driver, "Travel insurance", 1);	
		textPresent_Log(driver, "Total", 5);
		textPresent_Log(driver, "You pay", 5);
		safeClick(driver, getObjectPayment("PWA_PaymentPage_FareBreakup_closeIcon"));		
		if(textPresent(driver, "Convenience Fee", 1)) {
			Reporter.log("Close button is clicked");
			Assert.assertTrue(false);
		}
		
	}
	
	@Test (priority=3)
	public void PWA_Validate_ItineraryValidation() throws Exception {
		refreshPage(driver);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Itineray"),10);
		String ItineraryDetails_TripText = getText(driver, getObjectPayment("PWA_PaymentPage_Itineray_YourTrip_text"));
		Assert.assertEquals(ItineraryDetails_TripText, "Your trip details");
		
		String ItineraryDetails_DateText = getText(driver, getObjectPayment("PWA_PaymentPage_Itineray_TravelDate_text"));
		Assert.assertEquals(ItineraryDetails_DateText, "24 Oct - 24 Oct | John Miller+4");
		
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
		textPresent_Log(driver, "Mohit Verma", 1);
		String FlightDetails = getText(driver, getObjectPayment("PWA_PaymentPage_Itineray_FlightDetails"));
		if(!(FlightDetails.contains("BLR")&&FlightDetails.contains("Non stop")&&FlightDetails.contains("BOM"))) {
			Reporter.log("Flight details text is not displayed");
			Assert.assertTrue(false);
		}		
		elementPresent_log(driver, By.xpath("//div[2]/div/img"), "Flight Logo", 1);
		safeClick(driver, By.cssSelector("svg.c-pointer"));
		if(elementVisible(driver, By.xpath("//div[2]/div/img"),1)) {
			Assert.assertTrue(false);
		}
		//elementPresent_log(driver, By.xpath("//div[2]/div/img"), "Flight Logo", 1);
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
