// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  paymentsBento_com;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GV_Partial extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void GVPartial() throws Exception {
		get_Bento_Url(driver, "AirGV", "");
		textPresent_Log(driver, "Pay to complete your booking", 5);
		bento_Select_PaymentType(driver, "CC");
		bento_Select_PaymentType(driver, "NB");
		//bento_Select_PaymentType(driver, "PayPal");
		bento_Select_PaymentType(driver, "TW");
		String GVText=getText(driver, By.xpath("//div[@id='root']/div/main/div/div[2]/div[2]/div/div[10]/p"));
		if(!GVText.contains("Gift card")&&GVText.contains("3000331035955930")) {
			Reporter.log("GV is not displayed"+GVText);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Pay to complete your booking", 1);
		
		if(!elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is not displayed");
			Assert.assertTrue(false);
		}		
		textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare", 2);
		textPresent_Log(driver, "booking policy", 2);
		textPresent_Log(driver, "privacy policy", 1);
		textPresent_Log(driver, "terms", 1);		
		textPresent_Log(driver, "Convenience fee", 1);
		Reporter.log("Includes a convenience fee of text is displayed");
		String YouPay = getText(driver, By.xpath("//p[2]/span")); 
		if (!YouPay.contains("650")) {
			Reporter.log("Youpay doesn't contain 650 rs");
			Assert.assertTrue(false);
		}
	
		String ConvFee = getText(driver, By.xpath("//div[8]/p"));
		if (!ConvFee.contains("150")) {
			Reporter.log("ConvFee doesn't contain 150 rs");
			Assert.assertTrue(false);
		}else Reporter.log("ConvFee contain 150 rs");
		String Total = getText(driver, By.xpath("//div/div/span"));
		if (!Total.contains("650")) {
			Reporter.log("Total doesn't contain 650 rs");
			Assert.assertTrue(false);
		}else Reporter.log("Total contain 6500 rs");

		Assert.assertEquals("Pay now", getText(driver, getObjectPayment("Bento_Pay_Button")));
		
		
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