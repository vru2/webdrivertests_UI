package paymentsUI;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PaymentUI_AddStoredCardAndValidate extends domains.PaymentNodeJS{

	public RemoteWebDriver driver;
	protected String Url;
	protected String paymentUrl;
	protected String qaUrl;
	protected String cleartripQaUrl="https://qa2.cleartrip.com";
	Cookie cookieName = new Cookie("ct-auth", "rMSd%2B7hfyuZ4uZjjNKlV42pN3SDMXLXVZk1TVNkNLVrKtFI4anQeWMFUWdffHNMIJWNRt682KuqiFuXV2whf5HObWzaif65XOuM37YyrPLX%2BaiBNwnkmSmMem2WbpgBvzIOK8AA0ICwzYBAszCVlK7Wt4vbavb4Rc9plZba2GjgZcQBlPkvWs0YEAP%2B2OXYcwxeut2x6p5X1i%2BvPjnOjN5c7bmkG62x2TeRUPL%2BcTfQ7ZtPZvYpaAQ3oRcyhXrPhCUmcbRdKxTvjY08FAtXwySBwZnpRB%2Fr6Tdc4tErNeglqJTknezoRpPhKBzjfu1gtd8ro1XIKetU3yLt3kXt9RMitRVpAKIqLA%2Bkwfued9ARpSFWPHNzcb5k%2BZjusDdQuULECGHAP00B8LK7MltV20wodXXSeczhpDpmjAwJJBWF2kulqJ%2FaQ5Oi%2BUMmQ92BEqwQ0%2FZ1GGS%2FCsh4%2Flet6bIQmTJelK7OdeSLJlOhcpan1uHwoj5PmK6CrwQl4iGe6N0IBzS8MCjon9SGgFW8uc%2B97NUe06yWRwDtxLHRrqe%2B8UfmNCT%2B9HIFFr7urccGIf09n7B1MBN2D%2F3uBsb4bR8YYXRDmXYUVm%2FXms5YZHzl1u0HRpkoj3SJCZNksleaf4%2FRMFvDNJjcW0zkxFMlzew2BiwCGms1A%2Bpuib7AbTmi3KrivJofipyqlrlOmpIFB1G8rwPKUm0CWFPPLzylXMjky%2BAwgs2JmZ98juSAlxpgfxu0MBkt3zdybT2Q%2FMN0xIQa%2FoD%2FBy5WV6e%2FBKNrYyg%3D%3D");
	public Response resp;



	@BeforeClass
	public void setUp() throws Exception {
		resp = payUIget("BookApp/GetPay","","");
		qaUrl = qaurl;
		Url = qaUrl+ fetchPaymentURL(resp);
	}


	@Test
	public void addSCandVerifyIfAdded() throws Exception{
			driver=(RemoteWebDriver) getDriver(driver);
			driver.manage().deleteAllCookies();
			signCleartripHomePage(driver,"cltppayment@gmail.com","cleartrip");
//			driver.get(cleartripQaUrl);
//			driver.manage().addCookie(cookieName);
			refreshPage(driver);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//			click(driver,PaymentUI_CommonUtilities.manageTripsXpath);
			//			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			//elementVisible(driver, getObject("Acc_Expressway_Tab"), 10);
			//			textPresent_Log(driver, "Trips you've booked", 10);
			//			safeClick(driver, getObject("Acc_Expressway_Tab"));
			//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			click(driver,PaymentUI_CommonUtilities.homePageUserAccountLinkXpath);
			click(driver,PaymentUI_CommonUtilities.homePageAccountEWLinkXpath);
			Thread.sleep(3000);
			boolean savedCards = driver.findElement(By.xpath("//div/h2[contains(text(),'Your saved cards')]")).isDisplayed();
			if(savedCards==true)
			{
				List<WebElement> savedCardList= driver.findElements(By.xpath("//ul[@id='cardlist']/li"));
				int size=savedCardList.size();
				while(size>1)
				{
					click(driver,PaymentUI_CommonUtilities.removeStoredCardXpath);
					Alert alert = driver.switchTo().alert();
					alert.accept();
					Thread.sleep(5000);
					size--;
				}
			}
			
			//boolean storeCard = driver.findElement(By.xpath("//h5[contains(text(),'Introducing single-click flight bookings')]")).isDisplayed();
			else
			{
				click(driver,PaymentUI_CommonUtilities.addNewCardEWXpath);
				fillCardDetailsForEW(driver, PaymentUI_CommonUtilities.ewCardNumberXpath,PaymentUI_CommonUtilities.ewCardHolderNameXpath,PaymentUI_CommonUtilities.ewCardExpiryMonthXpath,PaymentUI_CommonUtilities.ewCardExpiryYearXpath);
				click(driver,PaymentUI_CommonUtilities.ewAddCardButtonXpath);
				safeType(driver,By.xpath("//textarea[contains(@id,'xpress_street_address')]"),"test");
				safeType(driver,By.xpath("//input[contains(@id,'xpress_city')]"),"test");
				safeType(driver,By.xpath("//input[contains(@id,'xpress_state')]"),"test");
				safeType(driver,By.xpath("//input[contains(@id,'xpress_pin')]"),"123456");
				safeType(driver,By.xpath("//input[contains(@id,'xpress_country')]"),"India");
				click(driver,PaymentUI_CommonUtilities.ewAddCardAddrButtonXpath);
				List<WebElement> element= driver.findElements(By.xpath("//h3[contains(text(),'3456 78')]"));
				if(element.size()==1)
				{	click(driver,PaymentUI_CommonUtilities.removeStoredCardXpath);
					Alert alert = driver.switchTo().alert();
					alert.accept();
				}
				}
			
//			else{
//			List<WebElement> element= driver.findElements(By.xpath("//h3[contains(text(),'3456 78')]"));
//				if(element.size()==1)
//				{
//					click(driver,PaymentUI_CommonUtilities.removeStoredCardXpath);
//					Alert alert = driver.switchTo().alert();
//					alert.accept();
//				}
//			}
				driver=(RemoteWebDriver) getDriver(driver);
				driver.get(Url);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.manage().addCookie(cookieName);
				refreshPage(driver);
				elementVisible(driver, By.xpath("(//label[@class='checkbox-round'])[1]"), 10);
				List<WebElement> walletCheckBoxList = driver.findElements(By.xpath("(//label[@class='checkbox-round'])[1]"));
				if(walletCheckBoxList.size()!=0) 
				{
					click(driver,PaymentUI_CommonUtilities.walletCheckBox);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				}
				fillValidAmexCreditCardDetails(driver,PaymentUI_CommonUtilities.cardNumberxpath,PaymentUI_CommonUtilities.cardHolderNamexpath,PaymentUI_CommonUtilities.expiryMonthxpath,PaymentUI_CommonUtilities.expiryYearxpath,PaymentUI_CommonUtilities.cvvNumberxpath);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				assert_elementNotPresent(driver, By.xpath("//p[text()='Stored Cards']"));
				//click(driver,PaymentUI_CommonUtilities.storedCardCheckbox);
				click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				elementVisible(driver, By.xpath("//input[@type='submit']"), 10);
				click(driver,PaymentUI_CommonUtilities.amexGatewayAuthenticationSubmitxpath);
				resp = payUIget("BookApp/GetPay","",getNewDate_TripID());
				Url = qaUrl+ fetchPaymentURL(resp);
				driver.get(Url);
				elementVisible(driver, By.xpath("(//label[@class='checkbox-round'])[1]"), 10);
				click(driver,PaymentUI_CommonUtilities.walletCheckBox);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//validatePaymentModes(driver,PaymentUI_CommonUtilities.paymentModeStoredCard,PaymentUI_CommonUtilities.paymentModexpath);
				validateIfPresent(driver,PaymentUI_CommonUtilities.storedCardNewXpath);
				click(driver,PaymentUI_CommonUtilities.storedCardNewXpath);
				validateIfPresent(driver,PaymentUI_CommonUtilities.amexCardIsPresentEndingWithSeven);
				click(driver,PaymentUI_CommonUtilities.makePaymentbutton);
				validateIfPresent(driver,PaymentUI_CommonUtilities.errortextCvvXpath);
				validateIfPresent(driver,PaymentUI_CommonUtilities.storedCardNewXpath);
			

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
