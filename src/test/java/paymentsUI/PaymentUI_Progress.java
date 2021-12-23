package paymentsUI;

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

import domains.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_Progress extends PaymentNodeJS{
	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	public Response resp;
	Cookie cookie_Parl_Wallet = new Cookie("ct-auth", "g%2BN18HeWvZyEk7HQeFThJxsObDgYiHvhM4u28wBQ4BejFU9Ke4ME8Tw3kDrS%2BYaXuQmW3NiUZma8q2lELnUuyC3uAF5DaTQONdJlLn%2FO2me%2FiLCzDjUE8Mm7nMigogz0dlw4XnBuAMvrrzg32ECZZREQ3m1%2Fwdl0v8nRObVcrkLkfySvhcY68HkUF0r5u%2Bu1GYGQ%2FU5486qrW%2F0RUIlB1Q%3D%3D");
	


	@BeforeClass
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}

	@Test(priority=1)
	public void firePaymentURLandValidatePaymentModes() throws Exception{		
		validation_PaymentUI("BookApp/GetPay", resp);
		
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies(); 
			driver.get(Url);
			driver.manage().addCookie(cookie_Parl_Wallet);
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

		
	}

	@Test(priority=2)
	public void inProgressPayment() throws Exception{
			boolean storedCard = driver.findElementByXPath("//p[text()='Saved card']").isDisplayed();
			if(storedCard==true)
			{
				click(driver,PaymentUI_CommonUtilities.creditCardPaymentxpath);
				fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
			}
			else 
			{
				fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
			}

			List<WebElement> walletCheckBoxList=driver.findElements(By.xpath("//label[@class='checkbox-round'][1]"));
			int chkBoxes=walletCheckBoxList.size();
			for(int i=0;i<chkBoxes;i++){
				walletCheckBoxList.get(1).click();
				break;
			}

			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			Thread.sleep(6000);
			driver.get(Url);
			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			/*waitForElementVisibility(driver,By.xpath("//h1[text()='Payment in progress']"),10);
			validateIfPresent(driver, PaymentUI_CommonUtilities.paymentInProgressHeaderXpath);
			validateIfPresent(driver, PaymentUI_CommonUtilities.paymentInProgressMessageXpath);*/

		
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