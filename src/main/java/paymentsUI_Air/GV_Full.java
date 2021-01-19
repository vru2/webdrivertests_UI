// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsUI_Air;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GV_Full extends PaymentUI_Common{
	public RemoteWebDriver driver;
	
	@Test
	public void GVFull() throws Exception {
		String PayUrl = getPayUI("AirGVFull", "");
		driver.manage().deleteAllCookies(); 
		driver.get(PayUrl);	  
		refreshPage(driver);
		String GVText=getText(driver, By.xpath("//div[3]/div[4]/div"));
		  if(!GVText.contains("Gift card")&&GVText.contains("3000331035955930")) {
			Reporter.log("GV is not displayed"+GVText);
			Assert.assertTrue(false);
		}
		if(textPresent(driver, "Enter your credit card details", 2)) {
			Reporter.log("for full GV aother pay options are displayed - Enter your credit card details");
			Assert.assertTrue(false);
		}
		if(elementVisible(driver, getObjectPayment("PayUI_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is displayed");
			Assert.assertTrue(false);
		}
		
		Reporter.log("Non of the payment types are displayed for full GV");
		textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare", 2);
		textPresent_Log(driver, "Booking policy", 2);
		textPresent_Log(driver, "Privacy Policy", 1);
		textPresent_Log(driver, "Terms", 1);		
		textPresent_Log(driver, "Includes a convenience fee of ", 1);
		Reporter.log("Includes a convenience fee of text is displayed");
		String YouPay = getText(driver, By.cssSelector("p.fw-700.fs-6.flex.flex-end")); 
		if (!YouPay.contains("0")) {
			Reporter.log("Youpay doesn't contain 0 rs");
			Assert.assertTrue(false);
		}
		if (YouPay.contains("150")) {
			Reporter.log("Youpay does contain 150 rs");
			Assert.assertTrue(false);
		}
		 Reporter.log("Youpay  contain 0 rs");
		String ConvFee = getText(driver, By.cssSelector("p.note-block__message.fs-2"));
		if (!ConvFee.contains("150")) {
			Reporter.log("ConvFee doesn't contain 150 rs");
			Assert.assertTrue(false);
		}else Reporter.log("ConvFee contain 150 rs");
		if (!ConvFee.contains("Includes a convenience fee of")) {
			Reporter.log("Includes a convenience fee of text not displayed");
			Assert.assertTrue(false);
		}else Reporter.log("Includes a convenience fee of text is displayed");
		String Total = getText(driver, By.cssSelector("span.fs-6.fw-700"));
		if (!Total.contains("0")) {
			Reporter.log("Total doesn't contain 0 rs");
			Assert.assertTrue(false);
		}else Reporter.log("Total contain 0 rs");

		Assert.assertEquals("Complete booking", getText(driver, getObjectPayment("PayUI_Make_Payment_Btn")));
		
		if(common.value("Bento_Payment").equalsIgnoreCase("true")) {			
		safeClick(driver, getObjectPayment("PayUI_Make_Payment_Btn"));
		Reporter.log("Scripts should be fixed after Air integration");
		Thread.sleep(10000);
		payUI_Mock_ConfirmationPage(driver, PayUrl);
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
