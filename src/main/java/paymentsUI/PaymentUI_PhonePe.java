package paymentsUI;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_PhonePe extends PaymentNodeJS{

	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	protected String cleartripQaUrl="https://qa2.cleartrip.com";
	Cookie cookieName = new Cookie("ct-auth", "rMSd%2B7hfyuZ4uZjjNKlV42pN3SDMXLXVZk1TVNkNLVrKtFI4anQeWMFUWdffHNMIJWNRt682KuqiFuXV2whf5HObWzaif65XOuM37YyrPLX%2BaiBNwnkmSmMem2WbpgBvzIOK8AA0ICwzYBAszCVlK7Wt4vbavb4Rc9plZba2GjgZcQBlPkvWs0YEAP%2B2OXYcwxeut2x6p5X1i%2BvPjnOjN5c7bmkG62x2TeRUPL%2BcTfQ7ZtPZvYpaAQ3oRcyhXrPhCUmcbRdKxTvjY08FAtXwySBwZnpRB%2Fr6Tdc4tErNeglqJTknezoRpPhKBzjfu1gtd8ro1XIKetU3yLt3kXt9RMitRVpAKIqLA%2Bkwfued9ARpSFWPHNzcb5k%2BZjusDdQuULECGHAP00B8LK7MltV20wodXXSeczhpDpmjAwJJBWF2kulqJ%2FaQ5Oi%2BUMmQ92BEqwQ0%2FZ1GGS%2FCsh4%2Flet6bIQmTJelK7OdeSLJlOhcpan1uHwoj5PmK6CrwQl4iGe6N0IBzS8MCjon9SGgFW8uc%2B97NUe06yWRwDtxLHRrqe%2B8UfmNCT%2B9HIFFr7urccGIf09n7B1MBN2D%2F3uBsb4bR8YYXRDmXYUVm%2FXms5YZHzl1u0HRpkoj3SJCZNksleaf4%2FRMFvDNJjcW0zkxFMlzew2BiwCGms1A%2Bpuib7AbTmi3KrivJofipyqlrlOmpIFB1G8rwPKUm0CWFPPLzylXMjky%2BAwgs2JmZ98juSAlxpgfxu0MBkt3zdybT2Q%2FMN0xIQa%2FoD%2FBy5WV6e%2FBKNrYyg%3D%3D");
	public Response resp;


	@BeforeClass 
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","");
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}

	@Test(priority=1)
	public void getPayURL() throws Exception{
		validation_PaymentUI("BookApp/GetPay", resp);

	}

	@Test(priority=2, dependsOnMethods={"getPayURL"})
	public void phonePayPayment() throws Exception{
		try {
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			driver.manage().addCookie(cookieName);
			refreshPage(driver);
			Thread.sleep(6000);
			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//waitForElementVisibility(driver,By.xpath("//input[@type='submit']"), 15);
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
			safeType(driver,By.xpath("//input[@name='mobileNumber']"), "8951009392");
			click(driver,PaymentUI_CommonUtilities.phonePeMobileNoSubmit);
			driver.get(Url);
			Thread.sleep(6000);
			waitForElementVisibility(driver,By.xpath("//h1[text()='Payment in progress']"),10);
			validateIfPresent(driver,PaymentUI_CommonUtilities.paymentInProgressHeaderXpath);
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