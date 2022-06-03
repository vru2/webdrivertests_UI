// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsUI_Flyin;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.paymentsUI_Air.PaymentUI_Common;

public class Flyin_Arabic extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test(priority=1)
	public void Validate_Text_CC() throws Exception {
		String PayUrl = getPayUI("AirFlyinAR", "FLYIN");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType(driver, "CC");
		validate_Currency(driver, "", "SAR");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));	
		elementPresent_log(driver, By.cssSelector("div.ml-3.fs-2.cvv-help-text > p"), "CVV help", 5);
		textPresent_Log(driver, "Enter your credit card details", 1);		
		textPresent_Log(driver, "Credit card no.", 1);				
		textPresent_Log(driver, "Expiry date", 1);				
		textPresent_Log(driver, "Card holder", 1);		
		textPresent_Log(driver, "CVV", 1);		
		textPresent_Log(driver, "Please enter a valid cvv", 1);			
		textPresent_Log(driver, "Please enter a valid card number", 1);		
		textPresent_Log(driver, "Please enter a valid expiry month", 1);	
		textPresent_Log(driver, "Please enter a valid name", 1);		
		textPresent_Log(driver, "Please enter a valid expiry year", 1);		
		textPresent_Log(driver, "Please enter a valid cvv", 1);					
		String Conv_Fee = getText(driver, By.cssSelector("p.note-block__message.fs-2"));
		
		if(!Conv_Fee.equals("Includes a convenience fee of SAR  10")) {
			Reporter.log("Conv fee is not shown its displayed as "+Conv_Fee);
			Assert.assertTrue(false);
		}
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
		textPresent_Log(driver, "Debit card no.", 1);				
		textPresent_Log(driver, "Expiry date", 1);				
		textPresent_Log(driver, "Card holder", 1);		
		textPresent_Log(driver, "CVV", 1);		
		textPresent_Log(driver, "Please enter a valid card number", 1);		
		textPresent_Log(driver, "Please enter a valid expiry month", 1);		
		textPresent_Log(driver, "Please enter a valid expiry year", 1);		
		textPresent_Log(driver, "Please enter a valid name", 1);	
		textPresent_Log(driver, "Please enter a valid cvv", 1);
		textPresent_Log(driver, "Includes a convenience fee of", 1);
	}

	@Test(priority=3)
	public void Validate_TermandCondition() throws Exception {
		payUI_Select_PaymentType(driver, "CC");
		safeClick(driver, getObjectPayment("PayUI_I_Agree_CheckBox"));
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));		
		textPresent_Log(driver, "Please accept the terms and conditions to proceed with this booking", 2);
		textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare,", 1);
		
		
	//	textPresent_Log(driver, "Booking policy the Privacy Policy ", 1);
		//textPresent_Log(driver, "and the Terms & Conditions of Flyin", 1);
		
	/*	boolean button = driver.findElement(By.xpath("//button")).isEnabled();
		if(!button) {
			Reporter.log("Make Pament Button is not disabled");
			Assert.assertTrue(false);
		}
		safeClick(driver, getObjectPayment("PayUI_I_Agree_CheckBox"));
		button = driver.findElement(By.xpath("//button")).isEnabled();
		if(button) {
			Reporter.log("Make Pament Button is not enabled");
			Assert.assertTrue(false);
		}*/
	}

	@Test(priority=4)
	public void Validate_Text_Conv_Fee_Pricing() throws Exception {
		payUI_Select_PaymentType(driver, "CC");
		String Conv_Fee = getText(driver, By.cssSelector("p.note-block__message.fs-2"));
		
		if(!Conv_Fee.equals("Includes a convenience fee of SAR  10")) {
			Reporter.log("Conv fee is not shown its displayed as "+Conv_Fee);
			Assert.assertTrue(false);
		}
		textPresentInElement(driver, getObjectPayment("PayUI_Convinence_Fee"), "Includes a convenience fee of SAR 10", 1);
		String PricingElement = getText(driver, getObjectPayment("PayUI_Pricing_Elements"));
		if(!(PricingElement.contains("You pay")&&PricingElement.contains("Convenience fee"))) { 
			Reporter.log("You pay & Convenience fee text not diplayed");
			Assert.assertTrue(false);
		}
		
	}


	@Test(priority=5)
	public void Validate_Itinerary() throws Exception {
		payUI_Select_PaymentType(driver, "CC");	
		
		String ItineraryDetails = getText(driver, getObjectPayment("PayUI_Itinerary_Details"));
		if(!(ItineraryDetails.contains("Flight itinerary")&&ItineraryDetails.contains("Bangalore")&&ItineraryDetails.contains("Mumbai")&&ItineraryDetails.contains("Travellers (1)"))) {
			Reporter.log("Flight Itinerary and other detail text not diplayed");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Mr test test", 2); 
		

		/*safeClick(driver, getObjectPayment("PayUI_Hide_Traveller_Link"));
		textPresent(driver, "+1 travellers", 2);
		safeClick(driver, getObjectPayment("PayUI_Hide_Traveller_Link"));*/
	}
	

	@Test(priority=6)
	public void Validate_Expressway_Unavialblility() throws Exception {	
			if(elementVisible(driver, getObjectPayment("PayUI_Expressway_CheckBox"), 1)) {
			Reporter.log("save card is displayed for unsigned User");
			Assert.assertTrue(false);
		}
	}
	

	@Test(priority=7)
	public void Validate_Misc() throws Exception {		
		elementPresent_log(driver, By.id("image0"), "Flyin Logo ", 2);
		
		
		textPresent_Log(driver, " 2006–2020 Saudi Ebreez Company", 1);
		
		

		textPresent_Log(driver, "Completely safe and secure transaction", 1);
		textPresent_Log(driver, "Total inclusive all taxes", 1);	
		String Title = driver.getTitle();
		if(!Title.contains("Flyin | Pay securely")) {
			Reporter.log("flyin | payment securely page title is not displayed");
			Assert.assertEquals(Title, "Flyin | Pay securely");
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
