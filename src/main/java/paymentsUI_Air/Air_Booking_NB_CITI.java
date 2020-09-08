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

import io.restassured.response.Response;

public class Air_Booking_NB_CITI extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void NB_Citi_Pay() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(AirUrl);
		String  handle= driver.getWindowHandle();
		elementPresent_Time(driver, By.xpath("//div[4]/button"),30);
		textPresent(driver, "Get fare updates via email", 10);
		safeClick(driver, By.xpath("//div[4]/button"));
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		elementPresent_Time(driver, By.xpath("//div[2]/div/div/div/button"),30);
		textPresent(driver, "Review your itinerary", 10);		
		String itneraryUrl = getURL(driver);
		Reporter.log("itnerary URL : "+itneraryUrl);
		driver.switchTo().window(handle);
		driver.get(itneraryUrl);	
		safeClick(driver, By.xpath("//div[2]/div/div/div/button"));
		elementVisible(driver, By.xpath("//div[2]/div/input"), 30);
		safeType(driver, By.xpath("//div[2]/div/input"), "9986696785");
		safeType(driver, By.xpath("//div[3]/div/div/input"), "cltppayment@gmail.com");
		payUI_Select_PaymentType(driver, "NB");
		
		//validate_Currency(driver, "", "INR");
		payUI_Enter_PaymentDetails(driver, "NB", "Citibank");
		payUI_Mock_ConfirmationPage(driver, PayUrl);
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
