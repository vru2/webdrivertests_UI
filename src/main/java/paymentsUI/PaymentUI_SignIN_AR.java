package paymentsUI;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_SignIN_AR extends PaymentNodeJS{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	
	@BeforeClass
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
		//qaUrl = qaurl;
		Url = "https://qa2.cleartrip.com/pay/train/WlxVcEdAQxBJMzk1UQ==?lang=ar";
		Reporter.log(Url);
	}
	

	@Test
	public void PaymentUI_SignIN() throws Exception{

			driver=(RemoteWebDriver) getDriver(driver);
			driver.get(Url);
			Thread.sleep(5000);
			String AR = getText(driver, By.xpath("//label"));
			System.out.println("AR "+AR);
			if(textPresent(driver, "Credit card no.", 10)) {
				System.out.println("text credit card is present");
			}
			assertNotEquals(AR, "Credit card no.");
    }	

	
	@AfterMethod (alwaysRun = true)
	  public void afterMethod(ITestResult _result) throws Exception {
		 afterMethod(driver, _result);
	  }
	
	 @AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);
	  }
}
