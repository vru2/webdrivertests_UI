package paymentsUI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import paymentsUI_Air.PaymentUI_Common;

public class PaymentUI_SuccessfulPaymentMessage  extends PaymentUI_Common{

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
	public void successMessage() throws Exception{
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			payUI_Select_PaymentType(driver, "CC");
			payUI_Enter_PaymentDetails(driver, "CC", "AMEXTRAIN");
			payUI_Mock_ConfirmationPage_Train(driver, Url);
			
			/*driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			elementPresent(driver, By.xpath("//input[@type='submit']"), 30);
			click(driver,PaymentUI_CommonUtilities.amexGatewayAuthenticationSubmitxpath);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.get(Url);
			waitForElementVisibility(driver,By.xpath("//h1[text()='Payment successful!']"),10);
			validateIfPresent(driver,PaymentUI_CommonUtilities.paymentSuccessHeaderTextXpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.paymentSuccessMessageTextXpath);	*/	
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