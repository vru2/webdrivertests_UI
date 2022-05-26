package test.java.paymentsUI;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_PhonePe extends PaymentNodeJS{

	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	protected String cleartripQaUrl="https://qa2.cleartrip.com";
	Cookie cookieName = new Cookie("ct-auth", "l6Z4I%2B04vrKt5Gx5nozaFyIseNC%2F5P86kzHHuN%2BPzKN4kr%2Bi%2B8B1m%2FB3fm8feq89U1wSr%2BJEjIe7OwdBdJFDtD0X9xTlhHu6stvjt4kgx7V%2BU5jhnqDtCyzJKu4r3kS7Dzw77SFNX94pdJKH9ncdBCJZfiYsA%2BxsMZOCWYm%2F5RhvQr3zj8pfk%2FTLaX5oxC%2FmMyFYg0rsrdIWJGsJLs3nG583asuyXjEFE9HqevjGqPQwtTWw3v1Ps%2FeXcTnweK96BAslgpJfYKqT8TQ9YUyOz9Hd48BLn8TPZ64oXHdiw9WArciwjup573ATWfGIR6KzSPC%2F0YxQBY8N9T2dntLTLwF3mcBECRuqgHzn%2Bi1153OwIoMo%2BwfT%2FGlbG1ftVLochM6pqdxHxCtCG4wWZpi%2FblzMcH4QxwyXRYtvbXf8Kr0uDeTP%2Fbrb9ybJ0uaGfelr%2BlKW75DV2cw0UTjQ7wxTBXUbkYGBSGMD8WnnVlChJW04snx7V%2BNTla2AfO3y6t56vdtyuN44Cw9fCyJpDCyE1vzru9iFV7Ni%2Bz4qUj1wJ3pelHswRA5V2l1g79fciujmH%2FlCz0LimGX5mv0W%2FEn0EjvVC2OxVAXWGRLidTk2kEY%3D");
	public Response resp;


	@BeforeClass 
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
		
	}

	/*@Test(priority=1)
	public void getPayURL() throws Exception{
		//validation_PaymentUI("BookApp/GetPay", resp);

	}*/

	@Test(priority=1)
	public void phonePayPayment() throws Exception{
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			driver.manage().addCookie(cookieName);
			refreshPage(driver);
			Thread.sleep(6000);
			List<WebElement> walletCheckBoxList=driver.findElements(By.xpath("//label[@class='checkbox-round'][1]"));
			int chkBoxes=walletCheckBoxList.size();
			for(int i=0;i<chkBoxes;i++)
			{
				walletCheckBoxList.get(0).click();
				break;
			}
			elementVisible(driver,PaymentUI_CommonUtilities.paymentModes, 10);
			validatePaymentURLLoad(driver,Url);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeUPI,PaymentUI_CommonUtilities.paymentModexpath);
			click(driver,PaymentUI_CommonUtilities.upiModeXpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			validateIfPresent(driver,PaymentUI_CommonUtilities.convenienceXpathNew);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			validateErrors(driver,PaymentUI_CommonUtilities.upiErrorTextSelectUPI,PaymentUI_CommonUtilities.upiErrorTextXpath);
			driver.findElement(By.xpath("//li[@role='menuitem']")).click();
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			waitForElementVisibility(driver,By.xpath("//input[@name='mobileNumber']"),20);
			safeType(driver,By.xpath("//input[@name='mobileNumber']"), "9986696785");
			click(driver,PaymentUI_CommonUtilities.phonePeMobileNoSubmit);
			driver.get(Url);
			waitForElementVisibility(driver,By.xpath("//h1[text()='Payment in progress']"),10);
			validateIfPresent(driver,PaymentUI_CommonUtilities.paymentInProgressHeaderXpath);
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