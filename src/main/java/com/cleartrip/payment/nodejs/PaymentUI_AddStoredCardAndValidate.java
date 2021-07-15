package com.cleartrip.payment.nodejs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.PaymentNodeJS;
import io.restassured.response.Response;

public class PaymentUI_AddStoredCardAndValidate extends PaymentNodeJS{

	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	protected String cleartripQaUrl="https://qa2.cleartrip.com";
	Cookie cookieName = new Cookie("ct-auth", "87C9Fr7mr3oSQFuWpTeSCZlZpqHYrtP%2FN5zEPqC5NLF%2B7J7ysRwE3EJeclaozLuvEo8d6Dfhz4%2BHvS1m%2FYxr2Vmj7x5fSq8hjnslk91WN3I5qTd8EGUynsZ%2FVC2INa7RZJtx%2BJ31duZqrcZ2jUdJ6F4ai43jNI5aChVTEmGAfqQahvGkzHhRz5kPpCa%2F7j3utX4un4jpIzA3dx1i9UD1iDhmbaHDtVTELASCTE65iYNDav43rPHfhVjrxuJhRUe9V9J1s9EnvD%2BVObAzErSBUd%2BYomLdDuWrqqdRzGq7%2FdYA0m79Z5nuhvvWgXtLZpphBygb5DF%2BiBArg6EEGJYTRX%2BnhhaYeP8g0CEQkY6Tiy5TjaMpmSpDgnnLm%2BDSJ1neLNDdt4lbkIkiHm4U%2BILB5nlL8dxoLWJl9kyPF816r63Maj2L4amZjXfwchT0mTlpDa2ygLFp5L81InkTtQTOjoJCv6yAseB3ObeldP%2FhImNgP9PGmxEa81Fz1%2BHQguMZWLfjM1jmDCo03nvJokOAR8xBHhCOYHkcqF3etZ%2FAHD3WZG1RF1NXr2CdZBW3klLFUzFq5a8OyqY6J83eKcrC4c2467U3gj1pCXgKnrdubNT23%2FJmi3O7R3hRt1fvImaIstb4Osgh5F%2B5Fg%2F7a10PMlIgM50KZ7fvyMp%2B9uJutBYZCv0ClddN0dhcS21DReuju5PdOpRDNrlvp048Qsh%2FSFJGWlfe7jVRo3luuP3HszUWnHLxYLnXIEc2Rjg%2BMXNdgL6Ek3DtVVynksSC5TSzafbmhQnnA7qOnJ9zWkBZ6Cy96ygb6H2kOrups52wYmlAHmTTLNI8JuKgrbxs7igclST5jyTLdsPPE1lsPb9%2BcRtWEtptA838hbZ5ZPCO0rkaUy4a4Y5tevtmRSjvPZLxkJmw9s1v2%2B30pkAbWeG8m3wJbOoJNi2j03NINZZdJIZt9t2Nmf7TmGY7spVpsIEC%2Fdff2mlWX24BkTQWa9jWIIZkbgx%2BxShDqSPcHZs5AWQFfGpy3c0NQyfusTAdUzM%2BJ74J1zQBLDW5t%2FP5f0nF2qQQPqwSPiePJNd%2BFISixOPYV5%2BjtduCt70pagO2Z6Fi7P8QVUn9Go5qR1YW4I8l%2BE8WKKupgF5qulgt5GqGEiWTHn58Czwyx360nm%2Fqza7KSoyQK5%2BX1HaK3BQVE0c2LonUCPFIMoZVV3TwyXGvte8LQbp3mnw6URpHvhNqu5FZsyRUHgrkW3j9IoVne7s41y3WT1kxLmSVTJxwpVwcgg4r%2Ff82HEJufpHImkACd8E2gXM38Uxk4hCIWXzfChlJC4RVQH0fkbszOy%2FypVXxn0xqHRTUtbI7XGE%2FLephNxcLPw6lnd4dO4P4OGSRrKrCGwMpGXVCYSdYnD2Kh7E9tY5FWKySSXBgPh30%2FoMok8jNSDsHWV5MfG%2FD3Erusdk7HZeokbTBKWaNP0pxrH1zZYJdDGH0qox5c%2BhsEi0PpoNB7I3EkLE7ZyXicaMAfVuTJ%2FY%3D");
	public Response resp;
	


	@BeforeClass
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","");
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}


	@Test
	public void addSCandVerifyIfAdded() throws Exception{
          
		try {
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies();
			driver.get(cleartripQaUrl);
			driver.manage().addCookie(cookieName);
			refreshPage(driver);
			click(driver,PaymentUI_CommonUtilities.manageTripsXpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(10000);
			elementVisible(driver, getObject("Acc_Expressway_Tab"), 10);
			textPresent_Log(driver, "Trips you've booked", 10);
			safeClick(driver, getObject("Acc_Expressway_Tab"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			List<WebElement> element= driver.findElements(By.xpath("//h3[contains(text(),'3456 78')]"));
			if(element.size()==1){
				click(driver,PaymentUI_CommonUtilities.removeStoredCardXpath);
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
			driver=(RemoteWebDriver) getDriver(driver);
			driver.get(Url);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().addCookie(cookieName);
			refreshPage(driver);
			fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			assert_elementNotPresent(driver, By.xpath("//p[text()='Stored Cards']"));
			click(driver,PaymentUI_CommonUtilities.storedCardCheckbox);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			elementVisible(driver, By.xpath("//input[@type='submit']"), 10);
			click(driver,PaymentUI_CommonUtilities.amexGatewayAuthenticationSubmitxpath);
			resp = payUIget("BookApp/GetPay","");
			Url = qaUrl+ fetchPaymentURL(resp);
			driver.get(Url);
			validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeStoredCard,PaymentUI_CommonUtilities.paymentModexpath);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			click(driver,PaymentUI_CommonUtilities.storedCardXpath);
			validateIfPresent(driver,PaymentUI_CommonUtilities.amexCardIsPresentEndingWithSeven);
			click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
			validateIfPresent(driver,PaymentUI_CommonUtilities.errortextCvvXpath);
			elementPresent(driver, By.xpath("//p[text()='Stored Cards']"));
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
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}
