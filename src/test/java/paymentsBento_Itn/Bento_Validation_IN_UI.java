package paymentsBento_Itn;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_Validation_IN_UI extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
	}

	@Test (priority=1)
	public void CC_Validation() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(searchurl("IN"));
		Reporter.log(searchurl("IN"));
		Searchpagebook(driver,"","com","");
	    book_itnnew(driver,"");
		driver.manage().deleteAllCookies(); // deleting login details
	    bento_Validation_UI(driver,"CC");	    
	}
	
	@Test (priority=2)
	public void NB_Validation() throws Exception {
		bento_Validation_UI(driver,"NB");
	}
	
	@Test (priority=3)
	public void TP_Wallet_Validation() throws Exception {
		bento_Validation_UI(driver,"TW");
	}

	@Test (priority=4)
	public void UPI_Validation() throws Exception {
		bento_Validation_UI(driver,"UPI");
		
	}	

	@Test (priority=5)
	public void StoredCard_Validation_Text() throws Exception {
		bento_Validation_UI(driver,"SC");		
	}	 
	
	@Test (priority=6)
	public void Expressway_Validation_Text() throws Exception {
		bento_Validation_UI(driver,"Expressway");		
	}	 
	
	@AfterClass
	public void closeSelenium() throws Exception {
	 	browserClose(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

}