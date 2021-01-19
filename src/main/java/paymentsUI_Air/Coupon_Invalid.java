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

public class Coupon_Invalid extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test (priority=1)
	public void Coupon_Remove() throws Exception {		
		String PayUrl = getPayUI("AirCoupon", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);
		refreshPage(driver);
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), "Net Banking");	
		String CouponText = getText(driver, By.xpath("//div[4]/div"));
		if(!CouponText.contains("DOMOW")) {
			Reporter.log("Copon text is not displayed "+CouponText);
			Assert.assertTrue(false);
		}
		elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 20);
		textPresent_Log(driver, "Popular banks", 1);
		safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "ICICI Bank");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		/*textPresent_Log(driver, "Hmm, something's not right", 5);
		if(!elementVisible(driver, By.xpath("//div[@id='root']/main/div/section/div/div/div/div/div/div/div/div/div"), 10)) {
			
			Reporter.log("Hmm, something's not right - Coupin api not working");
			Assert.assertTrue(false);
		}*/

		textPresent_Log(driver, "Coupon not applicable", 10);
		String Coupon_Invalid_Price = getText(driver, By.xpath("//div/div/div/div/div/div/div/div/div[2]"));
		
		if(!Coupon_Invalid_Price.contains("800")) {
			Reporter.log("800 rs is not displayed");
			Assert.assertTrue(false);
		}	

		//textPresent_Log(driver, "Coupon code DOMOW is not applicable on the chosen payment mode. Please update card details to avail benefits or proceed without coupon. Your updated price is", 5);
		textPresent_Log(driver, "Coupon code DOMOW is not applicable on the chosen payment method. Change payment mode/card details or book without coupon at", 5);
		

		safeClick(driver, By.xpath("//button"));

	//	safeClick(driver, By.xpath("//div[@id='root']/main/div/section/div/div/div/div/div/div/div/div[2]/div"));
		if(!elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 10)) {
			Reporter.log("PayUI Page is not displayed");
			String UI_error = getText(driver, By.xpath("//h1"));
			Reporter.log(UI_error);			
			Assert.assertTrue(false);
		}
		Thread.sleep(5000);
		safeClickList(driver, getObjectPayment("PayUI_Pay_Tabs"), "Net Banking");
		elementVisible(driver, getObjectPayment("PayUI_NB_DropDown"), 20);
		textPresent_Log(driver, "Popular banks", 1);
		safeSelect(driver, getObjectPayment("PayUI_NB_DropDown"), "ICICI Bank");
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		//String ChangePayment_Text= getText(driver, By.xpath("//button"));

		String ContinueWithout_Text= getText(driver, By.xpath("//button[2]"));
		//System.out.println("ChangePayment_Text "+ChangePayment_Text);
		//System.out.println("ContinueWithout_Text "+ContinueWithout_Text);
	//	Assert.assertEquals(ChangePayment_Text, "Change payment mode");
		Assert.assertEquals(ContinueWithout_Text, "Book without Coupon");
		if(elementVisible(driver, By.xpath("//div[@id='root']/main/div/section/div/div/div/div/div/div/div/div/div"), 10)) {
			//Assert.assertTrue(false);
		}
		safeClick(driver, By.xpath("//div[@id='root']/main/div/section/div/div/div/div/div/div/div/div[2]/button"));
		/*payUI_BankPage(driver, "Citibank");
		payUI_Mock_ConfirmationPage(driver, PayUrl); */
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
