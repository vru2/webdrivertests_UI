package paymentsBento_Itn;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Bento_IN_EMI_Razorpay extends PaymentsBento_Itn_Common {

	@BeforeClass
	public void startSelenium() throws Exception {
		this.driver = getDriver(driver);
		baseUrl = getBaseUrl("com");
	}

	@Test
	public void bento_EMI() throws Exception {
		driver.manage().deleteAllCookies();
		driver.navigate().to(qa2url + searchurl1);
		driver.manage().addCookie(cookie_Bento_Payment_EMI);	
		System.out.println(qa2url + searchurl1);
		Reporter.log(qa2url + searchurl1);
		Searchpagebook(driver, "","com","");
		book_itnnew(driver,"");
		bento_Validation_Text(driver, "EMI", "");
		paymentPage(driver,"EMI","RAZORPAY","","",""); 
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
