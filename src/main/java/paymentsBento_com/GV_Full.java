// Framework - Cleartrip Automation
// Author - Kiran Kumar

package paymentsBento_com;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GV_Full extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test
	public void GVFull() throws Exception {
		get_Bento_Url(driver, "AirGVFull", "");
		textPresent_Log(driver, "Pay to complete your booking", 5);
		textNotPresent_Log(driver, "Enter card details", 1);
		
		String GVText=getText(driver, By.xpath("//div[2]/div[2]/div[1]/div[10]/p[1]"));
		  if(!GVText.contains("Gift card")&&GVText.contains("3000331035955930")) {
			Reporter.log("GV is not displayed"+GVText);
			Assert.assertTrue(false);
		}
		textPresent_Log(driver, "Pay to complete your booking", 1);
		
		if(elementVisible(driver, getObjectPayment("Bento_Pay_Tabs"), 1)) {
			Reporter.log("CC tab is displayed");
			Assert.assertTrue(false);
		}		
		Reporter.log("payment tab is not displayed for full GV");
		textPresent_Log(driver, "I understand and agree to the rules and restrictions of this fare", 2);
		textPresent_Log(driver, "booking policy", 2);
		textPresent_Log(driver, "privacy policy", 1);
		textPresent_Log(driver, "terms", 1);		
		textPresent_Log(driver, "Convenience fee", 1);
		Reporter.log("Includes a convenience fee of text is displayed");
		String YouPay = getText(driver, By.xpath("//div[2]/div[2]/div[1]")); 
		if (!YouPay.contains("0")) {
			Reporter.log("Youpay doesn't contain 0 rs");
			Assert.assertTrue(false);
		}
	
		 Reporter.log("Youpay  contain 0 rs");
		String ConvFee = getText(driver, By.xpath("//div[2]/div/div[1]/div[8]/p"));
		if (!ConvFee.contains("150")) {
			Reporter.log("ConvFee doesn't contain 150 rs");
			Assert.assertTrue(false);
		}else Reporter.log("ConvFee contain 150 rs");
		String Total = getText(driver, By.xpath("//div/div/span"));
		System.out.println("total "+Total);
		if (!Total.contains("0")) {
			Reporter.log("Total doesn't contain 0 rs");
			Assert.assertTrue(false);
		}else Reporter.log("Total contain 0 rs");

		Assert.assertEquals("Complete booking", getText(driver, getObjectPayment("Bento_Pay_Button")));
		
		
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