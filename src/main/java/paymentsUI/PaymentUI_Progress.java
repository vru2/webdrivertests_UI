package paymentsUI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_Progress extends PaymentNodeJS{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;


	@BeforeClass
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","");
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}

	@Test(priority=1)
	public void firePaymentURLandValidatePaymentModes() throws Exception{		
			validation_PaymentUI("BookApp/GetPay", resp);
		try{
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			elementVisible(driver, By.xpath("//ul[@id='paymentModeTab']"), 10);
			validatePaymentURLLoad(driver,Url);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeCC,PaymentUI_CommonUtilities.paymentModexpath);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeDC,PaymentUI_CommonUtilities.paymentModexpath);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeNB,PaymentUI_CommonUtilities.paymentModexpath);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeUPI,PaymentUI_CommonUtilities.paymentModexpath);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeRP,PaymentUI_CommonUtilities.paymentModexpath);

		}
		catch(Exception e){
			Reporter.log("Exception is"+e);
		}
	}
	
	@Test(priority=2)
	public void inProgressPayment() throws Exception{
		try{
			fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			Thread.sleep(10000);
            driver.get(Url);
            validateIfPresent(driver, PaymentUI_CommonUtilities.paymentInProgressHeaderXpath);
            validateIfPresent(driver, PaymentUI_CommonUtilities.paymentInProgressMessageXpath);
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
