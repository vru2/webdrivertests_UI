package paymentsUI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_ErrorValidationUPIWalletAndNB extends PaymentNodeJS {
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;


	@BeforeClass
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}


	@Test
	public void errorValidations() throws Exception{
	
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);		
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			click(driver,PaymentUI_CommonUtilities.upiModeXpath);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			validateIfPresent(driver,PaymentUI_CommonUtilities.upiErrorTextXpath);
			click(driver,PaymentUI_CommonUtilities.nbModeXpath);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			validateIfPresent(driver,PaymentUI_CommonUtilities.nbErrorTextXpath);
		
	}

	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass
	public void tearDown(){
		browserClose(driver);
	}
}