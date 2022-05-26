package test.java.paymentsUI;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_FailureMessage extends PaymentNodeJS{

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
	public void PaymentFailure() throws Exception{
        try {
        	driver=(RemoteWebDriver) getDriver(driver);
    		driver.manage().deleteAllCookies(); 
    		driver.get(Url);
    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    		fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
    		Thread.sleep(15);
    		selectFromDropdown(driver, PaymentUI_CommonUtilities.amexGatewayAuthenticationFailureXpath, PaymentUI_CommonUtilities.amexGatewayAuthenticationFailureValue);
    		click(driver,PaymentUI_CommonUtilities.amexGatewayAuthenticationSubmitxpath);
    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    		validateIfPresent(driver, PaymentUI_CommonUtilities.retry3DFailureHeaderXpath);
    		validateIfPresent(driver, PaymentUI_CommonUtilities.retry3DFailureMessageXpath);
        }
		
        catch(Exception e) {
        	Reporter.log("Exception is" +e);
        	Assert.assertTrue(false);
        }
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

