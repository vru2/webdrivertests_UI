package paymentsUI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.PaymentNodeJS;
import io.restassured.response.Response;


public class PaymentUI_TPW_AmazonPay extends PaymentNodeJS{

	public RemoteWebDriver driver;
	public String Url;
	protected String paymentUrl;
	protected String qaUrl;
	protected String cleartripQaUrl="https://qa2.cleartrip.com";
	Cookie cookieName = new Cookie("ct-auth", "l6Z4I%2B04vrKt5Gx5nozaFyIseNC%2F5P86kzHHuN%2BPzKN4kr%2Bi%2B8B1m%2FB3fm8feq89U1wSr%2BJEjIe7OwdBdJFDtD0X9xTlhHu6stvjt4kgx7V%2BU5jhnqDtCyzJKu4r3kS7Dzw77SFNX94pdJKH9ncdBCJZfiYsA%2BxsMZOCWYm%2F5RhvQr3zj8pfk%2FTLaX5oxC%2FmMyFYg0rsrdIWJGsJLs3nG583asuyXjEFE9HqevjGqPQwtTWw3v1Ps%2FeXcTnweK96BAslgpJfYKqT8TQ9YUyOz9Hd48BLn8TPZ64oXHdiw9WArciwjup573ATWfGIR6KzSPC%2F0YxQBY8N9T2dntLTLwF3mcBECRuqgHzn%2Bi1153OwIoMo%2BwfT%2FGlbG1ftVLochM6pqdxHxCtCG4wWZpi%2FblzMcH4QxwyXRYtvbXf8Kr0uDeTP%2Fbrb9ybJ0uaGfelr%2BlKW75DV2cw0UTjQ7wxTBXUbkYGBSGMD8WnnVlChJW04snx7V%2BNTla2AfO3y6t56vdtyuN44Cw9fCyJpDCyE1vzru9iFV7Ni%2Bz4qUj1wJ3pelHswRA5V2l1g79fciujmH%2FlCz0LimGX5mv0W%2FEn0EjvVC2OxVAXWGRLidTk2kEY%3D");
	public Response resp;


	@BeforeClass 
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
		qaUrl = qaurl;
	}

	@Test()
	public void amazonPayPayment() throws Exception{

		Url = qaUrl+ fetchPaymentURL(resp);
		String PayUrl = getPayUI("Air", "");
			validation_PaymentUI("BookApp/GetPay", resp);	
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);			
			elementPresent(driver,By.xpath(PaymentUI_CommonUtilities.paymentModes), 10);
			elementVisible(driver,PaymentUI_CommonUtilities.paymentModes, 10);
			validatePaymentURLLoad(driver,Url);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeWallet,PaymentUI_CommonUtilities.paymentModexpath);
			click(driver,PaymentUI_CommonUtilities.walletsXpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			validateIfPresent(driver,PaymentUI_CommonUtilities.convenienceXpathNew);
			safeClick(driver, By.xpath("//li[7]"));
			Thread.sleep(2000);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			/*validateErrors(driver,PaymentUI_CommonUtilities.walletErrorTextSelectWallet,PaymentUI_CommonUtilities.walletErrorTextXpath);
			
			click(driver,PaymentUI_CommonUtilities.walletAmazonPaySelectXpath);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);*/
			waitForElementVisibility(driver,By.xpath("//img[contains(@alt,'Amazon Pay')]"),30);
			validateIfPresent(driver,PaymentUI_CommonUtilities.amazonPayUsernameXpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.amazonPayPasswordXpath);
			textPresent_Log(driver, "Login with your Amazon account", 20);
			safeType(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Email"), "kiran.kumar@cleartrip.com");
			safeType(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Password"), "Cleartrip@123");
			safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Signin_Login"));
			textPresent_Log(driver, "Select payment method", 20);
			safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard"));
			safeType(driver, getObjectPayment("MakePayment_Amazon_Page_SelectCard_CVV"), "123");
			safeClick(driver, getObjectPayment("MakePayment_Amazon_Page_Pay_Button"));
			Thread.sleep(5000);
			payUI_Mock_ConfirmationPage(driver, Url);
		
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
