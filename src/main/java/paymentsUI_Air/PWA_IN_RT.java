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

public class PWA_IN_RT extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void PWA_RT() throws Exception {
		String PayUrl = getPayUI("AirRT", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Itineray"),10);
		String ItineraryDetails_TripText = getText(driver, getObjectPayment("PWA_PaymentPage_Itineray_YourTrip_text"));
		Assert.assertEquals(ItineraryDetails_TripText, "Your trip details");

		safeClick(driver, getObjectPayment("PWA_PaymentPage_Itineray"));
		elementVisible(driver, getObjectPayment("PWA_PaymentPage_Itineray_Header"),10);
		String itineraryText=getText(driver, getObjectPayment("PWA_PaymentPage_Itineray_Header"));
		if(!itineraryText.contains("Trip summary")) {
			Reporter.log("Trip summary text is not displayed");
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "FLIGHT ITINERARY", 1);
		textPresent_Log(driver, "TRAVELLERS", 1);
		elementPresent(driver, By.xpath("//div[2]/div[2]/div"));
		String from = getText(driver, By.xpath("//div[2]/div/div/div/div"));
		String to = getText(driver, By.xpath("//div[2]/div[2]/div"));
		System.out.println("from "+from);
		System.out.println("from "+to);
		if(!(to.contains("BLR")&&to.contains("MAA"))) {
			Reporter.log("to "+to);
			Assert.assertTrue(false);
		}
		if(!(from.contains("BLR")&&to.contains("MAA"))) {
			Reporter.log("from "+from);
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