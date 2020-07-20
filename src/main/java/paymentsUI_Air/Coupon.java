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

import io.restassured.response.Response;

public class Coupon extends PaymentUI_Common{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	
	@Test (priority=1)
	public void Coupon_Remove() throws Exception {		
		String PayUrl = getPayUI("AirCoupon", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), "Net Banking");	
		String CouponText = getText(driver, By.xpath("//div[4]/div"));
		if(!CouponText.contains("DOMOW")) {
			Reporter.log("Copon text is not displayed "+CouponText);
			Assert.assertTrue(false);
		}
		elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 20);
		textPresent_Log(driver, "Popular Banks", 1);
		safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "Citibank");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		if(!elementVisible(driver, By.xpath("//div[@id='root']/main/div/section/div/div/div/div/div/div/div/div/div"), 10)) {
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Coupon code DOMOW is not applicable on the chosen payment mode. Please update card details to avail benefits or proceed without coupon. Your updated price is", 5);
		safeClick(driver, By.xpath("//div[@id='root']/main/div/section/div/div/div/div/div/div/div/div[2]/div"));
		if(!elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10)) {
			Reporter.log("PayUI Page is not displayed");
			String UI_error = getText(driver, By.xpath("//h1"));
			Reporter.log(UI_error);			
			Assert.assertTrue(false);
		}
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), "Net Banking");
		elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 20);
		textPresent_Log(driver, "Popular Banks", 1);
		safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "Citibank");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		if(elementVisible(driver, By.xpath("//div[@id='root']/main/div/section/div/div/div/div/div/div/div/div/div"), 10)) {
			//Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//div[@id='root']/main/div/section/div/div/div/div/div/div/div/div[2]/button"));
		payUI_BankPage(driver, "Citibank");
		payUI_Mock_ConfirmationPage(driver, PayUrl); 
	}
	
/*	@Test (priority=2)
	public void Coupon_Pay_Valid() throws Exception {
		
		String PayUrl = getPayUI("AirCoupon", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		payUI_Select_PaymentType(driver, "CC");
		payUI_Enter_PaymentDetails(driver, "CC", "MASTER");
		payUI_Mock_ConfirmationPage(driver, PayUrl);	
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
