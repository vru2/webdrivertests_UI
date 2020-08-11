package paymentsUI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PaymentUI_Captcha extends domains.PaymentNodeJS{
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
	public void retryCaptcha() throws Exception{
		driver=(RemoteWebDriver) getDriver(driver);
		driver.manage().deleteAllCookies(); 
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		validatePaymentURLLoad(driver,Url);
		try{
			int flag=1;
			fillInInvalidCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
			while (flag<4){
				click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				flag++;
			}
			scrollIntoAParticularElement(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			flag++;
			while(flag==5||flag<7){
				scrollIntoAParticularElement(driver,PaymentUI_CommonUtilities.makePaymentbutton);
				click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
				validateRetryCaptcha(driver,PaymentUI_CommonUtilities.captchaErrorXpath,PaymentUI_CommonUtilities.captchaErrorName);
				flag++;
			}
		}

		catch(Exception e){
			Reporter.log("Exception is"+e);
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
