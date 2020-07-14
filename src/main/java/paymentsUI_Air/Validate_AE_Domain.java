// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import groovy.lang.ReadOnlyPropertyException;
import io.restassured.response.Response;

public class Validate_AE_Domain extends PaymentUI_Common{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	
	@Test(priority=1)
	public void Validate_Text_CC() throws Exception {
		String PayUrl = getPayUI("AirAE", "AE");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType(driver, "CC");
		String totalPay_LeftColumn=getText(driver, getObjectPayment("PayUI_Total_Pay_Value_RightColumn"));
		String totalPay=getText(driver, getObjectPayment("PayUI_Total_Pay_Value"));
	/*	if(!totalPay_LeftColumn.contains("AED")) {
			Reporter.log("TotalPay Left Column doesnt contain AED  "+totalPay_LeftColumn);
			Assert.assertTrue(false);
		}
		*/
		if(!totalPay.contains("AED")) {
			Reporter.log("TotalPay doesnt contain AED"+totalPay);
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));	
		textPresent_Log(driver, "Enter your credit card details", 5);	
		textPresent_Log(driver, "Please enter a valid card number", 1);		
		textPresent_Log(driver, "Please enter a valid expiry month", 1);		
		textPresent_Log(driver, "Please enter a valid expiry year", 1);		
		textPresent_Log(driver, "Please enter a valid cvv", 1);				
		textPresent_Log(driver, "Save this card and make single-click payments", 1);
		textPresentInElement(driver, getObjectPayment("PayUI_Convinence_Fee"), "Includes a convenience fee of ₹ 200.00", 1);
		textPresent_Log(driver, "Includes a convenience fee of", 5);
	}

	@Test(priority=2)
	public void Validate_Text_DC() throws Exception {
		payUI_Select_PaymentType(driver, "DC");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));	
		textPresent_Log(driver, "Enter your debit card details", 5);
		textPresent_Log(driver, "Please enter a valid card number", 1);		
		textPresent_Log(driver, "Please enter a valid expiry month", 1);		
		textPresent_Log(driver, "Please enter a valid expiry year", 1);		
		textPresent_Log(driver, "Please enter a valid cvv", 1);
		textPresentInElement(driver, getObjectPayment("PayUI_Convinence_Fee"), "Includes a convenience fee of ₹ 200.00", 1);
		textPresent_Log(driver, "Includes a convenience fee of", 5);
	}

	@Test(priority=3)
	public void Validate_Text_NB() throws Exception {
		payUI_Select_PaymentType(driver, "NB");
		textPresent_Log(driver, "Popular Banks", 5);	
		textPresent_Log(driver, "All Other Banks", 1);	
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
		textPresent_Log(driver, "Please select your bank", 5);	
		textPresentInElement(driver, getObjectPayment("PayUI_Convinence_Fee"), "Includes a convenience fee of ₹ 200.00", 1);
		textPresent_Log(driver, "Includes a convenience fee of", 5);
	}

	@Test(priority=4)
	public void Validate_TermandCondition() throws Exception {
		safeClick(driver, getObjectPayment("PayUI_I_Agree_CheckBox"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
		textPresent_Log(driver, "Please accept the terms and conditions to proceed with this booking.", 5);
		boolean button = driver.findElement(By.xpath("//button")).isEnabled();
		if(!button) {
			Reporter.log("Make Pament Button is not disabled");
			Assert.assertTrue(false);
		}
	}
		
	@Test(priority=5)
	public void Validate_Text_Conv_Fee_Pricing() throws Exception {
		payUI_Select_PaymentType(driver, "CC");
		textPresentInElement(driver, getObjectPayment("PayUI_Convinence_Fee"), "Includes a convenience fee of ₹ 200.00", 1);
		textPresent_Log(driver, "Includes a convenience fee of", 5);
		String PricingElement = getText(driver, getObjectPayment("PayUI_Pricing_Elements"));
		if(!(PricingElement.contains("You pay")&&PricingElement.contains("Convenience fee"))) { 
			Reporter.log("You pay & Convenience fee text not diplayed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority=6)
	public void Validate_Itinerary() throws Exception {
		String ItineraryDetails = getText(driver, getObjectPayment("PayUI_Itinerary_Details"));
		if(!(ItineraryDetails.contains("Flight Itinerary")&&ItineraryDetails.contains("New Delhi")&&ItineraryDetails.contains("Mumbai")&&ItineraryDetails.contains("Travellers (5)")&&ItineraryDetails.contains("+1 travellers"))) {
			Reporter.log("Flight Itinerary and other detail text not diplayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectPayment("PayUI_Hide_Traveller_Link"));
		textPresent(driver, "+1 travellers", 2);
		safeClick(driver, getObjectPayment("PayUI_Hide_Traveller_Link"));
		textPresent(driver, "Sachin Reddy", 2); 
	}
	
	@Test(priority=7)
	public void Validate_Expressway() throws Exception {		
		safeClick(driver, getObjectPayment("PayUI_Expressway_CheckBox"));
		textPresent_Log(driver, "2006–2020 Cleartrip Pvt. Ltd", 1);		
		textPresent_Log(driver, "Save this card and make single-click payments", 1);	
	}
	
	@Test(priority=8)
	public void Validate_Misc() throws Exception {		
		elementPresent_log(driver, getObjectPayment("PayUI_Cleartrip_Logo"), "Cleartrip ", 2);
		textPresent_Log(driver, "2006–2020 Cleartrip Pvt. Ltd", 1);	
		textPresent_Log(driver, "Total inclusive all taxes", 1);	
		String Title = driver.getTitle();
		if(!Title.contains("cleartrip | payment securely")) {
			Reporter.log("cleartrip | payment securely page title is not displayed");
			Assert.assertEquals(Title, "cleartrip | payment securely");
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
