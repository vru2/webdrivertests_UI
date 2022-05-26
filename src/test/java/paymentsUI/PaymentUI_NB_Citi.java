package test.java.paymentsUI;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import test.java.paymentsUI_Air.PaymentUI_Common;


public class PaymentUI_NB_Citi extends PaymentUI_Common{

	public RemoteWebDriver driver;
	public String Url;
	protected String paymentUrl;
	protected String qaUrl;
	protected String cleartripQaUrl="https://qa2.cleartrip.com";
	public Response resp;


	@BeforeClass 
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}

	@Test()
	public void amazonPayPayment() throws Exception{

		driver=(RemoteWebDriver) getDriver(driver);
		driver.manage().deleteAllCookies(); 
		driver.get(Url);
		elementPresent(driver,By.xpath(PaymentUI_CommonUtilities.paymentModes), 10);
			elementVisible(driver,PaymentUI_CommonUtilities.paymentModes, 10);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeNB,PaymentUI_CommonUtilities.paymentModexpath);
			click(driver,PaymentUI_CommonUtilities.netBankingPaymentxpath);
			convenienceText(driver,PaymentUI_CommonUtilities.convenienceTextxpath);
			/*validateIfPresent(driver, PaymentUI_CommonUtilities.popularBanks);
			selectItemFromList(driver,PaymentUI_CommonUtilities.nbDropDown,"Citibank");
*/
			payUI_Enter_PaymentDetails(driver, "NB", "Axis Bank", "TRAINS");
			payUI_Mock_ConfirmationPage_Train(driver, Url);
		
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
