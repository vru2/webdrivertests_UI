// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class Validate_Images_Links extends PaymentUI_Common{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	
	@Test(priority=1)
	public void images() throws Exception {
		String PayUrl = getPayUI("Air", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		String  handle= driver.getWindowHandle();
		elementPresent_log(driver, getObjectPayment("PayUI_Image_CC_VerifyVisa"), "Visa Image", 5);
		safeClick(driver, getObjectPayment("PayUI_Image_CC_VerifyVisa"));
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String visaUrl = getURL(driver);
		Reporter.log("Visa Image URL : "+visaUrl);
		if(!visaUrl.contains("usa.visa.com/support/consumer/security.html")) {
			Reporter.log("Visa Image URL : "+visaUrl);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Peace of mind", 5);
		driver.switchTo().window(handle);
		elementPresent_log(driver, getObjectPayment("PayUI_Image_CC_VerifyMaster"), "Master Card image", 5);
		safeClick(driver, getObjectPayment("PayUI_Image_CC_VerifyMaster"));
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String masterCardUrl = getURL(driver);
		Reporter.log("masterCardUrl Image URL : "+visaUrl);
		if(!masterCardUrl.contains("https://www.mastercard.us/en-us.html")) {
			Reporter.log("masterCard Image URL : "+masterCardUrl);
			Assert.assertTrue(false);
		}

		driver.switchTo().window(handle);
		elementPresent_log(driver, getObjectPayment("PayUI_Image_CC_VerifyAMEX"), "AMEX image", 5);
		safeClick(driver, getObjectPayment("PayUI_Image_CC_VerifyAMEX"));
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String amexCardUrl = getURL(driver);
		Reporter.log("amexCardUrl Image URL : "+amexCardUrl);
		if(!amexCardUrl.contains("www.americanexpress.com")) {
			Reporter.log("masterCard Image URL : "+amexCardUrl);
			Assert.assertTrue(false);
		}

		driver.switchTo().window(handle);		
		
	}
	
	
	@Test(priority=2)
	public void links() throws Exception {

		String  handle= driver.getWindowHandle();
		elementPresent_log(driver, getObjectPayment("PayUI_Booking_Policy"), "Booking Policy", 5);
		safeClick(driver, getObjectPayment("PayUI_Booking_Policy"));
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		String BookingPolicyUrl = getURL(driver);
		Reporter.log("Booking Policy URL : "+BookingPolicyUrl);
		if(!BookingPolicyUrl.contains("qa2.cleartrip.com/flights/booking-policies")) {
			Reporter.log("BookingPolicyUrl URL : "+BookingPolicyUrl);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Cleartrip flight booking policy", 5);
		driver.switchTo().window(handle);		
		elementPresent_log(driver, getObjectPayment("PayUI_Privacy_Policy"), "Privacy Policy", 5);
		safeClick(driver, getObjectPayment("PayUI_Privacy_Policy"));
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	/*	String PrivacyPolicyUrl = getURL(driver);
		Reporter.log("PrivacyPolicyUrl URL : "+PrivacyPolicyUrl);
		if(!PrivacyPolicyUrl.contains("qa2.cleartrip.com/privacy")) {
			Reporter.log("PrivacyPolicyUrl URL : "+PrivacyPolicyUrl);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Privacy Policy", 5);
	*/	driver.switchTo().window(handle);
		
		elementPresent_log(driver, getObjectPayment("PayUI_Privacy_Policy"), "Privacy Policy", 5);
		safeClick(driver, getObjectPayment("PayUI_Privacy_Policy"));
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		/*String TermsConditionUrl = getURL(driver);
		Reporter.log("PrivacyPolicyUrl URL : "+TermsConditionUrl);
		if(!TermsConditionUrl.contains("qa2.cleartrip.com/terms")) {
			Reporter.log("TermsConditionUrl URL : "+TermsConditionUrl);
			Assert.assertTrue(false);
		}*/
		//textPresent_Log(driver, "Cleartrip flight booking policy", 5);
		driver.switchTo().window(handle);
		
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