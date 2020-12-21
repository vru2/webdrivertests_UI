package paymentsUI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentsUI_NB extends PaymentNodeJS{

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

	

	@Test(priority=1)
	public void cancelNBPayment() throws Exception{
			validation_PaymentUI("BookApp/GetPay", resp);

			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			elementVisible(driver,PaymentUI_CommonUtilities.paymentModes, 30);
			/*validatePaymentURLLoad(driver,Url);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeNB,PaymentUI_CommonUtilities.paymentModexpath);
			click(driver,PaymentUI_CommonUtilities.netBankingPaymentxpath);
			convenienceText(driver,PaymentUI_CommonUtilities.convenienceTextxpath);
			validateIfPresent(driver, PaymentUI_CommonUtilities.popularBanks);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			validateErrors(driver,PaymentUI_CommonUtilities.errortextSelectBank,PaymentUI_CommonUtilities.errorSelectBankxpath);
			click(driver,PaymentUI_CommonUtilities.nbCitiSelectXpath);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			elementVisible(driver, By.xpath("//select[@name='PAID']"), 30);
			selectItemFromList(driver,PaymentUI_CommonUtilities.citiBankTransaction,"N");
			elementVisible(driver, PaymentUI_CommonUtilities.nbCitiSubmit, 30);
			click(driver,PaymentUI_CommonUtilities.nbCitiSubmit);
			validateIfPresent(driver, PaymentUI_CommonUtilities.invalid3DFailureXpath);*/
		
	}


	@Test(priority=2)
	public void successfulNBPayment() throws Exception{
			elementVisible(driver,PaymentUI_CommonUtilities.paymentModes, 30);
			Thread.sleep(2000);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeNB,PaymentUI_CommonUtilities.paymentModexpath);
			click(driver,PaymentUI_CommonUtilities.netBankingPaymentxpath);
			convenienceText(driver,PaymentUI_CommonUtilities.convenienceTextxpath);
			validateIfPresent(driver, PaymentUI_CommonUtilities.popularBanks);
			selectItemFromList(driver,PaymentUI_CommonUtilities.nbDropDown,"ICICI Bank");
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			Thread.sleep(5000);
		/*	selectItemFromList(driver,PaymentUI_CommonUtilities.citiBankTransaction,"Y");
			elementVisible(driver, PaymentUI_CommonUtilities.nbCitiSubmit, 30);
			click(driver,PaymentUI_CommonUtilities.nbCitiSubmit);*/
			textPresent(driver, "Welcome to Razorpay Bank", 20);
			safeClick(driver, By.xpath("//button"));
			Thread.sleep(5000);
			payUI_Mock_ConfirmationPage(driver, Url);
			/*
			driver.get(Url);
			validateIfPresent(driver,PaymentUI_CommonUtilities.paymentSuccessHeaderTextXpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.paymentSuccessMessageTextXpath);	*/	
	}

	/*@Test(priority=4)
	public void paymentWallet() throws Exception{
		try {

			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			elementVisible(driver, By.xpath("//ul[@id='paymentModeTab']"), 10);
			validatePaymentURLLoad(driver,Url);
			Cookie cookieName = new Cookie("ct-auth", "rMSd%2B7hfyuZ4uZjjNKlV42pN3SDMXLXVZk1TVNkNLVrKtFI4anQeWMFUWdffHNMIJWNRt682KuqiFuXV2whf5HObWzaif65XOuM37YyrPLX%2BaiBNwnkmSmMem2WbpgBvzIOK8AA0ICwzYBAszCVlK7Wt4vbavb4Rc9plZba2GjgZcQBlPkvWs0YEAP%2B2OXYcwxeut2x6p5X1i%2BvPjnOjN5c7bmkG62x2TeRUPL%2BcTfQ7ZtPZvYpaAQ3oRcyhXrPhCUmcbRdKxTvjY08FAtXwySBwZnpRB%2Fr6Tdc4tErNeglqJTknezoRpPhKBzjfu1gtd8ro1XIKetU3yLt3kXt9RMitRVpAKIqLA%2Bkwfued9ARpSFWPHNzcb5k%2BZjusDdQuULECGHAP00B8LK7MltV20wodXXSeczhpDpmjAwJJBWF2kulqJ%2FaQ5Oi%2BUMmQ92BEqwQ0%2FZ1GGS%2FCsh4%2Flet6bIQmTJelK7OdeSLJlOhcpan1uHwoj5PmK6CrwQl4iGe6N0IBzS8MCjon9SGgFW8uc%2B97NUe06yWRwDtxLHRrqe%2B8UfmNCT%2B9HIFFr7urccGIf09n7B1MBN2D%2F3uBsb4bR8YYXRDmXYUVm%2FXms5YZHzl1u0HRpkoj3SJCZNksleaf4%2FRMFvDNJjcW0zkxFMlzew2BiwCGms1A%2Bpuib7AbTmi3KrivJofipyqlrlOmpIFB1G8rwPKUm0CWFPPLzylXMjky%2BAwgs2JmZ98juSAlxpgfxu0MBkt3zdybT2Q%2FMN0xIQa%2FoD%2FBy5WV6e%2FBKNrYyg%3D%3D");
			driver.manage().addCookie(cookieName);
			refreshPage(driver);
			elementVisible(driver, By.xpath("(//label[@class='checkbox-round'])[1]"), 10);
			List<WebElement> walletCheckBoxList = driver.findElements(By.xpath("(//label[@class='checkbox-round'])[1]"));
			if(walletCheckBoxList.size()!=0) {
				click(driver,PaymentUI_CommonUtilities.walletCheckBox);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			}
			
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false);
		}

	}*/
	
	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
	
	@AfterClass
	public void tearDown(){
		browserClose(driver);
	}

}
