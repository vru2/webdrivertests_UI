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

public class Validate_Text_Messages extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test(priority=1)
	public void Validate_Text_CC() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType(driver, "CC");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		elementPresent_log(driver, By.cssSelector("div.col-8.pl-0 > svg"), "CVV help", 5);
		textPresent_Log(driver, "Enter your credit card details", 1);	
		textPresent_Log(driver, "Credit card no.", 1);	
		textPresent_Log(driver, "Expiry date", 1);	
		textPresent_Log(driver, "Card holder", 1);	
		textPresent_Log(driver, "CVV", 1);			
		textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare", 1);			
		textPresent_Log(driver, "Booking policy", 1);	
	//	textPresent_Log(driver, "the Privacy Policy", 1);	
	//	textPresent_Log(driver, "and the Terms", 1);	
		textPresent_Log(driver, "Conditions of Cleartrip", 1);	
		textPresent_Log(driver, "Please enter a valid expiry month", 1);		
		textPresent_Log(driver, "Please enter a valid expiry year", 1);		
		textPresent_Log(driver, "Please enter a valid name", 1);	
		textPresent_Log(driver, "Please enter a valid cvv", 1);					
		textPresent_Log(driver, "Includes a convenience fee of", 5);
	//	textPresent_Log(driver, "Save this card and make single-click payments", 1);	
		/*payUI_Error_Validation(driver, getObjectPayment("PaymentPage_Error_Banner"), "Enter valid credit card number");
		safeClick(driver, getObjectPayment("PaymentPage_Error_Banner_Close_Btn"));
		if(elementNotPresent_Time(driver, getObjectPayment("PaymentPage_Error_Banner"), 10)) {
			Reporter.log("Error banner is not closed");	
			Assert.assertTrue(false);
		}*/
	}

	@Test(priority=2)
	public void Validate_Text_DC() throws Exception {
		payUI_Select_PaymentType(driver, "DC");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));	
		textPresent_Log(driver, "Enter your debit card details", 2);
		textPresent_Log(driver, "Please enter a valid card number", 1);		
		textPresent_Log(driver, "Please enter a valid expiry month", 1);		
		textPresent_Log(driver, "Please enter a valid expiry year", 1);	
		textPresent_Log(driver, "Please enter a valid name", 1);	
		textPresent_Log(driver, "Please enter a valid cvv", 1);
		textPresent_Log(driver, "Includes a convenience fee of", 1);
		//payUI_Error_Validation(driver, getObjectPayment("PaymentPage_Error_Banner"), "Enter valid debit card number");
	}

	@Test(priority=3)
	public void Validate_Text_NB() throws Exception {
		payUI_Select_PaymentType(driver, "NB");
		textPresent_Log(driver, "Popular banks", 2);	
		textPresent_Log(driver, "All other banks", 1);	
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
		textPresent_Log(driver, "Please select your bank", 5);	
		textPresent_Log(driver, "Includes a convenience fee of", 1);
	}

	@Test(priority=4)
	public void Validate_Text_TW() throws Exception {
		payUI_Select_PaymentType(driver, "TW");
		textPresent_Log(driver, "Select a wallet to make your payment", 5);	
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
		textPresent_Log(driver, "Please select any wallet", 2);	
		textPresent_Log(driver, "Includes a convenience fee of", 1);
	}

	@Test(priority=5)
	public void Validate_Text_UPI() throws Exception {
		payUI_Select_PaymentType(driver, "UPI");
		textPresent_Log(driver, "Select UPI partner to make your payment", 5);	
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
		textPresent_Log(driver, "Please select any UPI payment method", 2);
		textPresent_Log(driver, "Includes a convenience fee of", 1);
	}
	
	@Test(priority=6)
	public void Validate_TermandCondition() throws Exception {
		payUI_Select_PaymentType(driver, "CC");
		safeClick(driver, getObjectPayment("PayUI_I_Agree_CheckBox"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
		textPresent_Log(driver, "Please accept the terms and conditions to proceed with this booking", 2);
		boolean button = driver.findElement(By.xpath("//button")).isEnabled();
		safeClick(driver, getObjectPayment("PayUI_I_Agree_CheckBox"));
		if(!button) {
			Reporter.log("Make Pament Button is not disabled");
			Assert.assertTrue(false);
		}
	}
		
	@Test(priority=7)
	public void Validate_Text_Conv_Fee_Pricing() throws Exception {
		payUI_Select_PaymentType(driver, "CC");
		textPresentInElement(driver, getObjectPayment("PayUI_Convinence_Fee"), "Includes a convenience fee of ₹ 150.00", 1);
		textPresent_Log(driver, "Includes a convenience fee of", 5);
		String PricingElement = getText(driver, getObjectPayment("PayUI_Pricing_Elements"));
		if(!(PricingElement.contains("You pay")&&PricingElement.contains("Convenience fee"))) { 
			Reporter.log("You pay & Convenience fee text not diplayed");
			Assert.assertTrue(false);
		}
		//Assert.assertTrue(false);// validate Conv fee
	}

	@Test(priority=8)
	public void Validate_Itinerary() throws Exception {
		payUI_Select_PaymentType(driver, "CC");	
		
		String ItineraryDetails = getText(driver, getObjectPayment("PayUI_Itinerary_Details"));
		if(!(ItineraryDetails.contains("Flight itinerary")&&ItineraryDetails.contains("Bangalore")&&ItineraryDetails.contains("Mumbai")&&ItineraryDetails.contains("Travellers (5)")&&ItineraryDetails.contains("+1 travellers"))) {
			Reporter.log("Flight Itinerary and other detail text not diplayed");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectPayment("PayUI_Hide_Traveller_Link"));
		textPresent(driver, "+1 travellers", 2);
		safeClick(driver, getObjectPayment("PayUI_Hide_Traveller_Link"));
		textPresent(driver, "Sachin Reddy", 2); 
	}
	
	@Test(priority=9)
	public void Validate_Expressway_Unsigned() throws Exception {	
		payUI_Select_PaymentType(driver, "CC");	
		if(elementVisible(driver, getObjectPayment("PayUI_Expressway_CheckBox"), 1)) {
			Reporter.log("save card is displayed for unsigned User");
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Save this card and make single-click payments", 1)) {
			Reporter.log("Save this card and make single-click payments");
			Assert.assertTrue(false);
		}
		
		/*
		safeClick(driver, getObjectPayment("PayUI_Expressway_CheckBox"));
		textPresent_Log(driver, "2006–2020 Cleartrip Pvt. Ltd", 1);
		textPresent_Log(driver, "Save this card and make single-click payments", 1);	*/	
	}
	
	@Test(priority=10)
	public void Validate_Misc() throws Exception {		
		payUI_Select_PaymentType(driver, "CC");
		elementPresent_log(driver, getObjectPayment("PayUI_Cleartrip_Logo"), "Cleartrip ", 2);
		textPresent_Log(driver, "2006–2020 Cleartrip Pvt. Ltd", 1);
		textPresent_Log(driver, "Completely safe and secure transaction", 1);
		textPresent_Log(driver, "Total inclusive all taxes", 1);	
		String Title = driver.getTitle();
		if(!Title.contains("Cleartrip | Pay securely")) {
			Reporter.log("Cleartrip | Pay securely page title is not displayed");
			Assert.assertEquals(Title, "cleartrip | payment securely");
			Assert.assertTrue(false);
		}
	}
	
	/*@Test(priority=11)
	public void Validate_CCDC_Text() throws Exception {
		payUI_Select_PaymentType(driver, "DC");
		Enter_CC_Details(driver, platform.value("AmexCard_Number"), platform.value("AmexCard_Month_New"), platform.value("AmexCard_Year"), platform.value("AmexCard_CVV"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		textPresent_Log(driver, "Enter valid Debit card number", 2);
		payUI_Select_PaymentType(driver, "CC");
		Enter_CC_Details(driver, platform.value("ADCBCard_Number"), platform.value("ADCBCard_Expiry_Month"), platform.value("ADCBCard_Expiry_Year"), platform.value("ADCBCard_CVV"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		textPresent_Log(driver, "Enter valid credit card number", 2);

	}*/
		
		
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
