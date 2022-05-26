// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsBento_com;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Amendment extends PaymentUI_Common_Bento{
	public RemoteWebDriver driver;	
	
	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void Amendment_IN() throws Exception {
		get_Bento_Url(driver, "AirAmend", "");
		textNotPresent_Log(driver, "Includes a convenience fee of", 1);
		textPresent_Log(driver, "Paid before", 1);

		bento_Select_PaymentType(driver, "NB");

		textNotPresent_Log(driver, "Includes a convenience fee of", 1);
		textPresent_Log(driver, "Paid before", 1);
		bento_Select_PaymentType(driver, "UPI");

		textNotPresent_Log(driver, "Includes a convenience fee of", 1);
		textPresent_Log(driver, "Paid before", 1);
		bento_Select_PaymentType(driver, "TW");

		textNotPresent_Log(driver, "Includes a convenience fee of", 1);
		textPresent_Log(driver, "Paid before", 1);

		driver.manage().addCookie(cookie_Parl_Wallet);
		refreshPage(driver);
		smartClick(driver, By.cssSelector("span.checkbox__mark.bs-border.bc-neutral-500.bw-1.ba"));
		bento_Select_PaymentType(driver, "SC");		
		textNotPresent_Log(driver, "Includes a convenience fee of", 1);
		textPresent_Log(driver, "Paid before", 1);
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